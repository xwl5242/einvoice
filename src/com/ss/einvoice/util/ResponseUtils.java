package com.ss.einvoice.util;

import com.alibaba.fastjson.JSONObject;
import com.ss.einvoice.bean.DZFPRequestResult;
import com.ss.einvoice.bean.DZFPRequestResult.Data;
import com.ss.einvoice.bean.DZFPRequestResult.Data.DataDescription;
import com.ss.einvoice.bean.DZFPRequestResult.GlobalInfo;
import com.ss.einvoice.bean.DZFPRequestResult.ReturnStateInfo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ResponseUtils {
	
	/**
	 * 电子发票请求结果字符串转json对象
	 * @param reqResultStr 电子发票请求结果字符串
	 * @param interfaceLau 请求语言类型 xml和json
	 * @return
	 * @throws Exception
	 */
	public static DZFPRequestResult reqResult2Bean(String reqResultStr,String interfaceLau) throws Exception{
		if (DZFPUtils.interfaceLau_xml.equals(interfaceLau)) {
			return reqResultXmlTrans4Bean(reqResultStr);
		} else if (DZFPUtils.interfaceLau_json.equals(interfaceLau)) {
			return reqResultJsonTrans4Bean(reqResultStr);
		}
		return null;
	}
	
	/**
	 * 电子发票请求结果json字符串转json对象
	 * @param reqResultJson 电子发票请求结果json字符串
	 * @return json对象
	 * @throws Exception
	 */
	public static DZFPRequestResult reqResultJsonTrans4Bean(String reqResultJson) throws Exception{
		if(null==reqResultJson||"".equals(reqResultJson)){
			return null;
		}
		//请求结果JSON字符串转为json对象
		JSONObject jo = JSONObject.parseObject(reqResultJson);
		JSONObject jio = JSONObject.parseObject(jo.getString("interface"));
		jio.put("data", jio.get("Data"));
		jio.remove("Data");
		return DZFPUtils.MapTrans4JavaBean(jio, DZFPRequestResult.class);
	}
	
	/**
	 * 电子发票请求结果xml字符串转json对象
	 * @param reqResultXml 电子发票请求结果xml
	 * @return
	 */
	public static DZFPRequestResult reqResultXmlTrans4Bean(String reqResultXml){
		XStream x = new XStream(new DomDriver());
		x.alias("interface", DZFPRequestResult.class);
		x.alias("globalInfo", GlobalInfo.class);
		x.alias("returnStateInfo", ReturnStateInfo.class);
		x.alias("data", Data.class);
		x.alias("dataDescription", DataDescription.class);
		reqResultXml = reqResultXml.replaceAll("<Data>", "<data>");
		reqResultXml = reqResultXml.replaceAll("</Data>", "</data>");
		return (DZFPRequestResult) x.fromXML(reqResultXml);
	}
	
	/**
	 * 电子发票请求结果bean中获取返回参数字符串
	 * @param r 电子发票请求结果bean
	 * @return
	 * @throws Exception
	 */
	public static String getResponseBody4Bean(DZFPRequestResult r) throws Exception{
		String content = r.getData().getContent();
		return decrypt4BASE64(content);
	}
	
	/**
	 * 密文解密
	 * @param miwen 密文
	 * @return
	 */
	public static String decrypt4BASE64(String miwen) throws Exception{
		return new String(MyAES.decryptBASE64(miwen));
	}
}
