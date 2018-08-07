package com.ss.einvoice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ss.einvoice.bean.DZFPJY;
import com.ss.einvoice.bean.DZFPJYJson;
import com.ss.einvoice.bean.DZFPKJ;
import com.ss.einvoice.bean.DZFPKJXmxx;
import com.ss.einvoice.bean.DZFPRequestResult;
import com.ss.einvoice.bean.DZFPResponseBody;
import com.ss.einvoice.util.DZFPUtils;
import com.ss.einvoice.util.JsonUtils;
import com.ss.einvoice.util.RequestUtils;
import com.ss.einvoice.util.ResponseUtils;
import com.ss.einvoice.util.XmlUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class DZFPService {
	
	/**
	 * 圣商普通发票
	 * @param gmfName 购买方姓名
	 * @param gmfPhone 购买方电话
	 * @param gmfEmail 购买方邮箱
	 * @param mName 课程名称
	 * @param mPrice 课程价格
	 * @return
	 * @throws Exception
	 */
	public static DZFPResponseBody kj4SS(String gmfName,String gmfPhone,String gmfEmail,String gmfNsrsbh,String xmJe) throws Exception{
		List<DZFPKJXmxx> list = new ArrayList<DZFPKJXmxx>();
		DZFPKJXmxx xx = new DZFPKJXmxx();
		xx.setFPHXZ("0");//发票行性质，0:普通;1:折扣行;2:被折扣行
		xx.setSPBM(DZFPUtils.goods.get("spbm"));
		xx.setZXBM("");
		xx.setXMMC(DZFPUtils.goods.get("xxmc"));
		xx.setXMJE(xmJe);
		xx.setSL(DZFPUtils.normal_sl);
		list.add(xx);
		return kj4Normal("0",gmfNsrsbh,gmfName,gmfPhone,gmfEmail,DZFPUtils.kpr,"","",list);
	}
	
	/**
	 * 普通发票开具
	 * @param KPLX 开票类型 0:蓝票;1:红票
	 * @param GMF_NSRSBH 购买方纳税人识别号
	 * @param GMF_MC 购买方名称
	 * @param GMF_SJH 购买方手机号
	 * @param GMF_DZYX 购买方电子邮箱
	 * @param KPR 开票人
	 * @param list 发票开具项目明细集合
	 *     项目明细：DZFPKJXmxx
	 *     必填项：发票行性质  项目名称  项目金额  税率
	 * @return
	 * @throws Exception
	 */
	public static DZFPResponseBody kj4Normal(String KPLX,
			String GMF_NSRSBH,String GMF_MC,String GMF_SJH,String GMF_DZYX,
			String KPR,String YFP_DM,String YFP_HM,List<DZFPKJXmxx> list) throws Exception{
		
		if(!DZFPUtils.isNotEmpty(GMF_SJH)&&!DZFPUtils.isNotEmpty(GMF_DZYX)){
			throw new Exception("购买方手机号与电子邮箱不能同时为空!");
		}
		DZFPKJ kj = new DZFPKJ();
		kj.setKPLX(KPLX);//开票类型
		kj.setZSFS("0");//征税方式
		kj.setGMF_NSRSBH(GMF_NSRSBH);//购买方纳税人识别号
		kj.setGMF_MC(GMF_MC);//购买方名称
		kj.setGMF_SJH(GMF_SJH);//购买方手机号
		kj.setGMF_DZYX(GMF_DZYX);//购买方电子邮箱
		kj.setKPR(KPR);//开票人
		kj.setYFP_DM(YFP_DM);
		kj.setYFP_HM(YFP_HM);
		//开具项目详情信息list
		kj.setCOMMON_FPKJ_XMXXS(list);
		return kjService(kj);
	}
	
	/**
	 * 减按计征发票开具
	 * @param GMF_NSRSBH 购买方纳税人识别号
	 * @param GMF_MC 购买方名称
	 * @param GMF_SJH 购买方手机号
	 * @param GMF_DZYX 购买方电子邮箱
	 * @param KPR 开票人
	 * @param list 发票开具项目明细集合
	 *     项目明细：DZFPKJXmxx
	 *     必填项：项目名称  项目金额  税率
	 * @return
	 * @throws Exception
	 */
	public static DZFPResponseBody kj4Subtraction(String GMF_NSRSBH,String GMF_MC,
			String GMF_SJH,String GMF_DZYX,String KPR,List<DZFPKJXmxx> list)throws Exception{
		if(!DZFPUtils.isNotEmpty(GMF_SJH)&&!DZFPUtils.isNotEmpty(GMF_DZYX)){
			throw new Exception("购买方手机号与电子邮箱不能同时为空!");
		}
		DZFPKJ kj = new DZFPKJ();
		kj.setKPLX("0");//开票类型
		kj.setZSFS("1");//征税方式
		kj.setGMF_NSRSBH(GMF_NSRSBH);//购买方纳税人识别号
		kj.setGMF_MC(GMF_MC);//购买方名称
		kj.setGMF_SJH(GMF_SJH);//购买方手机号
		kj.setGMF_DZYX(GMF_DZYX);//购买方电子邮箱
		kj.setKPR(KPR);//开票人
		//开具项目详情信息list
		kj.setCOMMON_FPKJ_XMXXS(list);
		return kjService(kj);
	}

	/**
	 * 差额征税发票开具
	 * @param GMF_NSRSBH 购买方纳税人识别号
	 * @param GMF_MC 购买方名称
	 * @param GMF_SJH 购买方手机号
	 * @param GMF_DZYX 购买方电子邮箱
	 * @param KPR 开票人
	 * @param KCE 扣除额
	 * @param XMMC 发票开具项目明细-项目名称
	 * @param XMJE 发票开具项目明细-项目金额
	 * @param SL 发票开具项目明细-税率
	 * @return
	 * @throws Exception
	 */
	public static DZFPResponseBody kj4Difference(String GMF_NSRSBH,String GMF_MC,String GMF_SJH,
			String GMF_DZYX,String KPR,String JSHJ,String KCE,String SPBM,String ZXBM,String XMMC,String SL)throws Exception{
		if(!DZFPUtils.isNotEmpty(GMF_SJH)&&!DZFPUtils.isNotEmpty(GMF_DZYX)){
			throw new Exception("购买方手机号与电子邮箱不能同时为空!");
		}
		//差额征税发票的项目明细只能是一条，在这里封装
		List<DZFPKJXmxx> list = new ArrayList<DZFPKJXmxx>();
		DZFPKJXmxx xx = new DZFPKJXmxx();//项目明细
		xx.setSPBM(SPBM);
		xx.setZXBM(ZXBM);
		xx.setXMMC(XMMC);//设置项目名称
		xx.setSL(SL);//设置税率
		list.add(xx);//放进list中
		//发票开具信息封装
		DZFPKJ kj = new DZFPKJ();
		kj.setKPLX("0");//开票类型
		kj.setZSFS("2");//征税方式
		kj.setGMF_NSRSBH(GMF_NSRSBH);//购买方纳税人识别号
		kj.setGMF_MC(GMF_MC);//购买方名称
		kj.setGMF_SJH(GMF_SJH);//购买方手机号
		kj.setGMF_DZYX(GMF_DZYX);//购买方电子邮箱
		kj.setKPR(KPR);//开票人
		kj.setJSHJ(JSHJ);
		kj.setKCE(KCE);//扣除额
		//开具项目详情信息list
		kj.setCOMMON_FPKJ_XMXXS(list);
		return kjService(kj);//调用开具服务
	}
	
	/**
	 * 开具
	 * @param kj 开具请求参数信息
	 * @return
	 * @throws Exception
	 */
	public static DZFPResponseBody kjService(DZFPKJ kj) throws Exception{
		//购买方手机号和电子邮箱二者必须有一个存在
		if(!DZFPUtils.isNotEmpty(kj.getGMF_SJH())&&!DZFPUtils.isNotEmpty(kj.getGMF_DZYX())){
			throw new Exception("购买方手机号与电子邮箱不能同时为空!");
		}
		//开具项目详情个数判断
		if(null==kj.getCOMMON_FPKJ_XMXXS()||kj.getCOMMON_FPKJ_XMXXS().size()==0){
			throw new Exception("请传递项目明细！");
		}
		if(kj.getCOMMON_FPKJ_XMXXS().size()>100){
			throw new Exception("项目明细，最多100条！");
		}
		//红字发票
		if("1".equals(kj.getKPLX())){
			if(!DZFPUtils.isNotEmpty(kj.getYFP_DM())){
				throw new Exception("红字发票的原发票代码必填！");
			}
			if(!DZFPUtils.isNotEmpty(kj.getYFP_HM())){
				throw new Exception("红字发票的原发票号码必填！");
			}
		}
		//根据征税方式走不同流程。折扣只能是普通征税方式（红票也是）
		int zsfs = Integer.valueOf(kj.getZSFS());//征税方式
		if(zsfs==0||zsfs==1){
			kj = buildKJCommon4ZSFS01(kj,zsfs);
		}else if(zsfs==2){
			if(kj.getCOMMON_FPKJ_XMXXS().size()!=1) throw new Exception("差额征税只能开具一行明细！");
			if(!DZFPUtils.isNotEmpty(kj.getKCE())) throw new Exception("征税方式为差额征税时，扣除额为必填！");
			DZFPKJXmxx xx = kj.getCOMMON_FPKJ_XMXXS().get(0);//开具项目详细信息
			double jshj = Double.valueOf(kj.getJSHJ());//价税合计
			double kce = Double.valueOf(kj.getKCE());//扣除额
			double zz = jshj-kce;//增值部分
			double sl = Double.valueOf(xx.getSL());//税率
			double hjse = zz-zz/(1+sl);//税额
			//设置发票项目行性质
			xx.setFPHXZ("0");
			xx.setXMSL("1");//设置项目数量
			xx.setXMDJ(String.format("%.2f", jshj-hjse));
			xx.setXMJE(String.format("%.2f", jshj-hjse));//设置项目金额
			xx.setSE(String.format("%.2f",hjse));//设置税额
			//发票设置项目详细
			List<DZFPKJXmxx> list = new ArrayList<DZFPKJXmxx>();
			list.add(xx);
			//检查必填项
			checkFpKjRequiredFields(xx,DZFPUtils.fp_kj_xx_required_fields);
			kj.setCOMMON_FPKJ_XMXXS(list);
			//发票设置合计金额
			kj.setHJJE(String.format("%.2f", jshj-hjse));
			//发票设置合计税额
			kj.setHJSE(String.format("%.2f",hjse));
		}else{
			throw new Exception("发票征税方式填写有误！");
		}
		//补全发票的其他默认或系统属性
		kj.setFPQQLSH(DZFPUtils.getFPQQLSH());//发票请求流水号
		kj.setXSF_NSRSBH(DZFPUtils.nsrsbh);//销售方-纳税人识别号
		kj.setXSF_MC(DZFPUtils.xsf_mc);//销售方-名称
		kj.setXSF_DZDH(DZFPUtils.xsf_dzdh);//销售方-地址、电话
		kj.setXSF_YHZH(DZFPUtils.xsf_yhzh);//销售方-银行账号
		kj.setHYLX("0");//行业类型
		kj.setTSPZ("00");//不是特殊票种
		//检测开具信息必填项是否填写完整
		checkFpKjRequiredFields(kj,DZFPUtils.fp_kj_required_fields);
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("dzfpkj", kj);
		return service(DZFPUtils.dfxj1001,params);
	}
	
	/**
	 * 构建发票开具信息，征税方式为普通和减按计征的
	 * @param kj
	 * @param zsfs
	 */
	public static DZFPKJ buildKJCommon4ZSFS01(DZFPKJ kj,int zsfs) throws Exception{
		List<DZFPKJXmxx> list = new ArrayList<DZFPKJXmxx>();
		double jshj=0,hjje=0,hjse=0;//jshj:价税合计;hjse:合计税额
		//红票标志
		boolean redFPFlag = "1".equals(kj.getKPLX());
		for(DZFPKJXmxx xx:kj.getCOMMON_FPKJ_XMXXS()){
			if(zsfs==1) xx.setFPHXZ("0");
			//项目明细-项目金额
			double xmje = Double.valueOf(xx.getXMJE());
			//项目明细-税率
			double sl = Double.valueOf(xx.getSL());
			double se = 0;
			//不同征税类型计算税额
			if(zsfs==0){//普通征税
				se = xmje*sl;//计算税额
				//涉及到红票
				if(redFPFlag) xx.setXMSL("-1");
				xx.setYHZCBS("0");
			}else if(zsfs==1){//减按计征
				se = xmje/(1+(5.0/100))*sl;//计算税额
				xx.setYHZCBS("1");//设置使用优惠政策
				xx.setZZSTSGL("按5%简易征收减按1.5%计征");
			}
			if(zsfs==0) hjje+=xmje;
			if(zsfs==1) jshj+=xmje;
			hjse+=se;//合计
			//发票行性质：折扣行标志
			boolean zkhxz = "1".equals(xx.getFPHXZ());
			//最后确定发票项目明细中金额和税额的正负
			xmje=redFPFlag||zkhxz?-xmje:xmje;
			se=redFPFlag||zkhxz?-se:se;
			//设置项目金额
			xx.setXMJE(String.format("%.2f",xmje));
			xx.setSE(String.format("%.2f",se));//设置税额
			//检查必填项
			checkFpKjRequiredFields(xx,DZFPUtils.fp_kj_xx_required_fields);
			list.add(xx);
		}
		//设置发票外部参数
		kj.setCOMMON_FPKJ_XMXXS(list);
		//最后确定发票价税合计、合计税额、合计金额的正负
		hjje=redFPFlag?-hjje:hjje;
		hjse=redFPFlag?-hjse:hjse;
		if(zsfs==0){
			double js=redFPFlag?-(hjje+hjse):(hjje+hjse);
			kj.setJSHJ(String.format("%.2f",js));//设置价税合计
			kj.setHJJE(String.format("%.2f",hjje));//设置合计金额
		}else if(zsfs==1){
			double js=jshj-hjse;
			kj.setJSHJ(String.format("%.2f",jshj));//设置价税合计
			kj.setHJJE(String.format("%.2f",js));//设置合计金额
		}
		kj.setHJSE(String.format("%.2f",hjse));//设置合计税额
		return kj;
	}
	
	/**
	 * 查询发票服务
	 * @param FPQQLSH 发票请求流水号
	 * @return
	 * @throws Exception
	 */
	public static DZFPResponseBody query(String FPQQLSH) throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("fpqqlsh", FPQQLSH);
		return service(DZFPUtils.dfxj1004,params);
	}
	
	/**
	 * 库存查询服务
	 * @return
	 * @throws Exception
	 */
	public static int queryKC() throws Exception{
		return service(DZFPUtils.dfxj1003,new HashMap<String,Object>());
	}
	
	/**
	 * 发票下载 地址查询服务
	 * @param FPQQLSH 发票请求流水号
	 * @param FP_DM 发票代码
	 * @param FP_HM 发票号码
	 * @return
	 * @throws Exception
	 */
	public static String download(String FPQQLSH,String FP_DM,String FP_HM) throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("fpqqlsh", FPQQLSH);
		params.put("fpdm", FP_DM);
		params.put("fphm", FP_HM);
		return service(DZFPUtils.dfxj1005,params);
	}
	
	/**
	 * 执行电子发票服务
	 * @param interfaceCode 接口  开具  查询  下载 
	 * @param params 请求参数
	 * @return
	 */
	public static <T> T service(String interfaceCode,Map<String,Object> params) throws Exception{
		return service(interfaceCode, DZFPUtils.post_https, DZFPUtils.interfacelanguage, params);
	}

	/**
	 * 执行电子发票服务中的接口
	 * @param interfaceCode 接口  开具  查询  下载  
	 * @param requestInterface 请求方式 axis xfire postHttps
	 * @param interfaceLanguage 何种方式 xml json
	 * @param params 请求参数
	 * @return
	 */
	public static <T> T service(String interfaceCode,String requestInterface,
			String interfaceLanguage,Map<String,Object> params) throws Exception{
		T t = null;//初始化返回结果
		String requestData = null;//初始化请求报文
		String requestMethod = null;//初始化请求方法
		String requestUrlMethod = null;//初始化连接方法
		try{
			String fpqqlsh = DZFPUtils.getMapValue(params,"fpqqlsh");
			String fpdm = DZFPUtils.getMapValue(params,"fpdm");
			String fphm = DZFPUtils.getMapValue(params,"fphm");
			DZFPKJ dzfpkj = (DZFPKJ) params.get("dzfpkj");
			// 组装请求报文
			if (DZFPUtils.interfaceLau_xml.equals(interfaceLanguage)) {
				requestData = XmlUtils.getSendToTaxXML(interfaceCode,fpqqlsh,fpdm,fphm,dzfpkj);
				requestMethod = DZFPUtils.requestMethod_xml;//xml的请求方法
				requestUrlMethod = DZFPUtils.requestUrlMethod_xml;//xml的连接后缀
			} else if (DZFPUtils.interfaceLau_json.equals(interfaceLanguage)) {
				requestData = JsonUtils.getSendToTaxJson(interfaceCode,fpqqlsh,fpdm,fphm,dzfpkj);
				requestMethod = DZFPUtils.requestMethod_json;//json的请求方法
				requestUrlMethod = DZFPUtils.requestUrlMethod_json;//json的连接后缀
			}else{
				throw new Exception("请选择语言！");
			}
			String rsData = null;
			// 调用接口
			if(DZFPUtils.webservice_axis.equals(requestInterface)){
				rsData = RequestUtils.webServiceAxis(requestData,requestMethod,DZFPUtils.requestUrl+requestUrlMethod);
			}else if(DZFPUtils.webservice_xfire.equals(requestInterface)){
				rsData = RequestUtils.webServiceXfile(requestData, requestMethod, DZFPUtils.requestUrl+requestUrlMethod+"?wsdl");
			}else if(DZFPUtils.post_https.equals(requestInterface)){
				rsData = RequestUtils.getHttpConnectResult(requestData,DZFPUtils.requestUrl+"invoice");
			}
			System.out.println("调用结果："+rsData);
			//处理调用结果
			DZFPRequestResult dzfpReqRet = ResponseUtils.reqResult2Bean(rsData, interfaceLanguage);
			//请求结果，0000：请求成功；其他：请求失败。
			String code = dzfpReqRet.getReturnStateInfo().getReturnCode();
			//请求结果message
			String message = dzfpReqRet.getReturnStateInfo().getReturnMessage();
			if("0000".equals(code)){//调用成功
				//请求响应体
				String content = dzfpReqRet.getData().getContent();
				//解密
				content = ResponseUtils.decrypt4BASE64(content);
				//处理结果
				t = dealResultContent(interfaceCode, interfaceLanguage, content);
			}else{//调用失败
				throw new Exception("调用失败！code:"+code+",errMsg:"+message);
			}
		}catch(Exception e){
			throw e;
		}
		return t;
	}
	
	/**
	 * 处理返回结果
	 * @param interfaceCode 调用的方法
	 * @param interfaceLanguage 调用使用的语言格式
	 * @param content 响应体
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T dealResultContent(String interfaceCode,String interfaceLanguage,String content){
		T t=null;
		//处理结果
		if (interfaceCode.equals(DZFPUtils.dfxj1001)||interfaceCode.equals(DZFPUtils.dfxj1004)) {//开具和查询发票
			if(interfaceLanguage.equals(DZFPUtils.interfaceLau_json)){
				t = DZFPUtils.MapTrans4JavaBean(JSONObject.parseObject(content), DZFPResponseBody.class);
			}else if(interfaceLanguage.equals(DZFPUtils.interfaceLau_xml)){
				XStream x = new XStream(new DomDriver());
				x.alias("RESPONSE", DZFPResponseBody.class);
				t = (T) x.fromXML(content);
			}
		}else if (interfaceCode.equals(DZFPUtils.dfxj1003)) {//库存查询
			if(content.contains("SUM_NUM")){
				DZFPJY jy = null;
				if(interfaceLanguage.equals(DZFPUtils.interfaceLau_json)){
					DZFPJYJson json = JSONObject.parseObject(content, DZFPJYJson.class);
					jy = json.getRESPONSE();
				}else if(interfaceLanguage.equals(DZFPUtils.interfaceLau_xml)){
					XStream x = new XStream(new DomDriver());
					x.alias("RESPONSE", DZFPJY.class);
					jy = (DZFPJY) x.fromXML(content);
				}
				t = (T) Integer.valueOf(jy.getSUM_NUM());
			}else{
				t = (T) Integer.valueOf(content);
			}
		}else if (interfaceCode.equals(DZFPUtils.dfxj1005)) {//下载地址
			DZFPResponseBody db = null;
			if(interfaceLanguage.equals(DZFPUtils.interfaceLau_json)){
				db = DZFPUtils.MapTrans4JavaBean(JSONObject.parseObject(content), DZFPResponseBody.class);
			}else if(interfaceLanguage.equals(DZFPUtils.interfaceLau_xml)){
				XStream x = new XStream(new DomDriver());
				x.alias("RESPONSE", DZFPResponseBody.class);
				db = (DZFPResponseBody) x.fromXML(content);
			}
			t = (T) db.getPDF_URL();
		}
		return t;
	}
	
	/**
	 * 检查发票开具必填项
	 * @param kj
	 * @return
	 */
	private static void checkFpKjRequiredFields(Object o,String fields) throws Exception{
		String result = "success";
		Map<String,Object> map = DZFPUtils.JavaBeanTrans4Map(o);
		for(String f:fields.split(",")){
			if(!DZFPUtils.isNotEmpty(DZFPUtils.getMapValue(map,f.toLowerCase()))){
				result = f+"为必填项，请传递该参数的值！";
				break;
			}
		}
		if(!"success".equals(result)){
			throw new Exception(result);
		}
	} 
	
	public static void main(String[] args) throws Exception {
		System.out.println(queryKC());
	}
}
