package com.ss.einvoice.bean;

public class DZFPJY {

	private String UNDISTRIBUTED_NUM;
	private String SUM_NUM;
	public String getUNDISTRIBUTED_NUM() {
		return UNDISTRIBUTED_NUM;
	}
	public void setUNDISTRIBUTED_NUM(String uNDISTRIBUTED_NUM) {
		UNDISTRIBUTED_NUM = uNDISTRIBUTED_NUM;
	}
	public String getSUM_NUM() {
		return SUM_NUM;
	}
	public void setSUM_NUM(String sUM_NUM) {
		SUM_NUM = sUM_NUM;
	}
	@Override
	public String toString() {
		return "DZFPJY [UNDISTRIBUTED_NUM=" + UNDISTRIBUTED_NUM + ", SUM_NUM="
				+ SUM_NUM + "]";
	}
	
}
