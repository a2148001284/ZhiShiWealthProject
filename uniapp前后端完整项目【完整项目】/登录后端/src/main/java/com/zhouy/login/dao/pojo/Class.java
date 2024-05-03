package com.zhouy.login.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
public class Class implements Serializable {

    @TableId(type =  IdType.AUTO)
    private Integer id;

    private String className;

    private Integer teacherId;

    private String name;

    private String avatar;
}

