package com.zhouy.login.model;

import lombok.Data;


/**
 * 导出用户自填的在线学习
 */
@Data
public class OnlineLearning {
    /**
     * 学号
     */
    private String  id;
    /**
     * 名字
     */
    private String name;
    /**
     * 班级名称
     */
    private String className;
    /**
     * 学生类型
     */
    private String studentKind="";
    /**
     * 在线学习
     */
    private long onlineLearning= 100;
}
