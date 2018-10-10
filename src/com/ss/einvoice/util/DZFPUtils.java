package com.ss.einvoice.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
@SuppressWarnings("unchecked")
public class DZFPUtils {
	public static ObjectMapper om = new ObjectMapper();

	public static final String DZFP_CONFIG_PROPERTIES="dzfpConfig.properties";//发票服务配置信息文件位置
	public static final String dfxj1001 = "DFXJ1001"; // 订单上传接口
	public static final String dfxj1003 = "DFXJ1003"; // 发票库存信息
	public static final String dfxj1004 = "DFXJ1004"; // 发票查询
	public static final String dfxj1005 = "DFXJ1005"; // 发票下载地址查询
	
	public static final String interfaceLau_xml = "xml"; 
	public static final String interfaceLau_json = "json"; 
	
	public static final String webservice_axis = "webservice_axis";//请求方式使用axis的webservice
	public static final String webservice_xfire = "webservice_xfire";//请求方式使用xfire的webservice		
	public static final String post_https = "post_https";//使用post请求方式
	
	public static final String requestMethod_xml = "doService";//xml的webservice请求方法名
	public static final String requestMethod_json = "doJsonService";//json的webservice请求方法名
	
	public static final String requestUrlMethod_xml = "/services/DZFPService";//xml的webservice请求方法名
	public static final String requestUrlMethod_json = "/services/DZFPJsonService";//json的webservice请求方法名
	public static final String test_prefix="test_";//测试环境中变量的前缀
	public static final String pro_prefix="pro_";//生产环境中变量的前缀
	
	/**
	 * 发票相关配置
	 */
	public static String fpqqlsh_prefix=null;//发票请求流水号前缀
	public static String requestUrl = null;//初始化地址
	public static String appid = null;//appid
	public static String contentPassword = null;//AES加密密钥
	public static String nsrsbh = null;//纳税人识别号
	public static String xsf_mc = null;//销售方名称
	public static String xsf_dzdh = null;//销售方地址电话
	public static String xsf_yhzh = null;//销售方银行账号
	
	public static String KEY_STORE_FILE = null;
	public static String KEY_STORE_PASS = null;
	public static String TRUST_STORE_FILE = null;
	public static String TRUST_STORE_PASS = null;
	
	public static String interfacelanguage = null;//请求接口的语言格式 ：xml和json
	
	public static String fp_kj_required_fields = null;//发票开具必填项
	public static String fp_kj_xx_required_fields = null;//发票开具项目明细必填项
	
	public static Map<String,String> goods = new HashMap<String,String>();//所有商品信息
	public static String normal_sl = null;//普通发票税率
	public static String kpr = null;//开票人
	
	public static String person_info_file = null;//开票人
	
	static{
		Properties props = new Properties();
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(DZFP_CONFIG_PROPERTIES));
			//先判断配置中的运行环境，是测试还是生产
			String pro_switch=propertyFormat(props.getProperty("pro_switch"));
			
			/**
			 * 因环境而改变的量
			 */
			requestUrl = getProperty4Env(props,pro_switch,"request_url");//发票请求地址
			appid = getProperty4Env(props,pro_switch,"app_id");//请求appid
			contentPassword = getProperty4Env(props,pro_switch,"content_password");//文本内容加密密码
			nsrsbh = getProperty4Env(props,pro_switch,"nsrsbh");//纳税人识别号
			xsf_mc = getProperty4Env(props,pro_switch,"xsf_mc");//销售方名称
			xsf_dzdh = getProperty4Env(props,pro_switch,"xsf_dzdh");//销售方地址电话
			xsf_yhzh = getProperty4Env(props,pro_switch,"xsf_yhzh");//销售方银行账号
			KEY_STORE_FILE = getProperty4Env(props,pro_switch,"key_store_file");//key file
			KEY_STORE_PASS = getProperty4Env(props,pro_switch,"key_store_pass");//key pass
			
			/**
			 * 不变量
			 */
			TRUST_STORE_FILE = propertyFormat(props.getProperty("trust_store_file"));//secret file
			TRUST_STORE_PASS = propertyFormat(props.getProperty("trust_store_pass"));//secret pass
			fpqqlsh_prefix = propertyFormat(props.getProperty("fpqqlsh_prefix"));//发票请求流水号
			interfacelanguage = propertyFormat(props.getProperty("interfacelanguage"));//接口语言
			fp_kj_required_fields = propertyFormat(props.getProperty("fp_kj_required_fields"));//开具必要的属性
			fp_kj_xx_required_fields = propertyFormat(props.getProperty("fp_kj_xx_required_fields"));//开具必要的项目属性
			//获取项目商品的名称和编码
			String goodsStr = propertyFormat(props.getProperty("goods"));
			String[] gs = goodsStr.split("@");
			goods.put("spbm",gs[0]);
			goods.put("xxmc", gs[1]);
			normal_sl = propertyFormat(props.getProperty("normal_sl"));//普通税率
			kpr = propertyFormat(props.getProperty("kpr"));//开票人
			person_info_file = propertyFormat(props.getProperty("person_info_file"));//个人和公司相关扫描件存放地址
		} catch (Exception e) {
		}
	}
	
	/**
	 * 根据环境来获取相关的配置
	 * @param env 环境，on：生产；off：测试。
	 * @param key key
	 * @return
	 */
	public static String getProperty4Env(Properties props,String env,String key) throws Exception{
		String value = null;
		if("on".equals(env)){
			value=propertyFormat(props.getProperty(pro_prefix+key));
		}else if("off".equals(env)){
			value=propertyFormat(props.getProperty(test_prefix+key));
		}
		return value;
	}
	
	/**
	 * properties配置文件内容编码
	 * @param prop
	 * @return
	 * @throws Exception
	 */
	public static String propertyFormat(String prop) throws Exception{
		return new String(prop.getBytes("ISO-8859-1"),"utf-8");
	}
	
	/**
	 * 发票请求流水号
	 * @return
	 */
	public static String getFPQQLSH(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
		return fpqqlsh_prefix+sdf.format(new Date())+String.valueOf(new Random().nextInt(10));
	}
	
	/**
	 * 获取map中key的值
	 * @param map 集合
	 * @param key key值
	 * @return
	 */
	public static String getMapValue(Map<String,Object> map,String key){
		Object value = null;
		if(null!=map){
			Set<String> set = map.keySet();
			for(String s:set){
				if(s.toLowerCase().equals(key.toLowerCase())){
					value = map.get(s);
				}
			}
		}
		return null==value||"".equals(value)?"":value.toString();
	}
	
	/**
	 * 字符串不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		str = str.trim();
		return null!=str&&!"".equals(str)&&!"null".equals(str);
	}
	
	/**
	 * map转为javaBean
	 * @param source 带转换的map
	 * @param clazz javaBean 的class
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T MapTrans4JavaBean(Map source,Class<?> clazz){
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T t = null;
		try {
			byte[] rByte = om.writeValueAsBytes(source);
			t = (T) om.readValue(rByte, clazz);
		} catch (Exception e) {
		}
		return t;
	}
	
	/**
	 * javaBean转为map
	 * @param source javabean
	 * @return
	 */
	public static Map<String,Object> JavaBeanTrans4Map(Object source){
		Map<String,Object> t = null;
		try {
			byte[] rByte =om.writeValueAsBytes(source);
			t = (Map<String,Object>) om.readValue(rByte, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	/**
	 * 获取指定格式时间(yyyy-MM-dd)
	 * @return
	 */
	public static String formatToDay(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format((new Date()));
	}
	
	/**
	 * 获取指定格式时间(yyyyMMddHHmmss)
	 * @return
	 */
	public static String formatToTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format((new Date()));
	}
	
	/************************************************************************
	 * 获取9位随机数
	 */
	public static String randNineData(){
		return randData()+randFiveData();
	}
	
	/************************************************************************
	 * 获取5位随机数
	 */
	public static String randFiveData(){
		return String.valueOf((int)(Math.random()*90000+10000));
	}
	
	/************************************************************************
	 * 获取4位随机数
	 */
	public static String randData(){
		return String.valueOf((int)(Math.random()*9000+1000));
	}
	
}
