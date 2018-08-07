package com.ss.einvoice.bean;

public class DZFPRequestResult {

	/**
	 * {
	    "interface": {
	        "globalInfo": {
	            "appId": "百望电子提供 appId",
	            "interfaceId": "",
	            "interfaceCode": "DFXJ1001",
	            "requestCode": "DZFPQZ",
	            "requestTime": "2013-09-04 09:58:53",
	            "responseCode": "DS",
	            "dataExchangeId": " DZFPQZDFXJ10012017033098A6123D0 "
	        },
	        "returnStateInfo": {
	            "returnCode": "0000(成功) 0001-9999(错误码)",
	            "returnMessage": "成功或错误的详细消息"
	        },
	        "Data": {
	            "dataDescription": {
	                "zipCode": "0"
	            },
	            "content": "Base64 返回数据内容",
	            "contentKey": "请求数据 content 节点内的内容 MD5 再 AES 加密"
	        }
	    }
	}
	 */
	
	private GlobalInfo globalInfo;
	private ReturnStateInfo returnStateInfo;
	private Data data;
	
	public GlobalInfo getGlobalInfo() {
		return globalInfo;
	}
	public void setGlobalInfo(GlobalInfo globalInfo) {
		this.globalInfo = globalInfo;
	}
	public ReturnStateInfo getReturnStateInfo() {
		return returnStateInfo;
	}
	public void setReturnStateInfo(ReturnStateInfo returnStateInfo) {
		this.returnStateInfo = returnStateInfo;
	}
	
	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}


	public class GlobalInfo {
		private String appId;
		private String interfaceId;
		private String interfaceCode;
		private String requestCode;
		private String requestTime;
		private String responseCode;
		private String dataExchangeId;
		
		public GlobalInfo() {
		}
		public String getAppId() {
			return appId;
		}
		public void setAppId(String appId) {
			this.appId = appId;
		}
		public String getInterfaceId() {
			return interfaceId;
		}
		public void setInterfaceId(String interfaceId) {
			this.interfaceId = interfaceId;
		}
		public String getInterfaceCode() {
			return interfaceCode;
		}
		public void setInterfaceCode(String interfaceCode) {
			this.interfaceCode = interfaceCode;
		}
		public String getRequestCode() {
			return requestCode;
		}
		public void setRequestCode(String requestCode) {
			this.requestCode = requestCode;
		}
		public String getRequestTime() {
			return requestTime;
		}
		public void setRequestTime(String requestTime) {
			this.requestTime = requestTime;
		}
		public String getResponseCode() {
			return responseCode;
		}
		public void setResponseCode(String responseCode) {
			this.responseCode = responseCode;
		}
		public String getDataExchangeId() {
			return dataExchangeId;
		}
		public void setDataExchangeId(String dataExchangeId) {
			this.dataExchangeId = dataExchangeId;
		}
		@Override
		public String toString() {
			return "GlobalInfo [appId=" + appId + ", interfaceId="
					+ interfaceId + ", interfaceCode=" + interfaceCode
					+ ", requestCode=" + requestCode + ", requestTime="
					+ requestTime + ", responseCode=" + responseCode
					+ ", dataExchangeId=" + dataExchangeId + "]";
		}
	}
	
	public class ReturnStateInfo {
		private String returnCode;
		private String returnMessage;
		public ReturnStateInfo(){}
		public String getReturnCode() {
			return returnCode;
		}
		public void setReturnCode(String returnCode) {
			this.returnCode = returnCode;
		}
		public String getReturnMessage() {
			return returnMessage;
		}
		public void setReturnMessage(String returnMessage) {
			this.returnMessage = returnMessage;
		}
		@Override
		public String toString() {
			return "ReturnStateInfo [returnCode=" + returnCode
					+ ", returnMessage=" + returnMessage + "]";
		}
	}
	
	public class Data {
		private DataDescription dataDescription;
		private String content;
		public Data(){}
		public DataDescription getDataDescription() {
			return dataDescription;
		}
		public void setDataDescription(DataDescription dataDescription) {
			this.dataDescription = dataDescription;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		@Override
		public String toString() {
			return "Data [dataDescription=" + dataDescription + ", content="
					+ content + "]";
		}
		public class DataDescription{
			private String zipCode;
			public DataDescription(){}
			public String getZipCode() {
				return zipCode;
			}

			public void setZipCode(String zipCode) {
				this.zipCode = zipCode;
			}

			@Override
			public String toString() {
				return "DataDescription [zipCode=" + zipCode + "]";
			}
			
		}
	}
	
	@Override
	public String toString() {
		return "DZFPResponseBody [globalInfo=" + globalInfo
				+ ", returnStateInfo=" + returnStateInfo + ", data=" + data
				+ "]";
	}
	
}
