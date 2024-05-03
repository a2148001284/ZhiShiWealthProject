package com.zhouy.login.model;

import lombok.Data;

/**
 * 导出用户自填的实验成绩
 */
@Data
public class Experiment {
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
     * 实验成绩
     */
    private long experiment=100;
}
