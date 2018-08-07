package com.ss.einvoice.bean;

public class DZFPResponseBody {

	public String FPQQLSH;//发票请求流水号
	public String FP_DM;//发票代码
	public String FP_HM;//发票号码
	public String KPRQ;//开票日期
	public String JYM;//校验码
	public String PDF_URL;//PDF下载地址
	public String SP_URL;//收票地址
	public String getFPQQLSH() {
		return FPQQLSH;
	}
	public void setFPQQLSH(String fPQQLSH) {
		FPQQLSH = fPQQLSH;
	}
	public String getFP_DM() {
		return FP_DM;
	}
	public void setFP_DM(String fP_DM) {
		FP_DM = fP_DM;
	}
	public String getFP_HM() {
		return FP_HM;
	}
	public void setFP_HM(String fP_HM) {
		FP_HM = fP_HM;
	}
	public String getKPRQ() {
		return KPRQ;
	}
	public void setKPRQ(String kPRQ) {
		KPRQ = kPRQ;
	}
	public String getJYM() {
		return JYM;
	}
	public void setJYM(String jYM) {
		JYM = jYM;
	}
	public String getPDF_URL() {
		return PDF_URL;
	}
	public void setPDF_URL(String pDF_URL) {
		PDF_URL = pDF_URL;
	}
	public String getSP_URL() {
		return SP_URL;
	}
	public void setSP_URL(String sP_URL) {
		SP_URL = sP_URL;
	}
	@Override
	public String toString() {
		return "DZFPResponseBody [FPQQLSH=" + FPQQLSH + ", FP_DM=" + FP_DM
				+ ", FP_HM=" + FP_HM + ", KPRQ=" + KPRQ + ", JYM=" + JYM
				+ ", PDF_URL=" + PDF_URL + ", SP_URL=" + SP_URL + "]";
	}
	
}
