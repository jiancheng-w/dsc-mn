/*
 * Copyright (C), 2013-2019, 上海赛可电子商务有限公司
 * FileName: LmBizConsts.java
 * Author:   chenliang
 * Date:     2019年1月18日 上午11:05:04
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.smil.dcs.enums.wms;

/**
 * 物流常量.<br> 
 *
 * @author chenliang
 */
public class WmsBizConsts {
    
    private WmsBizConsts() {
        super();
    }
    
    // wms_dn状态(0.已取消1.已创建2.已通知3.已接收)
    public static final Byte DN_STATUS_CANCELED = new Byte("0");
    public static final Byte DN_STATUS_CREATED = new Byte("1");
    public static final Byte DN_STATUS_NOTICED = new Byte("2");
    public static final Byte DN_STATUS_ACCEPTED = new Byte("3");
    
    // Hellmann and SMME ftp接口交互目录定义
    /** 1   Inbound ASN Inbound ASN message Dispatch Advice SMME to Hellmann*/    
    public static final String SH_DISPATCH_ADVICE_PENDING        = "DCS_Hellman/DispatchAdvice/Pending";
    public static final String SH_DISPATCH_ADVICE_PROCCESSED = "DCS_Hellman/DispatchAdvice/Proccessed";
    /** 2 Inbound Confirmation GR Confirmation message Inbound Confirm Hellmann to SMME */
    public static final String SH_INBOUND_CONFIRM_PENDING        = "DCS_Hellman/InboundConfirm/Pending";
    public static final String SH_INBOUND_CONFIRM_PROCCESSED = "DCS_Hellman/InboundConfirm/Proccessed";
    /** 3 Outbound Order Outbound SN for disptach message Outbound advice SMME to Hellmann */
    public static final String SH_OUTBOUND_ADVICE_PENDING        = "DCS_Hellman/OutboundAdvice/Pending";
    public static final String SH_OUTBOUND_ADVICE_PROCCESSED = "DCS_Hellman/OutboundAdvice/Proccessed";
    /** 4 Order Pick Pack confirmation Outbound packing result message Packing Confirmation Hellmann to SMME */
    public static final String SH_PACKING_CONFIRMATION_PENDING        = "DCS_Hellman/PackingConfirmation/Pending";
    public static final String SH_PACKING_CONFIRMATION_PROCCESSED = "DCS_Hellman/PackingConfirmation/Proccessed";
    /** 5 Order Dispatch confirmation GI Confirmation Message Dispath Confirmation Hellmann to SMME */
    public static final String SH_DISPATCH_CONFIRMATION_PENDING        = "DCS_Hellman/DispatchConfirmation/Pending";
    public static final String SH_DISPATCH_CONFIRMATION_PROCCESSED = "DCS_Hellman/DispatchConfirmation/Proccessed";
    /** 6 Stock Reconciliation Stock Reconfirmation Inventory Recon Hellmann to SMME */
    public static final String SH_INVENTORY_RECON_PENDING        = "DCS_Hellman/InventoryRecon/Pending";
    public static final String SH_INVENTORY_RECON_PROCCESSED = "DCS_Hellman/InventoryRecon/Proccessed";
    /** 7 Material Master Material Master Data Item Master SMME to Hellmann */
    public static final String SH_ITEM_MASTER_PENDING        = "DCS_Hellman/ItemMaster/Pending";
    public static final String SH_ITEM_MASTER_PROCCESSED = "DCS_Hellman/ItemMaster/Proccessed";
    public static final String SH_SALES_ITEM_ENQUIRY = "DCS_Hellman/SalesItemEnquiry/Processed";

    
}
