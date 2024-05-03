package com.zhouy.login.params;

import lombok.Data;

import java.io.Serializable;
@Data
    public class StudentKindParam implements Serializable {
    /**
     * 正常应届学生总评比例
     */
    private Double studentProportion;
    /**
     * 特殊学生总评比例
     */
    private Double spacialStudentProportion;


}