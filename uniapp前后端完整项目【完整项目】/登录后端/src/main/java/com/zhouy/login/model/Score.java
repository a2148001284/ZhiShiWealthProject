package com.zhouy.login.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Data
@NoArgsConstructor
@Component
public class Score {
    /**
     * 期末成绩集合
     */
    private List<Double> finalExamScore;
    /**
     * 平时成绩
     */
    private List<Double> usualScore;

}
