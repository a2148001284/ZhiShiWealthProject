package com.zhouy.login.model;

import lombok.Data;

/**
 * 导出让用户自填的课堂表现
 */
@Data
public class ClassPerform {
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
     * 课堂表现
     */
    private long classPerform= 100;

}
