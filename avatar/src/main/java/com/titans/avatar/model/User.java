package com.titans.avatar.model;

/**
 * @author vinfai
 * @since 2016/11/30
 */
public class User {

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
