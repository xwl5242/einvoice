package com.ss.einvoice.bean;

import java.util.List;

public class DZFPKJ {

	private String FPQQLSH;//发票请求流水号；企业内部唯一请求，开票流水号，每个请求流水号只能开一次,流水号前面以公司名称前缀，必填
	private String BMB_BBH;//编码版本号；
	private String ZSFS;//征税方式；0：普通征税 1：减按计征 2：差额征税，必填
	private String KPLX;//开票类型；0-蓝字发票；1-红字发票，必填
	private String XSF_NSRSBH;//销售方纳税人识别号；如为非收购发票，此项必填，即开票方纳税人识别号；如为收购发票，且收票方为企业时此项必填
	private String XSF_MC;//销售方名称；如为非收购发票，此销售方指开票纳税人；如为收购发票，指销售方名称，例如烟叶农户；必填
	private String XSF_DZDH;//销售方地址、电话；如为非收购发票，此项必填，即开票方纳税人地址、电话；如为收购发票，此项为可选；必填
	private String XSF_YHZH;//销售方银行账号；
	private String GMF_NSRSBH;//购买方纳税人识别号；如为非收购发票，且收票方为企业时此项必填；如为收购发票，此项必填，即开票方纳税人识别号
	private String GMF_MC;//购买方名称；如为非收购发票，为购买方名称；如为收购发票，此项必填，即开票纳税人名称，例如 XX 烟草公司；必填
	private String GMF_DZDH;//购买方地址、电话；如为非收购发票，此项可选；如为收购发票，此项必填，即开票纳税人地址、电话
	private String GMF_YHZH;//购买方银行账号；
	private String GMF_SJH;//购买方手机号；可以多个，用逗号分开，用于接收和归集电子发票购买方手机号与电子邮箱不能同时为空如为非收购发票则为购买方手机号；如收购发票则为销售方手机号
	private String GMF_DZYX;//购买方电子邮箱；用于接收和归集电子发票,购买方手机号与电子邮箱不能同时为空如为非收购发票则为购买方电子邮箱；如为收购发票则为销货方电子邮箱
	private String FPT_ZH;//发票通账户；发 票 通 平 台（fapiao.com）注册账户名，用于接收和归集电子发票如为非收购发票则为购买方发票通平台账户；如为收购发票则为销货方发票通平台账户
	private String WX_OPENID;//微信 OPENID；用于用户微信公众号推送电子发票信息
	private String KPR;//开票人；必填
	private String SKR;//收款人；
	private String FHR;//复核人；
	private String YFP_DM;//原发票代码；红字发票时必须
	private String YFP_HM;//原发票号码；红字发票时必须
	private String JSHJ;//价税合计；单位：元（最多保留 2 位小数）；必填
	private String HJJE;//合计金额；不含税，单位：元（最多保留 2 位小数）；必填
	private String HJSE;//合计税额；单位：元（最多保留 2 位小数）；必填
	private String KCE;//扣除额；最多保留至小数点后 2 位，当 ZSFS 为2 时扣除额为必填项
	private String BZ;//备注；
	private String HYLX;//行业类型；0 商业、1 其它
	private String BY1;//备用字段1；
	private String BY2;//备用字段2；订单号
	private String BY3;//备用字段3；
	private String BY4;//备用字段4；
	private String BY5;//备用字段5；
	private String BY6;//备用字段6；
	private String BY7;//备用字段7；
	private String BY8;//备用字段8；
	private String BY9;//备用字段9；
	private String BY10;//备用字段10；
	private String WX_ORDER_ID;//微信用于预制卡券的唯一识别id；用于预制卡券的唯一识别 ID，例如：通过税号、门店号、小票号等 MD5 后的值
	private String WX_APP_ID;//商户所属微信公众号 APPID 或发票通公众号 APPID；如使用商户公众号拉起预制卡券则此内容需要传入,如采用发票通拉起预制卡券则为空
	private String ZFB_UID;//支付宝 UID；使用支付宝扫描开票时记录的用户UID
	private String TSPZ;//特殊票种标识；“00”不是“01”农产品销售“02”农产品收购；必填
	private String QJ_ORDER_ID;//全局唯一订单 ID；通过税号、内部唯一流水号等做 MD5后的值
	private List<DZFPKJXmxx> COMMON_FPKJ_XMXXS;//项目详细；
	public DZFPKJ(){}
	public String getFPQQLSH() {
		return FPQQLSH;
	}
	public void setFPQQLSH(String fPQQLSH) {
		FPQQLSH = fPQQLSH;
	}
	public String getBMB_BBH() {
		return BMB_BBH;
	}
	public void setBMB_BBH(String bMB_BBH) {
		BMB_BBH = bMB_BBH;
	}
	public String getZSFS() {
		return ZSFS;
	}
	public void setZSFS(String zSFS) {
		ZSFS = zSFS;
	}
	public String getKPLX() {
		return KPLX;
	}
	public void setKPLX(String kPLX) {
		KPLX = kPLX;
	}
	public String getXSF_NSRSBH() {
		return XSF_NSRSBH;
	}
	public void setXSF_NSRSBH(String xSF_NSRSBH) {
		XSF_NSRSBH = xSF_NSRSBH;
	}
	public String getXSF_MC() {
		return XSF_MC;
	}
	public void setXSF_MC(String xSF_MC) {
		XSF_MC = xSF_MC;
	}
	public String getXSF_DZDH() {
		return XSF_DZDH;
	}
	public void setXSF_DZDH(String xSF_DZDH) {
		XSF_DZDH = xSF_DZDH;
	}
	public String getXSF_YHZH() {
		return XSF_YHZH;
	}
	public void setXSF_YHZH(String xSF_YHZH) {
		XSF_YHZH = xSF_YHZH;
	}
	public String getGMF_NSRSBH() {
		return GMF_NSRSBH;
	}
	public void setGMF_NSRSBH(String gMF_NSRSBH) {
		GMF_NSRSBH = gMF_NSRSBH;
	}
	public String getGMF_MC() {
		return GMF_MC;
	}
	public void setGMF_MC(String gMF_MC) {
		GMF_MC = gMF_MC;
	}
	public String getGMF_DZDH() {
		return GMF_DZDH;
	}
	public void setGMF_DZDH(String gMF_DZDH) {
		GMF_DZDH = gMF_DZDH;
	}
	public String getGMF_YHZH() {
		return GMF_YHZH;
	}
	public void setGMF_YHZH(String gMF_YHZH) {
		GMF_YHZH = gMF_YHZH;
	}
	public String getGMF_SJH() {
		return GMF_SJH;
	}
	public void setGMF_SJH(String gMF_SJH) {
		GMF_SJH = gMF_SJH;
	}
	public String getGMF_DZYX() {
		return GMF_DZYX;
	}
	public void setGMF_DZYX(String gMF_DZYX) {
		GMF_DZYX = gMF_DZYX;
	}
	public String getFPT_ZH() {
		return FPT_ZH;
	}
	public void setFPT_ZH(String fPT_ZH) {
		FPT_ZH = fPT_ZH;
	}
	public String getWX_OPENID() {
		return WX_OPENID;
	}
	public void setWX_OPENID(String wX_OPENID) {
		WX_OPENID = wX_OPENID;
	}
	public String getKPR() {
		return KPR;
	}
	public void setKPR(String kPR) {
		KPR = kPR;
	}
	public String getSKR() {
		return SKR;
	}
	public void setSKR(String sKR) {
		SKR = sKR;
	}
	public String getFHR() {
		return FHR;
	}
	public void setFHR(String fHR) {
		FHR = fHR;
	}
	public String getYFP_DM() {
		return YFP_DM;
	}
	public void setYFP_DM(String yFP_DM) {
		YFP_DM = yFP_DM;
	}
	public String getYFP_HM() {
		return YFP_HM;
	}
	public void setYFP_HM(String yFP_HM) {
		YFP_HM = yFP_HM;
	}
	public String getJSHJ() {
		return JSHJ;
	}
	public void setJSHJ(String jSHJ) {
		JSHJ = jSHJ;
	}
	public String getHJJE() {
		return HJJE;
	}
	public void setHJJE(String hJJE) {
		HJJE = hJJE;
	}
	public String getHJSE() {
		return HJSE;
	}
	public void setHJSE(String hJSE) {
		HJSE = hJSE;
	}
	public String getKCE() {
		return KCE;
	}
	public void setKCE(String kCE) {
		KCE = kCE;
	}
	public String getBZ() {
		return BZ;
	}
	public void setBZ(String bZ) {
		BZ = bZ;
	}
	public String getHYLX() {
		return HYLX;
	}
	public void setHYLX(String hYLX) {
		HYLX = hYLX;
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
	public String getBY6() {
		return BY6;
	}
	public void setBY6(String bY6) {
		BY6 = bY6;
	}
	public String getBY7() {
		return BY7;
	}
	public void setBY7(String bY7) {
		BY7 = bY7;
	}
	public String getBY8() {
		return BY8;
	}
	public void setBY8(String bY8) {
		BY8 = bY8;
	}
	public String getBY9() {
		return BY9;
	}
	public void setBY9(String bY9) {
		BY9 = bY9;
	}
	public String getBY10() {
		return BY10;
	}
	public void setBY10(String bY10) {
		BY10 = bY10;
	}
	public String getWX_ORDER_ID() {
		return WX_ORDER_ID;
	}
	public void setWX_ORDER_ID(String wX_ORDER_ID) {
		WX_ORDER_ID = wX_ORDER_ID;
	}
	public String getWX_APP_ID() {
		return WX_APP_ID;
	}
	public void setWX_APP_ID(String wX_APP_ID) {
		WX_APP_ID = wX_APP_ID;
	}
	public String getZFB_UID() {
		return ZFB_UID;
	}
	public void setZFB_UID(String zFB_UID) {
		ZFB_UID = zFB_UID;
	}
	public String getTSPZ() {
		return TSPZ;
	}
	public void setTSPZ(String tSPZ) {
		TSPZ = tSPZ;
	}
	public String getQJ_ORDER_ID() {
		return QJ_ORDER_ID;
	}
	public void setQJ_ORDER_ID(String qJ_ORDER_ID) {
		QJ_ORDER_ID = qJ_ORDER_ID;
	}
	
	public List<DZFPKJXmxx> getCOMMON_FPKJ_XMXXS() {
		return COMMON_FPKJ_XMXXS;
	}
	public void setCOMMON_FPKJ_XMXXS(List<DZFPKJXmxx> cOMMON_FPKJ_XMXXS) {
		COMMON_FPKJ_XMXXS = cOMMON_FPKJ_XMXXS;
	}
	@Override
	public String toString() {
		return "DZFPKJ [FPQQLSH=" + FPQQLSH + ", BMB_BBH=" + BMB_BBH
				+ ", ZSFS=" + ZSFS + ", KPLX=" + KPLX + ", XSF_NSRSBH="
				+ XSF_NSRSBH + ", XSF_MC=" + XSF_MC + ", XSF_DZDH=" + XSF_DZDH
				+ ", XSF_YHZH=" + XSF_YHZH + ", GMF_NSRSBH=" + GMF_NSRSBH
				+ ", GMF_MC=" + GMF_MC + ", GMF_DZDH=" + GMF_DZDH
				+ ", GMF_YHZH=" + GMF_YHZH + ", GMF_SJH=" + GMF_SJH
				+ ", GMF_DZYX=" + GMF_DZYX + ", FPT_ZH=" + FPT_ZH
				+ ", WX_OPENID=" + WX_OPENID + ", KPR=" + KPR + ", SKR=" + SKR
				+ ", FHR=" + FHR + ", YFP_DM=" + YFP_DM + ", YFP_HM=" + YFP_HM
				+ ", JSHJ=" + JSHJ + ", HJJE=" + HJJE + ", HJSE=" + HJSE
				+ ", KCE=" + KCE + ", BZ=" + BZ + ", HYLX=" + HYLX + ", BY1="
				+ BY1 + ", BY2=" + BY2 + ", BY3=" + BY3 + ", BY4=" + BY4
				+ ", BY5=" + BY5 + ", BY6=" + BY6 + ", BY7=" + BY7 + ", BY8="
				+ BY8 + ", BY9=" + BY9 + ", BY10=" + BY10 + ", WX_ORDER_ID="
				+ WX_ORDER_ID + ", WX_APP_ID=" + WX_APP_ID + ", ZFB_UID="
				+ ZFB_UID + ", TSPZ=" + TSPZ + ", QJ_ORDER_ID=" + QJ_ORDER_ID
				+ ", COMMON_FPKJ_XMXXS=" + COMMON_FPKJ_XMXXS + "]";
	}
}
