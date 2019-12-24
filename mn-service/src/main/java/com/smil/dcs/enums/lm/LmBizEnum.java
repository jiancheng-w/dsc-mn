/*
 * Copyright (C), 2013-2018, 上海赛可电子商务有限公司
 * FileName: McDicEnum.java
 * Author:   chenliang
 * Date:     2018年12月21日 下午1:05:00
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.smil.dcs.enums.lm;

/**
 * 商品字典枚举类<br>
 * .
 *
 * @author chenliang
 */
public class LmBizEnum {

	/** 运输方式. */
	public enum TransModeEnum {

	/** 采购(purchase)运输方式: inBound. */
	PURCHASE(new Byte("1"), "采购运输方式: inBound"),

	/** 销售(sales)运输方式: outBound. */
	SALES(new Byte("2"), "销售运输方式: outBound");

		/** The code. */
		private Byte code;

		/** The value. */
		private String value;

		/**
		 * Instantiates a new trans mode enum.
		 *
		 * @param code  the code
		 * @param value the value
		 */
		TransModeEnum(Byte code, String value) {
			this.code = code;
			this.value = value;
		}

		/**
		 * Gets the code.
		 *
		 * @return the code
		 */
		public Byte getCode() {
			return code;
		}

		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		public String getValue() {
			return value;
		}
	}


	/**
	 * Inbound 物流节点
	 */
	public enum InboundLogLineEnum {

		SUBSIDIARY_ORDER_PROCESS("Subsidiary_Order_Process", 1),
		SMIL_ORDER_PROCESSING("SMIL_ORDER_PROCESSING", 2),
		OEM_CUTOFF("OEM_Cutoff", 3),
		SMIL_PROCESSING("SMIL_Processing", 4),
		OVERSEA_TRANSPORTATION("Oversea_Transportation", 5),
		LOCAL_CUSTOMS_DECLARATION("Local_Customs_Declaration", 6),
		LOCAL_TRANSPORTATION("Local_Transportation", 7),
		WAREHOUSE_INBOUND("Warehouse_Inbound", 8);

		/** The code. */
		private String no;

		/** The value. */
		private int value;

		InboundLogLineEnum(String no, int value) {
			this.no = no;
			this.value = value;
		}

		public String getNo() {
			return no;
		}

		public int getValue() {
			return value;
		}
	}


	/** 物流状态 */
	public enum LogStatusEnum {

		/** CANCLE  0.已取消*/
		CANCLE("CANCLE", new Byte("0")),

		/** BUILD  1.已创建*/
		BUILD("BUILD", new Byte("1")),

		/** INVOICE 2.已开票*/
		INVOICE("INVOICE", new Byte("2")),

		/** NOTICE 3.已通知*/
		NOTICE("NOTICE", new Byte("3")),

		/** RECEIVED  4.已收货*/
		RECEIVED("RECEIVED", new Byte("4")),

		/** INBOUND 5.已入库*/
		INBOUND("INBOUND", new Byte("5")),

		/** SETTLEMENT.6.已结算 */
		SETTLEMENT("SETTLEMENT", new Byte("6"));

		/** The code. */
		private String code;

		/** The value. */
		private Byte value;

		/**
		 * Instantiates a new logistics node enum
		 *
		 * @param code  the code
		 * @param value the value
		 * @return
		 */
		LogStatusEnum(String code, Byte value) {
			this.code = code;
			this.value = value;
		}

		/**
		 * Gets the code.
		 *
		 * @return the code
		 */
		public String getCode() {
			return code;
		}

		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		public Byte getValue() {
			return value;
		}
	}

	/** BO状态. */
	public enum BOStatusEnum {

		BO("1", "BO"),
		CANCELLED("0","Cancelled");

		private String code;

		private String value;

		BOStatusEnum(String code, String value) {
			this.code = code;
			this.value = value;
		}

		public String getCode() {
			return code;
		}

		public String getValue() {
			return value;
		}
	}

	/** 开票通知客户数据枚举. */
	public enum NoticeCiDataEnum {

		CoNo("CoNo", "采购公司编码"),
		VendorNo("VendorNo","供应商编码"),
		VendorName("VendorName","供应商名称");

		private String code;

		private String value;

		NoticeCiDataEnum(String code, String value) {
			this.code = code;
			this.value = value;
		}

		public String getCode() {
			return code;
		}

		public String getValue() {
			return value;
		}
	}

	/** 物流运输方式枚举. */
	public enum LmBizNoEnum {

		Sea("1", "Sea"),
		Air("2","Air"),
		Land("3","Land"),
		Express("4","Express");

		private String code;

		private String value;

		LmBizNoEnum(String code, String value) {
			this.code = code;
			this.value = value;
		}

		public String getCode() {
			return code;
		}

		public String getValue() {
			return value;
		}
	}

	/** 销售状态枚举. 状态(0.已取消1.已创建2.已装箱3.已开票4.已发货5.已结算) */
	public enum SaleStatusEnum {

		CANCLE(new Byte("0"), "已取消"),
		BUILD(new Byte("1"),"已创建"),
		PKL(new Byte("2"),"已装箱"),
		CI(new Byte("3"),"已开票"),
		SHIPPED(new Byte("4"),"已发货"),
		SETTLED(new Byte("5"),"已结算");

		private Byte code;

		private String value;

		SaleStatusEnum(Byte code, String value) {
			this.code = code;
			this.value = value;
		}

		public Byte getCode() {
			return code;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 出库跟踪状态枚举
	 */
	public enum LmOutboundTrackingDataStatusEnum {

		NOT_FOUND((byte)-99,""),				//NOT_FOUND
		NON_PACK((byte)-1, "Non-Pack"),		//Non-Pack
		CANCELlED((byte)0, "CANCELlED"),		//已取消
		CREATED((byte)1, "CREATED"),			//已创建
		PKL_CI((byte)2, "PKL_CI"),			//已装箱开票
		SHIPPED((byte)3, "SHIPPED"),			//已发货
		DELIVERED((byte)4, "DELIVERED");		//已交付

		private Byte code;
		private String value;

		LmOutboundTrackingDataStatusEnum(Byte code, String value) {
			this.code = code;
			this.value = value;
		}

		/**
		 * 功能描述: <br>
		 * 根据value获取枚举.
		 *
		 * @param code
		 * @return code对应的枚举, 未找到返回NOT_FOUND
		 */
		public static LmOutboundTrackingDataStatusEnum getEnum(Byte code) {
			LmOutboundTrackingDataStatusEnum[] values = LmOutboundTrackingDataStatusEnum.values();
			for (LmOutboundTrackingDataStatusEnum lmOutboundTrackingDataStatusEnum : values) {
				if (lmOutboundTrackingDataStatusEnum.getCode().equals(code)) {
					return lmOutboundTrackingDataStatusEnum;
				}
			}
			return NOT_FOUND;
		}

		public Byte getCode() {
			return code;
		}

		public String getValue() {
			return value;
		}
	}

}
