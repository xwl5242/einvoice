package com.ss.einvoice.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import sun.misc.BASE64Encoder;

import com.ss.einvoice.bean.DZFPKJ;
import com.ss.einvoice.bean.DZFPKJXmxx;

public class JsonUtils {

	/**
	 * 拼通用报文(json)
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static String getSendToTaxJson(String interfaceCode,String fpqqlsh,String fpdm,String fphm,DZFPKJ dzfpkj)
			throws Exception {
		String content = "";
		StringBuffer sb = new StringBuffer("");
		
		sb.append("{");
		sb.append("\"interface\": {");
		sb.append("\"globalInfo\": {");
		sb.append("\"appId\": \"").append(DZFPUtils.appid).append("\","); 
		sb.append("\"interfaceId\": \"\","); 
		sb.append("\"interfaceCode\": \"").append(interfaceCode).append("\","); 
		sb.append("\"requestCode\": \"DZFPQZ\",");
		sb.append("\"requestTime\": \"").append(new Date()).append("\","); 
		sb.append("\"responseCode\": \"Ds\","); 
		sb.append("\"dataExchangeId\": \"").append("DZFPQZ").append(interfaceCode).append(DZFPUtils.formatToDay()).append( DZFPUtils.randNineData()).append( "\"");
		sb.append("},"); 
		sb.append("\"returnStateInfo\": {");
		sb.append("\"returnCode\": \"\","); 
		sb.append("\"returnMessage\": \"\"");
		sb.append("},"); 
		sb.append("\"Data\": {");
		sb.append("\"dataDescription\": {");
		sb.append("\"zipCode\": \"0\"");
		sb.append("},"); 
		sb.append("\"content\": \"");
		if (interfaceCode.equals(DZFPUtils.dfxj1001)) {
			content = getUploadContent(dzfpkj);
		} 
		else if (interfaceCode.equals(DZFPUtils.dfxj1003)) {
			content = getRemainContent(DZFPUtils.nsrsbh);
		}
		else if (interfaceCode.equals(DZFPUtils.dfxj1004)) {
			content = getSearchContent(DZFPUtils.nsrsbh,fpqqlsh);
		}
		else if (interfaceCode.equals(DZFPUtils.dfxj1005)) {
			content = getDownloadAddrContent(DZFPUtils.nsrsbh, fpqqlsh, fpdm, fphm);
		}
		content  = content.replace("\r\n", "").replace("\n", "").replace("\r", "");//json的报文不允许有换行，base64会产生。因此此处做去换行处理。
		sb.append(content).append("\",");
		sb.append("\"contentKey\":\"");
		String contentMD5 = MyAES.MD5(content.getBytes("UTF-8"));
		String contentKey = MyAES.encryptBASE64(MyAES.encrypt(contentMD5.getBytes("UTF-8"), DZFPUtils.contentPassword)).replaceAll("\r\n", "").replaceAll("\n", "").replace("\r", "");
		sb.append(contentKey).append("\"");;
		sb.append("}");
		sb.append("}");
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * 根据加密上传发票内容报文（发票开具）
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getUploadContent(DZFPKJ kj) throws UnsupportedEncodingException {
		StringBuffer content = new StringBuffer("{");
		content.append("\"REQUEST_COMMON_FPKJ\": {");
		content.append("\"FPQQLSH\": \"" +toEmpty(kj.getFPQQLSH())+ "\",");
		content.append("\"BMB_BBH\": \"\",");
		content.append("\"ZSFS\": \""+toEmpty(kj.getZSFS())+"\",");
		content.append("\"KPLX\": \""+toEmpty(kj.getKPLX())+"\",");
		content.append("\"XSF_NSRSBH\": \""+toEmpty(kj.getXSF_NSRSBH())+"\",");
		content.append("\"XSF_MC\": \""+toEmpty(kj.getXSF_MC())+"\",");
		content.append("\"XSF_DZDH\": \""+toEmpty(kj.getXSF_DZDH())+"\",");
		content.append("\"XSF_YHZH\": \""+toEmpty(kj.getXSF_YHZH())+"\",");
		content.append("\"GMF_NSRSBH\": \""+toEmpty(kj.getGMF_NSRSBH())+"\",");
		content.append("\"GMF_MC\": \""+toEmpty(kj.getGMF_MC())+"\",");
		content.append("\"GMF_DZDH\": \""+toEmpty(kj.getGMF_DZDH())+"\",");
		content.append("\"GMF_YHZH\": \""+toEmpty(kj.getGMF_YHZH())+"\",");
		content.append("\"GMF_SJH\": \""+toEmpty(kj.getGMF_SJH())+"\",");
		content.append("\"GMF_DZYX\": \""+toEmpty(kj.getGMF_DZYX())+"\",");
		content.append("\"FPT_ZH\": \""+toEmpty(kj.getFPT_ZH())+"\",");
		content.append("\"WX_OPENID\": \""+toEmpty(kj.getWX_OPENID())+"\",");
		content.append("\"KPR\": \""+toEmpty(kj.getKPR())+"\",");
		content.append("\"SKR\": \""+toEmpty(kj.getSKR())+"\",");
		content.append("\"FHR\": \""+toEmpty(kj.getFHR())+"\",");
		content.append("\"YFP_DM\": \""+toEmpty(kj.getYFP_DM())+"\",");
		content.append("\"YFP_HM\": \""+toEmpty(kj.getYFP_HM())+"\",");
		content.append("\"JSHJ\": \""+toEmpty(kj.getJSHJ())+"\",");
		content.append("\"HJJE\": \""+toEmpty(kj.getHJJE())+"\",");
		content.append("\"HJSE\": \""+toEmpty(kj.getHJSE())+"\",");
		content.append("\"KCE\": \""+toEmpty(kj.getKCE())+"\",");
		content.append("\"BZ\": \""+toEmpty(kj.getBZ())+"\",");
		content.append("\"HYLX\": \""+toEmpty(kj.getHYLX())+"\",");
		content.append("\"BY1\": \""+toEmpty(kj.getBY1())+"\",");
		content.append("\"BY2\": \""+toEmpty(kj.getBY2())+"\",");
		content.append("\"BY3\": \""+toEmpty(kj.getBY3())+"\",");
		content.append("\"BY4\": \""+toEmpty(kj.getBY4())+"\",");
		content.append("\"BY5\": \""+toEmpty(kj.getBY5())+"\",");
		content.append("\"BY6\": \""+toEmpty(kj.getBY6())+"\",");
		content.append("\"BY7\": \""+toEmpty(kj.getBY7())+"\",");
		content.append("\"BY8\": \""+toEmpty(kj.getBY8())+"\",");
		content.append("\"BY9\": \""+toEmpty(kj.getBY9())+"\",");
		content.append("\"BY10\": \""+toEmpty(kj.getBY10())+"\",");
		content.append("\"WX_ORDER_ID\": \""+toEmpty(kj.getWX_ORDER_ID())+"\",");
		content.append("\"WX_APP_ID\": \""+toEmpty(kj.getWX_APP_ID())+"\",");
		content.append("\"ZFB_UID\": \""+toEmpty(kj.getZFB_UID())+"\",");
		content.append("\"COMMON_FPKJ_XMXXS\": {");
		content.append("\"COMMON_FPKJ_XMXX\": [");
		for(DZFPKJXmxx xx:kj.getCOMMON_FPKJ_XMXXS()){
			content.append("{\"FPHXZ\": \""+toEmpty(xx.getFPHXZ())+"\",");
			content.append("\"SPBM\": \""+toEmpty(xx.getSPBM())+"\",");
			content.append("\"ZXBM\": \""+toEmpty(xx.getZXBM())+"\",");
			content.append("\"YHZCBS\": \""+toEmpty(xx.getYHZCBS())+"\",");
			content.append("\"LSLBS\": \"\",");
			content.append("\"ZZSTSGL\": \""+toEmpty(xx.getZZSTSGL())+"\",");
			content.append("\"XMMC\": \""+toEmpty(xx.getXMMC())+"\",");
			content.append("\"GGXH\": \""+toEmpty(xx.getGGXH())+"\",");
			content.append("\"DW\": \""+toEmpty(xx.getDW())+"\",");
			content.append("\"XMSL\": \""+toEmpty(xx.getXMSL())+"\",");
			content.append("\"XMDJ\": \""+toEmpty(xx.getXMDJ())+"\",");
			content.append("\"XMJE\": \""+toEmpty(xx.getXMJE())+"\",");
			content.append("\"SL\": \""+toEmpty(xx.getSL())+"\",");
			content.append("\"SE\": \""+toEmpty(xx.getSE())+"\",");
			content.append("\"BY1\": \""+toEmpty(xx.getBY1())+"\",");
			content.append("\"BY2\": \""+toEmpty(xx.getBY2())+"\",");
			content.append("\"BY3\": \""+toEmpty(xx.getBY3())+"\",");
			content.append("\"BY4\": \""+toEmpty(xx.getBY4())+"\",");
			content.append("\"BY5\": \""+toEmpty(xx.getBY5())+"\"},");
		}
		String tm = content.substring(0, content.length()-1);
		content = new StringBuffer(tm);
		content.append("]}");
		content.append("}");
		content.append("}");
		return new BASE64Encoder().encodeBuffer(content.toString().getBytes("UTF-8"));
	}

	public static String toEmpty(String str){
		return "".equals(str)||null==str?"":str;
	}
	
	/**
	 * 获取加密查询报文内容(发票查询报文)
	 * 
	 * @param fpqqlsh 发票请求流水号（就是原先的订单号）
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getSearchContent(String nsrsbh ,String fpqqlsh)
			throws UnsupportedEncodingException {
		StringBuffer content = new StringBuffer("{");
		content.append("\"REQUEST_COMMON_FPCX\":{");
		content.append("\"FPQQLSH\":\"").append(fpqqlsh).append("\",");
		content.append("\"XSF_NSRSBH\":\"").append(nsrsbh).append("\"}}");
		return new BASE64Encoder().encodeBuffer(content.toString().getBytes("UTF-8"));
	}

	/**
	 * 获取加密查询报文内容(发票结余报文)
	 * 
	 * @param nsrsbh 纳税人识别号
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getRemainContent(String nsrsbh)
			throws UnsupportedEncodingException {
		StringBuffer content = new StringBuffer("{");
		content.append("\"REQUEST_COMMON_FPKCCX\":{");
		content.append("\"NSRSBH\":\"").append(nsrsbh).append("\",");
		content.append("\"DETAIL_FLAG\":\"0\"}}");
//		content.append("\"NSRSBH\":\"").append(nsrsbh).append("\"}}");
		return new BASE64Encoder().encodeBuffer(content.toString().getBytes("UTF-8"));
	}

	/**
	 * 获取加密查询报文内容(发票下载地址查询)
	 * @param nsrsbh 纳税人识别号
	 * @param fpqqlsh 发票请求流水号（就是原先的订单号）
	 * @param fpdm 发票代码
	 * @param fphm 发票号码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getDownloadAddrContent(String nsrsbh,String fpqqlsh,String fpdm,String fphm)
			throws UnsupportedEncodingException {
		StringBuffer content = new StringBuffer("{");
		content.append("\"REQUEST_COMMON_FPXZDZCX\":{");
		content.append("\"FPQQLSH\":\"").append(fpqqlsh).append("\",");
		content.append("\"NSRSBH\":\"").append(nsrsbh).append("\",");
		content.append("\"FP_DM\":\"").append(nsrsbh).append("\",");
		content.append("\"FP_HM\":\"").append(nsrsbh).append("\"}}");
		return new BASE64Encoder().encodeBuffer(content.toString().getBytes("UTF-8"));
	}
}
