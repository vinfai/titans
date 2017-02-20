package com.titans.avatar.api.vo;

import java.io.Serializable;

/**
 * dubbo 接口的数据，要实现序列化
 * @author vinfai
 * @since 2016/12/1
 */
public class UserVO implements Serializable{
    private Long id;
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
