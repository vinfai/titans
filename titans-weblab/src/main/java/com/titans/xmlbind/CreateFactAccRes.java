package com.titans.xmlbind;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author vinfai
 * @since 2017/6/26
 */
@ApiModel
public class CreateFactAccRes extends FactApiRes {
    @ApiModelProperty(value = "成功时返回的数据", required = false)
    private CreateFactAccResData data;

    public CreateFactAccResData getData() {
        return data;
    }

    public void setData(CreateFactAccResData data) {
        this.data = data;
    }

}
