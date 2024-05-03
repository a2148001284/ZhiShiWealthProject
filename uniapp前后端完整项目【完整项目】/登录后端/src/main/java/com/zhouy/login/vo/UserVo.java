package com.zhouy.login.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {

    private Integer id;

    private String account;

    private String password;

    private String mail;
}
