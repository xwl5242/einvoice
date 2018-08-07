package com.ss.einvoice.bean;

/**
 * 电子发票-开具-项目详细
 * @author xlw
 *
 */
public class DZFPKJXmxx {

	private String FPHXZ;//发票行性质；0 正常行、1 折扣行、2 被折扣行；必填
	private String SPBM;//商品编码；税局下发的商品编码表中最末级节点的编码
	private String ZXBM;//自行编码；未填写商品编码时，须使用自行增加的项目名称，并填写该商品的编码至自行编码中
	private String YHZCBS;//优惠政策标识；0：不使用，1：使用
	private String LSLBS;//零税率标识；空：非零税率， 1：免税，2：不征收，3 普通零税率
	private String ZZSTSGL;//增值税特殊管理；若含有预售卡业务，税率为 0，零税率标示必须为不征税，优惠政策标示为 1，增值税特殊管理必须为不征税
	private String XMMC;//项目名称；如果为折扣行，商品名称须与被折扣行的商品名称相同，不能多行折扣；必填
	private String GGXH;//规格型号；
	private String DW;//单位；
	private String XMSL;//项目数量；最多保留 6 位小数，总长度包含小数点不能超过 12位
	private String XMDJ;//项目单价；不含税，最多保留6 位小数，总长度包含小数点不能超过 12 位(只有当 ZSFS 为 1时，此处填含税单价)
	private String XMJE;//项目金额；不含税，单位：元（最多保留 2 位小数）(只有当 ZSFS 为 1时，此处填含税金额)；必填
	private String SL;//税率；例 17%为 0.17。小数点后最末位不能为 零 ， 例 10% 为0.1。；必填
	private String SE;//税额；单位：元（最多保留 2 位小数）；必填
	private String BY1;//备用字段1；
	private String BY2;//备用字段2；
	private String BY3;//备用字段3；
	private String BY4;//备用字段4；
	private String BY5;//备用字段5；
	public DZFPKJXmxx(){}
	public String getFPHXZ() {
		return FPHXZ;
	}
	public void setFPHXZ(String fPHXZ) {
		FPHXZ = fPHXZ;
	}
	public String getSPBM() {
		return SPBM;
	}
	public void setSPBM(String sPBM) {
		SPBM = sPBM;
	}
	public String getZXBM() {
		return ZXBM;
	}
	public void setZXBM(String zXBM) {
		ZXBM = zXBM;
	}
	public String getYHZCBS() {
		return YHZCBS;
	}
	public void setYHZCBS(String yHZCBS) {
		YHZCBS = yHZCBS;
	}
	public String getLSLBS() {
		return LSLBS;
	}
	public void setLSLBS(String lSLBS) {
		LSLBS = lSLBS;
	}
	public String getZZSTSGL() {
		return ZZSTSGL;
	}
	public void setZZSTSGL(String zZSTSGL) {
		ZZSTSGL = zZSTSGL;
	}
	public String getXMMC() {
		return XMMC;
	}
	public void setXMMC(String xMMC) {
		XMMC = xMMC;
	}
	public String getGGXH() {
		return GGXH;
	}
	public void setGGXH(String gGXH) {
		GGXH = gGXH;
	}
	public String getDW() {
		return DW;
	}
	public void setDW(String dW) {
		DW = dW;
	}
	public String getXMSL() {
		return XMSL;
	}
	public void setXMSL(String xMSL) {
		XMSL = xMSL;
	}
	public String getXMDJ() {
		return XMDJ;
	}
	public void setXMDJ(String xMDJ) {
		XMDJ = xMDJ;
	}
	public String getXMJE() {
		return XMJE;
	}
	public void setXMJE(String xMJE) {
		XMJE = xMJE;
	}
	public String getSL() {
		return SL;
	}
	public void setSL(String sL) {
		SL = sL;
	}
	public String getSE() {
		return SE;
	}
	public void setSE(String sE) {
		SE = sE;
	}
	public String getBY1() {
		return BY1;
	}
	public void setBY1(String bY1) {
		BY1 = bY1;
	}
	public String getBY2() {
		return BY2;
	}
	public void setBY2(String bY2) {
		BY2 = bY2;
	}
	public String getBY3() {
		return BY3;
	}
	public void setBY3(String bY3) {
		BY3 = bY3;
	}
	public String getBY4() {
		return BY4;
	}
	public void setBY4(String bY4) {
		BY4 = bY4;
	}
	public String getBY5() {
		return BY5;
	}
	public void setBY5(String bY5) {
		BY5 = bY5;
	}
	@Override
	public String toString() {
		return "DZFPKjXmxx [FPHXZ=" + FPHXZ + ", SPBM=" + SPBM + ", ZXBM="
				+ ZXBM + ", YHZCBS=" + YHZCBS + ", LSLBS=" + LSLBS
				+ ", ZZSTSGL=" + ZZSTSGL + ", XMMC=" + XMMC + ", GGXH=" + GGXH
				+ ", DW=" + DW + ", XMSL=" + XMSL + ", XMDJ=" + XMDJ
				+ ", XMJE=" + XMJE + ", SL=" + SL + ", SE=" + SE + ", BY1="
				+ BY1 + ", BY2=" + BY2 + ", BY3=" + BY3 + ", BY4=" + BY4
				+ ", BY5=" + BY5 + "]";
	}
}