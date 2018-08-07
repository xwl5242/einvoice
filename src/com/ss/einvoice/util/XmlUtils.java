package com.ss.einvoice.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import sun.misc.BASE64Encoder;

import com.ss.einvoice.bean.DZFPKJ;
import com.ss.einvoice.bean.DZFPKJXmxx;

public class XmlUtils {
	
	/**
	 * 拼通用报文(xml)
	 * 
	 * @return
	 * @throws Exception 
	 */

	public static String getSendToTaxXML(String interfaceCode,String fpqqlsh,String fpdm,String fphm,DZFPKJ dzfpkj)
			throws Exception {
		String content = "";
		StringBuffer sb = new StringBuffer("");
		sb.append("<?xml version='1.0' encoding='UTF-8' ?>");
		sb.append("<interface xmlns=\"\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:schemaLocation=\"http://www.chinatax.gov.cn/tirip/dataspec/interfaces.xsd\" version=\"DZFPQZ0.2\"> ");
		sb.append("<globalInfo>");
		sb.append("<appId>").append(DZFPUtils.appid).append("</appId>");
		sb.append("<interfaceId></interfaceId>");
		sb.append("<interfaceCode>").append(interfaceCode).append("</interfaceCode>");
		sb.append("<requestCode>DZFPQZ</requestCode>");
		sb.append("<requestTime>").append(new Date()).append("</requestTime>");
		sb.append("<responseCode>Ds</responseCode>");
		sb.append("<dataExchangeId>").append("DZFPQZ").append(interfaceCode).append(DZFPUtils.formatToDay()).append(DZFPUtils.randNineData()).append("</dataExchangeId>");
		sb.append("</globalInfo>");
		sb.append("<returnStateInfo>");
		sb.append("<returnCode></returnCode>");
		sb.append("<returnMessage></returnMessage>");
		sb.append("</returnStateInfo>");
		sb.append("<Data>");
		sb.append("<dataDescription>");
		sb.append("<zipCode>0</zipCode>");
		sb.append("</dataDescription>");
		sb.append("<content>");
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
		content = content.replaceAll("\r\n", "").replaceAll("\n", "");//去掉空格和换行
		sb.append(content);
		sb.append("</content>");
		sb.append("<contentKey>");
		String contentMD5 = MyAES.MD5(content.getBytes("UTF-8"));//对content数据进行MD5加密
		String contentKey = MyAES.encryptBASE64(MyAES.encrypt(contentMD5.getBytes("UTF-8"), DZFPUtils.contentPassword)).replaceAll("\r\n", "").replaceAll("\n", "");//对md5后的数据进行AES加密
		sb.append(contentKey);
		sb.append("</contentKey>");
		sb.append("</Data>");
		sb.append("</interface>");
		return sb.toString();
	}
	
	/**
	 * 根据加密上传发票内容报文（发票开具）
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getUploadContent(DZFPKJ kj) throws UnsupportedEncodingException {
		StringBuffer content = new StringBuffer("");
		content.append("<REQUEST_COMMON_FPKJ class='REQUEST_COMMON_FPKJ'> ");
		content.append("<FPQQLSH>" +toEmpty(kj.getFPQQLSH())+ "</FPQQLSH>");
		content.append("<KPLX>"+toEmpty(kj.getKPLX())+"</KPLX>");
		content.append("<ZSFS>"+toEmpty(kj.getZSFS())+"</ZSFS>");
		content.append("<XSF_NSRSBH>"+toEmpty(kj.getXSF_NSRSBH())+"</XSF_NSRSBH>");
		content.append("<XSF_MC>"+toEmpty(kj.getXSF_MC())+"</XSF_MC>");
		content.append("<XSF_DZDH>"+toEmpty(kj.getXSF_DZDH())+"</XSF_DZDH>");
		content.append("<XSF_YHZH>"+toEmpty(kj.getXSF_YHZH())+"</XSF_YHZH>");
		content.append("<GMF_NSRSBH>"+toEmpty(kj.getGMF_NSRSBH())+"</GMF_NSRSBH>");
		content.append("<GMF_MC>"+toEmpty(kj.getGMF_MC())+"</GMF_MC>");
		content.append("<GMF_DZDH>"+toEmpty(kj.getGMF_DZDH())+"</GMF_DZDH>");
		content.append("<GMF_YHZH>"+toEmpty(kj.getGMF_YHZH())+"</GMF_YHZH>");
		content.append("<KPR>"+toEmpty(kj.getKPR())+"</KPR>");
		content.append("<SKR>"+toEmpty(kj.getSKR())+"</SKR>");
		content.append("<FHR>"+toEmpty(kj.getFHR())+"</FHR>");
		content.append("<YFP_DM>"+toEmpty(kj.getYFP_DM())+"</YFP_DM>");
		content.append("<YFP_HM>"+toEmpty(kj.getYFP_HM())+"</YFP_HM>");
		content.append("<JSHJ>"+toEmpty(kj.getJSHJ())+"</JSHJ>");
		content.append("<HJJE>"+toEmpty(kj.getHJJE())+"</HJJE>");
		content.append("<HJSE>"+toEmpty(kj.getHJSE())+"</HJSE>");
		content.append("<BZ>"+toEmpty(kj.getBZ())+"</BZ>");
		content.append("<GMF_SJH>"+toEmpty(kj.getGMF_SJH())+"</GMF_SJH>");
		content.append("<GMF_DZYX>"+toEmpty(kj.getGMF_DZYX())+"</GMF_DZYX>");
		content.append("<FPT_ZH>"+toEmpty(kj.getFPT_ZH())+"</FPT_ZH>");
		content.append("<HYLX>"+toEmpty(kj.getHYLX())+"</HYLX>");
		content.append("<BY1>"+toEmpty(kj.getBY1())+"</BY1>");
		content.append("<BY2>"+toEmpty(kj.getBY2())+"</BY2>");
		content.append("<BY3>"+toEmpty(kj.getBY3())+"</BY3>");
		content.append("<BY4>"+toEmpty(kj.getBY4())+"</BY4>");
		content.append("<BY5>"+toEmpty(kj.getBY5())+"</BY5>");
		content.append("<BY6>"+toEmpty(kj.getBY6())+"</BY6>");
		content.append("<BY7>"+toEmpty(kj.getBY7())+"</BY7>");
		content.append("<BY8>"+toEmpty(kj.getBY8())+"</BY8>");
		content.append("<BY9>"+toEmpty(kj.getBY9())+"</BY9>");
		content.append("<BY10>"+toEmpty(kj.getBY10())+"</BY10>");
		content.append("<COMMON_FPKJ_XMXXS class='COMMON_FPKJ_XMXX' size='"+kj.getCOMMON_FPKJ_XMXXS().size()+"'>");
		for(DZFPKJXmxx xx:kj.getCOMMON_FPKJ_XMXXS()){
			content.append("<COMMON_FPKJ_XMXX>");
			content.append("<FPHXZ>"+toEmpty(xx.getFPHXZ())+"</FPHXZ>");
			content.append("<SPBM>"+toEmpty(xx.getSPBM())+"</SPBM>");
			content.append("<XMMC>"+toEmpty(xx.getXMMC())+"</XMMC>");
			content.append("<GGXH>"+toEmpty(xx.getGGXH())+"</GGXH>");
			content.append("<DW>"+toEmpty(xx.getDW())+"</DW>");
			content.append("<XMSL>"+toEmpty(xx.getXMSL())+"</XMSL>");
			content.append("<XMDJ>"+toEmpty(xx.getXMDJ())+"</XMDJ>");
			content.append("<XMJE>"+toEmpty(xx.getXMJE())+"</XMJE>");
			content.append("<SL>"+toEmpty(xx.getSL())+"</SL>");
			content.append("<SE>"+toEmpty(xx.getSE())+"</SE>");
			content.append("<BY1>"+toEmpty(xx.getBY1())+"</BY1>");
			content.append("<BY2>"+toEmpty(xx.getBY2())+"</BY2>");
			content.append("<BY3>"+toEmpty(xx.getBY3())+"</BY3>");
			content.append("<BY4>"+toEmpty(xx.getBY4())+"</BY4>");
			content.append("<BY5>"+toEmpty(xx.getBY5())+"</BY5>");
			content.append("</COMMON_FPKJ_XMXX>");
		}
		content.append("</COMMON_FPKJ_XMXXS>");
		content.append("</REQUEST_COMMON_FPKJ>");
		return  new BASE64Encoder().encodeBuffer(content.toString().getBytes("UTF-8"));
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
		StringBuffer sb = new StringBuffer("");
		sb.append("<REQUEST_COMMON_FPCX class='REQUEST_COMMON_FPCX'>");
		sb.append("<FPQQLSH>" + fpqqlsh + "</FPQQLSH>");
		sb.append("<XSF_NSRSBH>" + nsrsbh + "</XSF_NSRSBH>");
		sb.append("</REQUEST_COMMON_FPCX>");
		return new BASE64Encoder().encodeBuffer(sb.toString().getBytes("UTF-8"));
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
		StringBuffer sb = new StringBuffer("");
		sb.append("<REQUEST_COMMON_FPKCCX class=\"REQUEST_COMMON_FPKCCX\">");
		sb.append("<NSRSBH>" + nsrsbh + "</NSRSBH>");
		sb.append("<DETAIL_FLAG>0</DETAIL_FLAG>");
		sb.append("</REQUEST_COMMON_FPKCCX>");
		return new BASE64Encoder().encodeBuffer(sb.toString().getBytes("UTF-8"));
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
		StringBuffer sb = new StringBuffer("");
		sb.append("<REQUEST_COMMON_FPXZDZCX class=\"REQUEST_COMMON_FPXZDZCX\">");
		sb.append("<NSRSBH>"+nsrsbh+"</NSRSBH>");
		sb.append("<FPQQLSH>"+fpqqlsh+"</FPQQLSH>");
		sb.append("<FP_DM>"+fpdm+"</FP_DM>");
		sb.append("<FP_HM>"+fphm+"</FP_HM>");
		sb.append("</REQUEST_COMMON_FPXZDZCX>");
		return new BASE64Encoder().encodeBuffer(sb.toString().getBytes("UTF-8"));
	}

}
