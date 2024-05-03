package com.zhouy.login.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.zhouy.login.dao.mapper.UsualScoreRecordMapper;
import com.zhouy.login.dao.pojo.ClassInfo;
import com.zhouy.login.dao.pojo.UsualScoreRecord;
import com.zhouy.login.model.Result;
import com.zhouy.login.model.StudentInfo;
import com.zhouy.login.model.UsualScore;
import com.zhouy.login.params.StudentKindParam;
import com.zhouy.login.service.UploadService;
import com.zhouy.login.util.excel.MyExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    //新建D:/upload用于接受上传的excel
    //竟然用错注解了,人麻了
   @org.springframework.beans.factory.annotation.Value("${getTarTemplatePath}")
    private  String UPLOADED_FOLDER;

    @Value("${getUsualScorePath}")
    private String USUAL_SCORE_PATH;

    @Resource
    private MyExcelUtil myExcelUtil;

    @Resource
    private UsualScoreRecordMapper usualScoreRecordMapper;

    private  String usualScorePath;

    private  String finalScorePath;

    private List<UsualScore> List=new ArrayList<>();

    @Override
    public void FileUpload(MultipartFile[] files, HttpServletResponse response) throws IOException {
        for (MultipartFile file : files) {
            log.info(file.getOriginalFilename());
        }

        for (MultipartFile file : files) {
            byte[] bytes = file.getBytes();
            Path dir = Paths.get(UPLOADED_FOLDER);
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
            }
            Files.write(path, bytes);
        }
    }

    @Override
    public void getFile(HttpServletResponse response) {
        myExcelUtil.writeNormal(response);
//        deleteFile();
    }

    @Override
    public void getReBuildStuFile(HttpServletResponse response) {
        myExcelUtil.writeReBuild(response);
    }

    //前提已经获取了点名册
    @Override
    public void getInputClassPerformFile(HttpServletResponse response) {
        if(List.size()>0){
            myExcelUtil.getInputClassPerformFile(response,List);
        }
        else{
            log.warn("你尚未输入点名册,或系统读取点名册出错");
        }
    }

    @Override
    public void getInputOnlineLearningFile(HttpServletResponse response) {
        if(List.size()>0){
            myExcelUtil.getInputOnlineLearningFile(response,List);
        }
        else{
            log.warn("你尚未输入点名册,或系统读取点名册出错");
        }
    }

    @Override
    public void getInputExperimentFile(HttpServletResponse response) {
        if(List.size()>0){
            myExcelUtil.getInputExperimentFile(response,List);
        }
        else{
            log.warn("你尚未输入点名册,或系统读取点名册出错");
        }
    }

    //最后一步执行
    @Override
    public void getUsualScoreFile(HttpServletResponse response) {
        myExcelUtil.writeUsualScore(response,List);
        UsualScoreRecord usualScoreRecord;
        for (UsualScore usualScore : List) {
            ClassInfo classInfo = new ClassInfo();
            classInfo.setId(Integer.parseInt(usualScore.getId()));
            classInfo.setStuName(usualScore.getName());
            classInfo.setClassName(usualScore.getClassName());
            classInfo.setKind(usualScore.getStudentKind());
            classInfo.setFirstPart(usualScore.getClassPerform().doubleValue());
            classInfo.setSecondPart(usualScore.getHomeworkScore().doubleValue());
            classInfo.setThirdPart(usualScore.getOnlineLearning().doubleValue());
            classInfo.setFourthPart(usualScore.getExperiment().doubleValue());
        }
    }


    @Override
    public void uploadRollCallFile(MultipartFile file) throws IOException {
        Path path = Paths.get(USUAL_SCORE_PATH + file.getOriginalFilename());
        byte[] bytes = file.getBytes();
        Files.write(path,bytes);
        List<UsualScore> list =new ArrayList<>();
        List= myExcelUtil.getRollCallContent(path.toString(), list);
    }

    @Override
    public void homeworkUpload(MultipartFile file, HttpServletResponse response) throws IOException {
        Path path = Paths.get(USUAL_SCORE_PATH + file.getOriginalFilename());
        byte[] bytes = file.getBytes();
        Files.write(path,bytes);
        List =myExcelUtil.getHomeWorkScore(path.toString(),List);
    }

    @Override
    public void uploadInputClassPerformFile(MultipartFile file) throws IOException {
        Path path = Paths.get(USUAL_SCORE_PATH + file.getOriginalFilename());
        byte[] bytes = file.getBytes();
        Files.write(path,bytes);
        try {
            List=myExcelUtil.getClassPerformScore(path.toString(),List);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uploadInputOnlineLearningFile(MultipartFile file) throws IOException {
        Path path = Paths.get(USUAL_SCORE_PATH + file.getOriginalFilename());
        byte[] bytes = file.getBytes();
        Files.write(path,bytes);
        try {
            List=myExcelUtil.getOnlineLearningScore(path.toString(),List);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uploadInputExperimentFile(MultipartFile file) throws IOException {
        Path path = Paths.get(USUAL_SCORE_PATH + file.getOriginalFilename());
        byte[] bytes = file.getBytes();
        Files.write(path,bytes);
        try {
            List =myExcelUtil.getExperimentScore(path.toString(),List);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(){
        File file =new File(UPLOADED_FOLDER);
        File[] files = file.listFiles();
        for (File file1 : files) {
            boolean delete = file1.delete();
        }
    }

    @Override
    public Result getStudentKindProportion(StudentKindParam param) {
        myExcelUtil.initBaseInfos();
        initMyExcelUtil();
        return myExcelUtil.judgeKind(param);
    }


    public void initMyExcelUtil(){
        File file =new File(UPLOADED_FOLDER);
        File[] files = file.listFiles();
        for (File path : files) {
            if(path.getName().contains("点名册")){
                myExcelUtil.getRollCallContent(path.toString());
//                log.info("1:"+myExcelUtil.getRollCallContent(path.toString()));
            }
            else if(path.getName().contains("机考")||path.getName().contains("期末")){
                finalScorePath= String.valueOf(path);
                myExcelUtil.getFinalExams(path.toString());
//                log.info("2:"+myExcelUtil.getFinalExams(path.toString()));
            }
            else if(path.getName().contains("平时")){
                usualScorePath= String.valueOf(path);
                myExcelUtil.getUsualScore(path.toString());
//                log.info("3:"+myExcelUtil.getUsualScore(path.toString()));
            }
        }
        //先完成对上面接受excel内容的组装
        //再写入Servlet输出流中
        myExcelUtil.getScore(usualScorePath,finalScorePath);
    }
}
