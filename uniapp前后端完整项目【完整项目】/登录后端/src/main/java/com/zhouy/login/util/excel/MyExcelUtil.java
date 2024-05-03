package com.zhouy.login.util.excel;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.zhouy.login.model.Result;
import com.zhouy.login.model.Score;
import com.zhouy.login.model.StudentInfo;
import com.zhouy.login.model.UsualScore;
import com.zhouy.login.params.StudentKindParam;
import io.swagger.models.auth.In;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Component
public class MyExcelUtil {

    @Value("${Proportion}")
    private Double Proportion=0.4;

    @Value("${classPerformDefaultScore}")
    private long classPerformDefaultScore;

    @Value("${onlineLearningDefaultScore}")
    private long onlineLearningDefaultScore;

    @Value("${experimentDefaultScore}")
    private long experimentDefaultScore;

    //不区分学生类别
    private List<StudentInfo> baseInfos=new ArrayList<>();
    //学生类别为应届
    private List<StudentInfo> reBuildStudents;
    //学生类别为重修
    private  List<StudentInfo> studentInfos;

    private List<Double> homeworkList;

    //测试在此处抛出异常能否被捕获
    public void throwIndexOutOfBoundsException(){
        throw new IndexOutOfBoundsException("下标越界");
    }

    //根据点名册内容将学生信息先放入一个整的集合
    public void  getRollCallContent(String path) {
        ExcelReader reader = ExcelUtil.getReader(path);
        List<List<Object>> sheet = reader.read();
        //舍弃头部和尾部不需要的信息，老师给的前4行和最后一行要去掉
        sheet.remove(0);
        sheet.remove(0);
        sheet.remove(0);
        sheet.remove(0);
        sheet.remove(sheet.size() - 1);
        //根据学号升序
        sheet.sort(new Comparator<List<Object>>() {
            @Override
            public int compare(List<Object> o1, List<Object> o2) {
                return SortUtil.doSort(o1, o2, 1);
            }
        });
        for (List<Object> rows : sheet) {
                StudentInfo baseInfo =new StudentInfo();
                baseInfo.setId((String) rows.get(1));
                baseInfo.setName((String) rows.get(2));
                baseInfo.setClassName((String) rows.get(3));
                baseInfo.setStudentKind((String) rows.get(4));
                baseInfos.add(baseInfo);

        }
    }

    //重载根据点名册内容将学生信息先放入一个集合,T 可以是StudentInfo,UsualScore..
    public List<UsualScore> getRollCallContent(String path, List<UsualScore> list){
        ExcelReader reader = ExcelUtil.getReader(path);
        List<List<Object>> sheet = reader.read();
        //舍弃头部和尾部不需要的信息，老师给的前4行和最后一行要去掉
        sheet.remove(0);
        sheet.remove(0);
        sheet.remove(0);
        sheet.remove(0);
        sheet.remove(sheet.size() - 1);
        //根据学号升序
        sheet.sort(new Comparator<List<Object>>() {
            @Override
            public int compare(List<Object> o1, List<Object> o2) {
                return SortUtil.doSort(o1, o2, 1);
            }
        });
        for (List<Object> rows : sheet) {
            UsualScore usualScore =new UsualScore();
            usualScore.setId((String) rows.get(1));
            usualScore.setName((String) rows.get(2));
            usualScore.setClassName((String) rows.get(3));
            usualScore.setStudentKind((String) rows.get(4));
            list.add(usualScore);
        }
        return list;
    }

    /**
     * 获取期末成绩填入studentInfo
     * @param path 路径
     * @return List
     */
    public  List<Double> getFinalExams(String path) {
        ExcelReader reader = ExcelUtil.getReader(path);
        List<List<Object>> sheet = reader.read();
        sheet.remove(0);
        sheet.remove(0);
        sheet.remove(0);
        sheet.remove(0);
        sheet.remove(sheet.size() - 1);
        sheet.sort(new Comparator<List<Object>>() {
            @Override
            public int compare(List<Object> o1, List<Object> o2) {
                return SortUtil.doSort(o1, o2, 0);
            }
        });
        List<Double> finalExamScores = new ArrayList<>();
        Score scoreTemp =new Score();
        for (List<Object> rows : sheet) {
            //获取排好序的总成绩
            Double finalExamScore =  Double.parseDouble(String.valueOf(rows.get(rows.size() - 1)));
            finalExamScores.add(finalExamScore);
        }
        return finalExamScores;
    }

    /**
     * 获取平时成绩填入StudentInfo
     * @param path 路径
     * @return List
     */
    public  List<Double> getUsualScore(String path) {
        ExcelReader reader = ExcelUtil.getReader(path);
        List<List<Object>> sheet = reader.read();
        sheet.remove(0);
        sheet.remove(0);
        sheet.remove(0);
        sheet.remove(0);
        sheet.remove(sheet.size() - 1);
        sheet.sort(new Comparator<List<Object>>() {
            @Override
            public int compare(List<Object> o1, List<Object> o2) {
                return SortUtil.doSort(o1, o2, 1);
            }
        });
        List<Double> usualScores = new ArrayList<>();
        for (List<Object> rows : sheet) {
            //获取排好序的总成绩
            Double usualScore = Double.parseDouble(String.valueOf(rows.get(rows.size() - 1)));
            usualScores.add(usualScore);
        }
        return usualScores;
    }
    public Result judgeKind(StudentKindParam param){
        for (StudentInfo baseInfo : baseInfos) {
            //正常学生
            if(baseInfo.getStudentKind().equals("")){
                baseInfo.setProportion(param.getStudentProportion());
            }
            //特性学生
            else{
                baseInfo.setProportion(param.getSpacialStudentProportion());
            }
        }
        return Result.success(param,"接受成功");
    }

    //这里只是write的部分操作，完整的操作在uploadController中
    public void getScore(String path1,String path2) {
        Score score = new Score();
      //将score中的期末分和平时分得到最终分填入StudentInfo
        List<Double> usualScore = getUsualScore(path1);
        score.setUsualScore(usualScore);
        List<Double> finalExamScore = getFinalExams(path2);
        score.setFinalExamScore(finalExamScore);
        System.out.println(finalExamScore);
        for (int i = 0; i < usualScore.size(); i++) {
            Double studentScore = usualScore.get(i) * baseInfos.get(i).getProportion() + finalExamScore.get(i) * (1 - baseInfos.get(i).getProportion());
            //对成绩四舍五入
            long round = Math.round(studentScore);
            baseInfos.get(i).setScore(round);
        }
    }

    //获取整集合中学生为应届的学生
    public List<StudentInfo> getStudentInfos(List<StudentInfo> baseInfos){
        List<StudentInfo> list =new ArrayList<>();
        for (StudentInfo baseInfo : baseInfos) {
            if(!baseInfo.getStudentKind().contains("重修")){
                list.add(baseInfo);
            }
        }
        return list;
    }

    public List<StudentInfo> getReBuildStudents(List<StudentInfo> baseInfos){
        List<StudentInfo> list =new ArrayList<>();
        for (StudentInfo baseInfo : baseInfos) {
            if((baseInfo.getStudentKind().contains("重修"))){
                list.add(baseInfo);
            }
        }
        return list;
    }

    //创建导出教务系统导入模板的writer
    public ExcelWriter getAcademicSystemTemplateWriter(List<StudentInfo> tar){
        ExcelWriter writer=ExcelUtil.getWriter(true);
        writer.addHeaderAlias("id","学号");
        writer.addHeaderAlias("name","姓名");
        writer.addHeaderAlias("studentKind","学生类别");
        writer.addHeaderAlias("className","班级名称");
        writer.addHeaderAlias("score","总评成绩");
        writer.addHeaderAlias("remark","备注");
        writer.setOnlyAlias(true);
        writer.write(tar,true);
        return writer;
    }

    //创建导出输入课堂表现excel的writer
    public ExcelWriter InputClassPerformFileWriter(List<UsualScore> tar){
        for (UsualScore usualScore : tar) {
            usualScore.setClassPerform(classPerformDefaultScore);
        }
        ExcelWriter writer=ExcelUtil.getWriter(true);
        writer.addHeaderAlias("id","学号");
        writer.addHeaderAlias("name","姓名");
        writer.addHeaderAlias("studentKind","学生类别");
        writer.addHeaderAlias("className","班级名称");
        writer.addHeaderAlias("classPerform","课堂表现");
        writer.setOnlyAlias(true);
        writer.write(tar,true);
        return writer;
    }

    public ExcelWriter InputOnlineLearningFileWriter(List<UsualScore> tar){
        for (UsualScore usualScore : tar) {
            usualScore.setOnlineLearning(onlineLearningDefaultScore);
        }
        ExcelWriter writer=ExcelUtil.getWriter(true);
        writer.addHeaderAlias("id","学号");
        writer.addHeaderAlias("name","姓名");
        writer.addHeaderAlias("studentKind","学生类别");
        writer.addHeaderAlias("className","班级名称");
        writer.addHeaderAlias("onlineLearning","在线学习");
        writer.setOnlyAlias(true);
        writer.write(tar,true);
        return writer;
    }

    public ExcelWriter InputExperimentFileWriter(List<UsualScore> tar){
        for (UsualScore usualScore : tar) {
            usualScore.setOnlineLearning(experimentDefaultScore);
        }
        ExcelWriter writer=ExcelUtil.getWriter(true);
        writer.addHeaderAlias("id","学号");
        writer.addHeaderAlias("name","姓名");
        writer.addHeaderAlias("studentKind","学生类别");
        writer.addHeaderAlias("className","班级名称");
        writer.addHeaderAlias("experiment","实验");
        writer.setOnlyAlias(true);
        writer.write(tar,true);
        return writer;
    }

    public ExcelWriter usualScoreWriter(List<UsualScore> tar){
        ExcelWriter writer=ExcelUtil.getWriter(true);
        writer.addHeaderAlias("id","学号");
        writer.addHeaderAlias("name","姓名");
        writer.addHeaderAlias("studentKind","学生类别");
        writer.addHeaderAlias("className","班级名称");
        writer.addHeaderAlias("classPerform","课堂表现");
        writer.addHeaderAlias("experiment","作业");
        writer.addHeaderAlias("onlineLearning","在线学习");
        writer.addHeaderAlias("experiment","实验");
        writer.setOnlyAlias(true);
        writer.write(tar,true);
        return writer;
    }
    public void doWrite(ExcelWriter writer,HttpServletResponse response){
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=fileName" + ".xlsx");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.flush(out,true);
        writer.close();
        IoUtil.close(out);
    }

    //导出正常学生成绩表
    public void  writeNormal(HttpServletResponse response){
        List<StudentInfo> studentInfos = getStudentInfos(baseInfos);
        ExcelWriter writer = getAcademicSystemTemplateWriter(studentInfos);
        doWrite(writer,response);
    }

    //导出重修学生成绩表
    public void writeReBuild(HttpServletResponse response){
        List<StudentInfo> reBuildStudents = getReBuildStudents(baseInfos);
        ExcelWriter writer = getAcademicSystemTemplateWriter(reBuildStudents);
        doWrite(writer,response);
    }
    //置空baseInfos
    public void initBaseInfos() {
        this.baseInfos=new ArrayList<>();
    }

    /**
     * 填充作业成绩
     * @param path 路径
     * @param list 已经填充完基本信息的List<UsualScore>
     * @return 填充了homeworkScore的List<UsualScore>
     */
    public List<UsualScore> getHomeWorkScore(String path, List<UsualScore> list){
        ExcelReader reader = ExcelUtil.getReader(path);
        List<List<Object>> sheet = reader.read();
        //舍弃头部和尾部不需要的信息，老师给的前4行和最后一行要去掉
        sheet.remove(0);
        sheet.remove(0);
        //自行判断有几次作业
        List<Object> objects = sheet.get(0);
        Integer homeWorkFrequency = getHomeWorkFrequency(objects);
        //找出成绩列表的下标数组，数组长度即为作业次数
        sheet.remove(0);
        List<Object> objects1 = sheet.get(0);
        List<Integer> scoreSubIndexList = getScoreSubIndexList(objects1);
        sheet.remove(0);
        //根据学号升序
        sheet.sort(new Comparator<List<Object>>() {
            @Override
            public int compare(List<Object> o1, List<Object> o2) {
                return SortUtil.doSort(o1, o2, 1);
            }
        });
        for (int i = 0; i < sheet.size(); i++) {
            Double homeWorkScore;
            double total =0;
            for (Integer each : scoreSubIndexList) {
                Object temp = sheet.get(i).get(each);
                double eachScore=0;
                if(temp.toString().equals("")||temp==null){
                    continue;
                }
                eachScore = Double.parseDouble(temp.toString());
                total+=eachScore;
            }
            //四舍五入获取该学生成绩
            long round = Math.round(total / homeWorkFrequency);
            //再学生成绩表对应的位置填上他的成绩
            list.get(i).setHomeworkScore(round);
        }
        return list;
    }

    //判断有几次作业
    public Integer getHomeWorkFrequency(List<Object> objects){
        //作业次数
        Integer ans =0;
        for (Object object : objects) {
            if (object.toString().contains("作业")){
                ans++;
            }
        }
        return ans;
    }
    //找出成绩列表的下标数组
    public List<Integer> getScoreSubIndexList(List<Object> objects){
        List<Integer> ans =new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            if(objects.get(i).toString().contains("成绩")){
                ans.add(i);
            }
        }
        return ans;
    }

    /**
     *获取课堂表现分添加到总列表
     * @param path
     * @param list
     */
    public List<UsualScore> getClassPerformScore(String path,List<UsualScore> list) throws Exception {
        ExcelReader reader = ExcelUtil.getReader(path);
        List<List<Object>> sheet = reader.read();
        //找到字段为课堂表现的下标并标记
        Integer classPerformSubIndex=null;
        for (List<Object> objects : sheet) {
            for (int i = 0; i < objects.size(); i++) {
                if (objects.get(i).toString().contains("课堂成绩")){
                    classPerformSubIndex=i;
                    break;
                }
            }
        }
        if(classPerformSubIndex==null){
            throw new Exception("没有课堂成绩字段");
        }
        for (int i = 0; i < sheet.size(); i++) {
            List<Object> objects = sheet.get(i);
            Object classPerformScore = objects.get(classPerformSubIndex);
            list.get(i).setClassPerform((Long) classPerformScore);
        }
        return list;
    }

    /**
     * 获取在线学习分添加到总列表
     * @param path
     * @param list
     */
    public List<UsualScore> getOnlineLearningScore(String path,List<UsualScore> list) throws Exception {
        ExcelReader reader = ExcelUtil.getReader(path);
        List<List<Object>> sheet = reader.read();
        //找到字段为课堂表现的下标并标记
        Integer OnlineLearningSubIndex=null;
        for (List<Object> objects : sheet) {
            for (int i = 0; i < objects.size(); i++) {
                if (objects.get(i).toString().contains("在线学习")){
                    OnlineLearningSubIndex=i;
                    break;
                }
            }
        }
        if(OnlineLearningSubIndex==null){
            throw new Exception("没有在线学习字段");
        }
        for (int i = 0; i < sheet.size(); i++) {
            List<Object> objects = sheet.get(i);
            Object classPerformScore = objects.get(OnlineLearningSubIndex);
            list.get(i).setClassPerform((Long) classPerformScore);
        }
        return list;
    }

    /**
     *获取实验分添加到总列表
     * @param path
     * @param list
     */
    public List<UsualScore> getExperimentScore(String path,List<UsualScore> list) throws Exception {
        ExcelReader reader = ExcelUtil.getReader(path);
        List<List<Object>> sheet = reader.read();
        //找到字段为课堂表现的下标并标记
        Integer ExperimentSubIndex=null;
        for (List<Object> objects : sheet) {
            for (int i = 0; i < objects.size(); i++) {
                if (objects.get(i).toString().contains("实验")){
                    ExperimentSubIndex=i;
                    break;
                }
            }
        }
        if(ExperimentSubIndex==null){
            throw new Exception("实验");
        }
        for (int i = 0; i < sheet.size(); i++) {
            List<Object> objects = sheet.get(i);
            Object classPerformScore = objects.get(ExperimentSubIndex);
            list.get(i).setClassPerform((Long) classPerformScore);
        }
        return list;
    }


    public void writeUsualScore(HttpServletResponse response,List<UsualScore> list) {
        ExcelWriter writer = usualScoreWriter(list);
        doWrite(writer,response);
    }

    public void getInputClassPerformFile(HttpServletResponse response,List<UsualScore>tar ) {
        ExcelWriter writer = InputClassPerformFileWriter(tar);
        doWrite(writer,response);
    }

    public void getInputOnlineLearningFile(HttpServletResponse response,List<UsualScore>tar ) {
        ExcelWriter writer = InputOnlineLearningFileWriter(tar);
        doWrite(writer,response);
    }

    public void getInputExperimentFile(HttpServletResponse response,List<UsualScore>tar ) {
        ExcelWriter writer = InputExperimentFileWriter(tar);
        doWrite(writer,response);
    }
}
