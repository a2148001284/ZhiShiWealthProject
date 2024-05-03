package com.zhouy.login.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zhouy.login.model.UsualScore;
import lombok.Data;

import java.io.Serializable;

/**
 * 用于展示平时成绩的表格
 */
public class UsualScoreRecord extends UsualScore implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

}
