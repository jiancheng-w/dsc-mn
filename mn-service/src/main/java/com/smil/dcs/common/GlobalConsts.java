/*
 * Copyright (C), 2013-2018, 上海赛可电子商务有限公司
 * FileName: GlobalConsts.java
 * Author:   chenliang
 * Date:     2018年11月21日 下午6:23:49
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.smil.dcs.common;

/**
 * 常量<br>
 * .
 *
 * @author chenliang
 */
public class GlobalConsts {


    /** ftp文件的路径*/
    public static final String FTP_PATH = "/DCS/SME/Price/Pending/";

    /** ftp文件move路径 */
    public static final String FTP_PROCESSED_PATH = "/DCS/SME/Price/Processed/";

    /** ftp po文件待处理路径 */
    public static final String FTP_PO_PENDING_PATH = "/DCS/SME/Purchasing_Order/Pending/";

    /** ftp po文件已处理路径 */
    public static final String FTP_PO_PROCESSED_PATH = "/DCS/SME/Purchasing_Order/Processed/";

    /** ftp po反馈文件待处理路径 */
    public static final String FTP_PO_FEEDBACK_PENDING_PATH = "/DCS/SME/PO_Feedback/Pending/";

    /** ftp po反馈文件已处理路径 */
    public static final String FTP_PO_FEEDBACK_PROCESSED_PATH = "/DCS/SME/PO_Feedback/Processed/";

    /** ftp po状态文件待处理路径 */
    public static final String FTP_PO_STATUS_PENDING_PATH = "/DCS/SME/PO_Status/Pending/";

    /** ftp po状态文件已处理路径 */
    public static final String FTP_PO_STATUS_PROCESSED_PATH = "/DCS/SME/PO_Status/Processed/";
    
    /** ftp HS CODE待处理路径 */
	public static final String FTP_HS_CODE_PENDING_PATH = "/DCS/SME/HSCode/Pending/";

    /** ftp po 反馈成功字符串 */
    public static final String FTP_PO_FEEDBACK_SUCCESS = "SUCCESS";

    /** ftp po 反馈错误字符串 */
    public static final String FTP_PO_FEEDBACK_ERROR = "ERROR";

    /** ftp po 审核通过字符串 */
    public static final String FTP_PO_STATUS_APPROVED = "Approved";

    /** ftp po 审核拒绝字符串 */
    public static final String FTP_PO_STATUS_REJECT = "Reject";

    /** ftp po 审核拒绝备注信息 */
    public static final String FTP_PO_STATUS_REJECTED_REMARK = "GDS Rejected";

    /** ftp po 审核通过备注信息 */
    public static final String FTP_PO_STATUS_APPROVED_REMARK = "GDS Approved";

    public static final String FTP_PO_STATUS_RECEIVED_REMARK = "GDS Received";

    /**  发运信息文件路径 */
    public static final String FTP_OUTBOUND_DELIVERY_PENDING_PATH = "/DCS/SME/Outbound_Delivery/Pending/";
    /** 发运信息文件move路径 */
    public static final String FTP_OUTBOUND_DELIVERY_PROCESSED_PATH = "/DCS/SME/Outbound_Delivery/Processed/";
    public static final String FTP_OUTBOUND_DELIVERY_PREFIX = "Delivery";
    public static final String FTP_OUTBOUND_DELIVERY_HEAD_PREFIX = "Delivery_Head";
    public static final String FTP_OUTBOUND_DELIVERY_DETAIL_PREFIX = "Delivery_Detail";
    public static final String FTP_OUTBOUND_DELIVERY_LOCAL_PATH = "lmFtp_1";

    /** 发运文件bo数据 */
    public static final String FTP_OUTBOUND_BO_PENDING_PATH = "/DCS/SME/Back_Order/Pending/";
    public static final String FTP_OUTBOUND_BO_PROCESSED_PATH = "/DCS/SME/Back_Order/Processed/";
    public static final String FTP_OUTBOUND_BO_PREFIX = "BO";
    public static final String FTP_OUTBOUND_LOCALBO_PATH = "lmFtpBo_1";

    /** 本地ftp目录 */
    public static final String LOCLAL_FTP_PATH = "mdseFtp_1";

    /** ftp文件名前缀 */
    public static final String FTP_PREX = "Parts";

    /** ftp价格文件名前缀 */
    public static final String FTP_MDSE_PRICE_PREX = "Parts_Fob_Price";

    /** ftp费率文件名前缀 */
    public static final String FTP_MDSE_MARGIN_PREX = "Parts_Cif_Param";

    /** 读取商品价格船税费： 运输方式 */
    public static final String FTP_TRANSPORTATION= "YS001";

    /** 读取商品价格船税费： 贸易条款 */
    public static final String FTP_TRADING_TERM = "CIF";

    /** 读取商品价格船税费：FTP交易类型 */
    public static final String FTP_PART_TYPE = "0";

    /** ftp GBK编码 */
    public static final String FTP_CHARSET_NAME_GBK = "GBK";

    /** SMIL供应商编码 */
    public static final String SMILE_VENDOR_NO = "2400011";

    /** 系统用户 */
    public static final String SYSTME_ADMIN = "systemadmin";

    public static final String SYSTME_ALP = "systemALP";

    /** SMIL ORG_CODE */
    public static final String SMILE_ORG_CODE = "SMIL";

    /** DT CODE */
    public static final String DT_CODE = "220095";

    /**默认货币类型 USD */
    public static final String DEFAULT_CURRENCY ="USD";

    /**orderType*/
    public static final String ORDER_TYPE = "ORDER_TYPE";

    /**UOM*/
    public static final String UOM = "UOM";

    /**PKS*/
    public static final String PKS = "PKS";

    /** 商品默认单位 PCS  - EA*/
    public static final String MDSE_DEFAULT_UOM = "PCS";

    /**if_margin*/
    public static final String IFMARGIN = "IF_MARGIN";

    /**SMIL 商品更新起始时间 TYPE*/
    public static final String RLMDSETM = "RLMDSETM";

    /**SMIL 商品更新起始时间 START*/
    public static final String RLMDSETM_STARTTIME = "STARTTIME";

    /**SMIL 商品更新起始时间 END*/
    public static final String RLMDSETM_ENDTIME = "ENDTIME";

    /**SMIL 商品更新起始时间 STATUS*/
    public static final String RLMDSETM_STATUS = "STATUS";

    /**SMIL 商品更新全量起始时间 */
    public static final String SYNC_MDSE_START_TIME = "1960-01-01 12:00:00";

    /**保险费系数*/
    public static final String INSURANCE = "INSURANCE";

    /**危险品*/
    public static final String DANGEROUS = "Dangerous";

    /**非危险品*/
    public static final String NON_DANGEROUS = "Non-Dangerous";

    /**运费系数*/
    public static final String FREIGHT = "FREIGHT";

    /**isDeleted*/
    public static final String IS_DELETED = "isDeleted";

    /** 采购商群组编码 */
    public static final String PUR_PRICE_GROUP_NO = "ALL";

    /** 采购商群组名称 */
    public static final String PUR_PRICE_GROUP_NM = "ALL";

    /** 默认仓库id */
    public static final Long DEFAULT_WAREHOUSEID = 1l;

    /** BYTE -2 */
    public static final Byte BYTE_MINUS_TWO = -2;

    /** BYTE -1 */
    public static final Byte BYTE_MINUS_ONE = -1;

    /** BYTE 0 */
    public static final Byte BYTE_ZERO = 0;

    /** BYTE 1 */
    public static final Byte BYTE_ONE = 1;

    /** BYTE 2 */
    public static final Byte BYTE_TWO = 2;

    /** BYTE 3 */
    public static final Byte BYTE_THREE = 3;

    /** BYTE 4 */
    public static final Byte BYTE_FOUR = 4;

    /** BYTE 5 */
    public static final Byte BYTE_FIVE = 5;

    /** BYTE 6 */
    public static final Byte BYTE_SIX = 6;

    /** BYTE 7 */
    public static final Byte BYTE_SEVEN = 7;

    /** BYTE 8 */
    public static final Byte BYTE_EIGHT = 8;

    public static final Byte Y = 1;

    public static final Byte N = 0;

    public static final String STR_Y = "Y";

    public static final String STR_N = "N";


    public static final Long DEFAULTVERSION = 1L;

    public static final Byte SMIL = 1;

    public static final Byte LOCAL = 2;

    // 下划线
    public static final String UNDERLINE = "_";

    // 豆点
    public static final String DOT = ".";

    // 空字符串
    public static final String BLANK_STRING = "";

    // CSV文件扩展名
    public static final String FILE_EXTENSION_NAME_CSV = "csv";

    public static final String CSV_SEPARATOR = ",";

    // 时间格式1
    public static final String DATE_FORMAT_1 = "yyyyMMdd_HHmmss";

    // 时间格式2
    public static final String DATE_FORMAT_2 = "yyyyMMdd";

    // 服务器临时储存根目录
    public static final String STORAGE_PATH = "storage/sme/";

    public static final String STR_SMIL = "SMIL";

    public static final String STR_LOCAL = "LOCAL";

    /** 触发频次(PM.每月PY.每年) */
    public static final String TRIGGERFREQ_M = "PM";

    /** 触发频次(PM.每月PY.每年) */
    public static final String TRIGGERFREQ_Y = "PY";

    public static final String ERROR = "Error";

    public static final String NORMAL = "Normal";

    /** ROOT CATANO */
    public static final String ROOT_SALE_CATE = "000000";

    /** DATA FOREVER */
    public static final String DATA_FOREVER = "2199-01-01";

    public static final String DATE_FORMAT_STR_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";


    /** 大类加价率类型 */
    public static final String BIG_MARGIN_TYPE = "1";

    /** 小类加价率类型 */
    public static final String SMALL_MARGIN_TYPE = "2";

    /** 商品加价率类型 */
    public static final String MDSE_MARGIN_TYPE = "3";

    /** 修改基准价按钮 */
    public static final Integer UPD_PRICE_BUTTON = 1;

    /** 修改加价率按钮 */
    public static final Integer UPD_MARGIN_BUTTON = 2;

    /** 开始页 */
    public static final int PAGE_NUM = 1;

    /** 开始页 */
    public static final int PAGE_SIZE = 50;

    /** 后付费用户支付方式名 */
    public static final String TT_PAYMENT_METHOD = "T/T";

    /** 形式发票类型 */
    public static final Byte PI_TYPE = BYTE_TWO;

    /** 商业发票类型 */
    public static final Byte CI_TYPE = BYTE_ONE;

    /** 缺省客户名称 */
    public static final String DEFAULT_CUSTOMER_NAME = "No Customer Name";

    /**保留2位xiaoshu*/
    public static final int NEW_SCALE = 2;

    /**查商品加价率*/
    public static final Integer CONTAIN_MARGIN = 1;

    /**分批限制条数*/
    public static final Integer LIMIT_COUNT = 500;

    /** SAP语言代码 */
    public static final String SAP_LANGUAGE = "E";

    /** SAP中东公司代码 */
    public static final String SAP_SMME_COMPANY_CODE = "";

    /** SAP组织代码 */
    public static final String SAP_SMME_ORG_CODE = "";

    /** SAP SPART */
    public static final String SAP_SPART= "";

    /** SAP VTWEG */
    public static final String SAP_VTWEG= "";

    public static final String DAMAGECODE = "Q";

    public static final Byte BYTE_DAMAGE = 0;

    public static final Byte BYTE_NORMAL = 1;

    public static final Byte BYTE_CANCEL = 0;

    public static final Byte BYTE_CREATE = 1;

    public static final Byte BYTE_INBOUND = 2;

    public static final Byte BYTE_OUTBOUND = 2;

    public static final Integer DEFAULTQTY = 0;
    
    //装箱确认文件地址
    public static final String PACKING_CONFIRMATION_FILEPATH = "packing";
    //收货确认文件地址
    public static final String INBOUND_CONFIRMATION_FILEPATH = "inbound";
    //发货确认文件地址
    public static final String DISPATCH_CONFIRMATION_FILEPATH = "dispatch";

    public static final String CSV_SPLIT_SPLITCHARACTER = ";";

    public static final String CSV_RECORD_TYPE_H = "H";

    public static final String CSV_RECORD_TYPE_P = "P";

    /** 货物名称长度 1 */
    public static final Integer GOODS_NAME_FIR = 30;

    /** 货物名称长度 2 */
    public static final Integer GOODS_NAME_SEC = 60;

    /** 货物名称长度 3 */
    public static final Integer GOODS_NAME_THIR = 90;
    
    public static final String FILE_BIZ_TYPE_WMSASN="wmsAsn";
    
    public static final String WMS_ASN_FILE_FOLDER="ASN_";

    public static final String WMS_ITEM_MM="MM";
    public static final String WMS_OUTBOUND_DN="DN";

    /** 公司代码 */
    public static final String COMPANY_CODE_SMIL = "5000";
    public static final String COMPANY_CODE_SMME = "6000";
    public static final String COMPANY_CODE_SME = "9000";

    /** 未设置字典值提示语 */
    public static final String UNSET_DICTIONARY_VALUE = "UNSET DICTIONARY VALUE";

    /** 用于信用账户判定 */
    public static final String PAYMENT_METHOD_NO = "K";

    public static final String GMT8TimeZoneId = "GMT+8:00";

    public static final Integer LISTSIZE = 200;
    
    /** The Constant DIS_LOCK. */
    public static final String DIST_LOCK = "DIST_LOCK";

    /** The Constant DN_BILLING_LOCK. */
    public static final String DN_BILLING_LOCK = "DN_BILLING_LOCK";

    /** 单据明细编码长度. */
    public static final Integer ITEM_LENGTH = 6;

    public static final String SEPARATOR = "/";

    public static final String DEV_ENVIRONMENT_PROFILE_NAME = "dev";

    /** SME组织代码 */
    public static final String SME_ORG_CODE = "420004";

    /** SME APP CODE */
    public static final String SME_APP_CODE = "DCS_SME";

    //小数点位数
    public static final int ROUNDING_PRE = 8;

    public static final String ROLE_MENU_SME_DHL = "MENU_SME_DHL";

    /** 用户角色 */
    public static final String DCS_SME_APPROVER_ROLE_NAME = "MENU_SME_APPROVER";
    public static final String DCS_SME_FINANCE_ROLE_NAME = "MENU_SME_FINANCE";
    public static final String DCS_SME_OPERATOR_ROLE_NAME = "MENU_SME_OPERATOR";
    public static final String DCS_DHL_ROLE_NAME = "MENU_SME_DHL";
    public static final String DCS_DT_ROLE_NAME = "MENU_SME_DT";

    //mdse_status
    public static final Byte MDSE_STATUS_NOT_PURCHASABLE = 0;
    public static final Byte MDSE_STATUS_PURCHASABLE = 1;

    //sql partition size
    public static final int SQL_PARTITION_SIZE = 500;
}
