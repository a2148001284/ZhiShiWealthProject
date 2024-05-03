package com.zhouy.login.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Favorite implements Serializable {
    /**
     * 数据库自增id
     */
    @TableId(type =  IdType.AUTO)
    private String id;
    /**
     * 收藏的用户id
     */
    private String userId;
    /**
     * 最新收盘价
     */
    private String closingPrice;
    /**
     * PE
     */
    private String pe;
    /**
     * PB
     */
    private String pb;
    /**
     *喜欢股票的id
     */
    private String itemId;
    /**
     * 近一个月
     */
    private String nearlyAMonth;
    /**
     * 股票名字,mybatisPlus默认开启驼峰转换第一个字母小写,不然识别不了
     */
    private String itemName;
}
