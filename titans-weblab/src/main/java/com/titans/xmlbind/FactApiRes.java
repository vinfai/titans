package com.titans.xmlbind;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class FactApiRes {

	@ApiModelProperty(value = "代码，0000:成功;1004:参数有误;1001:商户不存在;9999:其它各种错误", required = true)
	private String returnCode;
	@ApiModelProperty(value = "信息", required = true)
	private String returnMsg;
	@ApiModelProperty(value = "时间戳", required = true)
	private String _t;
	@ApiModelProperty(value = "签名", required = true)
	private String sign;


	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public String get_t() {
		return _t;
	}
	public void set_t(String _t) {
		this._t = _t;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
}
