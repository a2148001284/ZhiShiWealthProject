package com.zhouy.login.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ClassInfo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String stuId;

    private String stuName;

    private String className;

    private Double firstPart;

    private Double secondPart;

    private Double thirdPart;

    private Double fourthPart;

    private Double fifthPart;

    private Double sixthPart;

    private Double seventhPart;

    private Double eighthPart;

    private Double ninthPart;

    private Double tenthPart;

    private Double target1Weight;

    private Double target1Degree;

    private Double target2Weight;

    private Double target2Degree;

    private Double target3Weight;

    private Double target3Degree;

    private String kind;

    private Integer classId;

}
