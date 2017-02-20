package com.titans.avatar.api.service;


import com.titans.api.vo.ResultCode;
import com.titans.avatar.api.vo.UserVO;

/**
 * dubbo api 测试<br/>
 *
 * @author vinfai
 * @since 2016/11/30
 */
public interface UserService {

    ResultCode<UserVO> getUserById(Long userid);
}
