package com.smil.dcs.common;

import com.microsoft.azure.storage.blob.*;
import com.microsoft.azure.storage.blob.models.BlobHTTPHeaders;
import com.microsoft.azure.storage.blob.models.BlobItem;
import com.microsoft.azure.storage.blob.models.ContainerListBlobFlatSegmentResponse;
import com.microsoft.azure.storage.blob.models.PublicAccessType;
import com.microsoft.rest.v2.RestException;
import com.smil.dcs.exception.DcsRuntimeException;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;


@Component
@ConfigurationProperties(prefix = "azure")
public class AzureBlobService {

    protected static Logger log = LoggerFactory.getLogger(AzureBlobService.class);

    private static final String SLASH = "/";


    private  String defaultEndpointsProtocol;

    private  String accountName;

    private  String accountKey;

    private  String endpointSuffix;

    public String uploadBlob(File sourceFile,String container,String fileName,PublicAccessType accessType){
        try {
            ContainerURL containerURL = this.getContainerUrl(container,accessType,Boolean.TRUE);
            // Create a BlockBlobURL to run operations on Blobs
            BlockBlobURL blockBlobURL = this.getBlockBlobURL(containerURL, fileName);
            this.uploadBlob(blockBlobURL,sourceFile);
            return blockBlobURL.toString();
        } catch (InvalidKeyException | IOException e) {
            throw new DcsRuntimeException(e);
        }
    }

    private void uploadBlob(BlockBlobURL blob, File sourceFile) throws IOException {
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(sourceFile.toPath());
        // Uploading a file to the blobURL using the high-level methods available in TransferManager class
        // Alternatively call the PutBlob/PutBlock low-level methods from BlockBlobURL type
        TransferManagerUploadToBlockBlobOptions transferManagerUploadToBlockBlobOptions = new TransferManagerUploadToBlockBlobOptions(null,new BlobHTTPHeaders().withBlobContentType(Files.probeContentType(Paths.get(sourceFile.getAbsolutePath()))),null,null,null);
        //此处上传应是异步操作,channel不应在此方法结束关闭
        TransferManager.uploadFileToBlockBlob(fileChannel, blob, 8*1024*1024, transferManagerUploadToBlockBlobOptions).subscribe(response->{
            log.info("Completed upload request ,blobUrl:{} , the responseCode is :{}  ",blob,response.statusCode());
            Files.delete(sourceFile.toPath());
            fileChannel.close();
        });
    }

    private  Single<ContainerListBlobFlatSegmentResponse> listAllBlobs(ContainerURL url, ContainerListBlobFlatSegmentResponse response) {
        // Process the blobs returned in this result segment (if the segment is empty, blobs() will be null.
        if (response.body().segment() != null) {
            for (BlobItem b : response.body().segment().blobItems()) {
                String output = "Blob name: " + b.name();
                if (b.snapshot() != null) {
                    output += ", Snapshot: " + b.snapshot();
                }
                log.info(output);
            }
        }
        else {
            log.info("There are no more blobs to list off.");
        }

        // If there is not another segment, return this response as the final response.
        if (response.body().nextMarker() == null) {
            return Single.just(response);
        } else {
            /*
            IMPORTANT: ListBlobsFlatSegment returns the start of the next segment; you MUST use this to get the next
            segment (after processing the current result segment
            */

            String nextMarker = response.body().nextMarker();

            /*
            The presence of the marker indicates that there are more blobs to list, so we make another call to
            listBlobsFlatSegment and pass the result through this helper function.
            */

            return url.listBlobsFlatSegment(nextMarker, new ListBlobsOptions().withMaxResults(10), null)
                    .flatMap(containersListBlobFlatSegmentResponse ->
                            listAllBlobs(url, containersListBlobFlatSegmentResponse));
        }
    }

    public void deleteBlob(String filePath){
        try {
            String url = this.getUrl();
            if(!filePath.contains(url)){
                throw new DcsRuntimeException("the current file does not exist or is not supported");
            }
            String args = filePath.replace(url+"/","");
            ContainerURL containerURL = this.getContainerUrl(args.substring(0,args.lastIndexOf(SLASH)),null,Boolean.FALSE);
            // Create a BlockBlobURL to run operations on Blobs
            BlockBlobURL blockBlobURL = this.getBlockBlobURL(containerURL, args.substring(args.lastIndexOf(SLASH)+1));
            this.deleteBlob(blockBlobURL);
        } catch (InvalidKeyException | IOException e) {
            throw new DcsRuntimeException(e);
        }
    }

    private void deleteBlob(BlockBlobURL blobURL) {
        // Delete the blob
        blobURL.delete(null, null, null)
                .subscribe(response -> log.info("blob deleteLeadTime blobURl:{} , the responseCode is :{} ",blobURL,response.statusCode()));
    }

    private String getUrl(){
        return defaultEndpointsProtocol+"://" + accountName +".blob."+ endpointSuffix;
    }


    private ContainerURL getContainerUrl(String container,PublicAccessType accessType,Boolean isCreate) throws InvalidKeyException, MalformedURLException {
            ContainerURL containerURL = null;
            // Create a ServiceURL to call the Blob service. We will also use this to construct the ContainerURL
            SharedKeyCredentials creds = new SharedKeyCredentials(accountName, accountKey);
            // We are using a default pipeline here, you can learn more about it at https://github.com/Azure/azure-storage-java/wiki/Azure-Storage-Java-V10-Overview
            final ServiceURL serviceURL = new ServiceURL(new URL(this.getUrl()), StorageURL.createPipeline(creds, new PipelineOptions()));
            // If container exists, we'll catch and continue
            containerURL = serviceURL.createContainerURL(container);
            //set container
            if(isCreate){
                this.createContainer(containerURL,accessType);
            }
        return containerURL;
    }

    // Create a BlockBlobURL to run operations on Blobs
    private BlockBlobURL getBlockBlobURL(ContainerURL containerURL,String blob){
        return containerURL.createBlockBlobURL(blob);
    }

    // If container exists, we'll catch and continue
    private void createContainer(ContainerURL containerURL,PublicAccessType accessType){
        try {
            containerURL.create(null,accessType,null).blockingGet();
        } catch (RestException e){
            if (e.response().statusCode() != 409) {
                throw e;
            } else {
                //已存在容器,处理容器访问权限,暂不处理
            }
        }
    }


    public String getDefaultEndpointsProtocol() {
        return defaultEndpointsProtocol;
    }

    public void setDefaultEndpointsProtocol(String defaultEndpointsProtocol) {
        this.defaultEndpointsProtocol = defaultEndpointsProtocol;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    public String getEndpointSuffix() {
        return endpointSuffix;
    }

    public void setEndpointSuffix(String endpointSuffix) {
        this.endpointSuffix = endpointSuffix;
    }
}
