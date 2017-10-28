package com.titans.xmlbind;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CreateFactAccResData {
    @ApiModelProperty(value = "资金账号", required = true)
    private String accountNo;
    @ApiModelProperty(value = "账户类型：M0：主账户，不允许创建子账户；M1：主账户，允许创建子账户；S0：子账户", required = true)
    private String accountType;
    @ApiModelProperty(value = "合作商号", required = true)
    private String merchantNo;
    @ApiModelProperty(value = "账户状态：00：新创建；01：生效；02：冻结；03：注销", required = true)
    private String status;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
