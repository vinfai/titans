package com.titans.web.action.api;

import com.titans.xmlbind.CreateFactAccRes;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger.web.UiConfiguration;

/**
 * @author vinfai
 * @since 2017/6/23
 */
@Api(value = "商户账号API接口")
@RestController
public class FactAccountApiController {
    public FactAccountApiController() {
        System.out.println("FactAccountApiController");
    }

    @ApiOperation(value = "创建资金账号", notes = "create fact account notes."/*, response = CreateFactAccRes.class*/)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "创建账号参数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "_t", value = "时间戳", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "sign", value = "请求签名", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"/*, response = CreateFactAccRes.class*/)
    })
    @RequestMapping(value = "/api/fact/account/create.xhtml", method = RequestMethod.POST)
    public String createAccout(String params, String sign, Long _t) {
//        UiConfiguration
        return null;
    }
    /*public String getAccount(String ){

    }*/
}
