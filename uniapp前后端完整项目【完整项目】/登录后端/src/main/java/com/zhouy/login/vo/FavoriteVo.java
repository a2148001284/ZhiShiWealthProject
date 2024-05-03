package com.zhouy.login.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class FavoriteVo implements Serializable {
    //favorite id
    private String itemId;
    //用户id
    private String UserId;
}
