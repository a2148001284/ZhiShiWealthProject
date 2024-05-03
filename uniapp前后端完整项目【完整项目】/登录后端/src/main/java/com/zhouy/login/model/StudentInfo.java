package com.zhouy.login.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 为输出系统导入模板的excel表对应的bean
 */
@Data
public class StudentInfo{
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
     * 最终成绩
     */
    private Long score;
    /**
     * 备注
     */
    private String remark="";
    /**
     * 默认平时分和期末的比例的比例
     */
    private Double  proportion=0.4;
}
