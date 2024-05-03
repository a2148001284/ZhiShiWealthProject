package com.zhouy.login.model;

import lombok.Data;

/**
 * 为输出系统导入模板的excel表对应的bean
 */
@Data
public class UsualScore {
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
    private Long classPerform;
    /**
     * 作业
     */
    private Long homeworkScore;
    /**
     * 在线学习
     */
    private Long onlineLearning;
    /**
     * 实验
     */
    private Long experiment;
    /**
     * 平时成绩 (target)
     */
    private Long usualScore;
}
