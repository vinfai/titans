package com.titans.avatar.service;

import com.titans.avatar.api.service.UserService;
import com.titans.avatar.api.vo.UserVO;

/**
 * dubbo 接口实现
 * @author vinfai
 * @since 2016/11/30
 */
public class UserServiceImpl implements UserService{

    //only for test
    public UserVO getUserById(Long userid) {
        UserVO user = new UserVO();
        user.setId(userid);
        user.setNickName(userid+"@wap");
        System.out.println("invoked.....");
        return user;
    }
}
