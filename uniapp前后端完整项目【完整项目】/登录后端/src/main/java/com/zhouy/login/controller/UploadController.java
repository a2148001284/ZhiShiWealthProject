package com.zhouy.login.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.zhouy.login.model.Result;
import com.zhouy.login.params.StudentKindParam;
import com.zhouy.login.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Api(tags = "上传文件")
@RestController
@Slf4j
public class UploadController {

    @Resource
    private UploadService uploadService;

    @GetMapping("/")
    public String index()
    {
        return "upload";
    }

    @ApiOperation("接收一组文件")
    @PostMapping("/upload")
    public void  singleFileUpload(@RequestParam("files") MultipartFile[] files, HttpServletResponse response) throws IOException {
        uploadService.FileUpload(files,response);
    }

    @ApiOperation("接受上传的学习通导出成绩")
    @PostMapping("/uploadHomework")
    public void  uploadHomework(MultipartFile  file, HttpServletResponse response) throws IOException {
        uploadService.homeworkUpload(file,response);
    }

    @ApiOperation("获取平时成绩的excel")
    @GetMapping("/getUsualScoreFile")
    public void getUsualScoreFile(HttpServletResponse response){
        uploadService.getUsualScoreFile(response);
    }

    @ApiOperation("获取正常学生的Excel")
    @GetMapping("/getStudentFile")
    public void getFile(HttpServletResponse response){
            uploadService.getFile(response);
    }

    @ApiOperation("获取重修学生的excel")
    @GetMapping("/getReBuildStudentFile")
    public void getReBuildStuFile(HttpServletResponse response){
        uploadService.getReBuildStuFile(response);
    }


    @ApiOperation("获取不同学生总评比例")
    @PostMapping("/uploadProportion")
    public Result getProportion(@RequestBody StudentKindParam param){
        return uploadService.getStudentKindProportion(param);
    }


    @ApiOperation("获取课堂表现输入的Excel")
    @GetMapping("/getInputClassPerformFile")
    public void getInputClassPerformFile(HttpServletResponse response){
        uploadService.getInputClassPerformFile(response);
    }


    @ApiOperation("获取在线学习输入的Excel")
    @GetMapping("/getInputOnlineLearningFile")
    public void getInputOnlineLearningFile(HttpServletResponse response){
        uploadService.getInputOnlineLearningFile(response);
    }


    @ApiOperation("获取实验成绩输入的Excel")
    @GetMapping("/getInputExperimentFile")
    public void getInputExperimentFile(HttpServletResponse response){
        uploadService.getInputExperimentFile(response);
    }

    @ApiOperation("上传课堂表现的Excel")
    @GetMapping("/uploadInputClassPerformFile")
    public void uploadInputClassPerformFile(MultipartFile file) throws IOException {
        uploadService.uploadInputClassPerformFile(file);
    }


    @ApiOperation("上传在线学习输入的Excel")
    @GetMapping("/uploadInputOnlineLearningFile")
    public void uploadInputOnlineLearningFile(MultipartFile file) throws IOException {
        uploadService.uploadInputOnlineLearningFile(file);
    }


    @ApiOperation("上传实验成绩输入的Excel")
    @GetMapping("/uploadInputExperimentFile")
    public void uploadInputExperimentFile(MultipartFile file) throws IOException {
        uploadService.uploadInputExperimentFile(file);
    }

    @ApiOperation("上传点名册excel")
    @GetMapping("/uploadRollCallFile")
    public void uploadRollCallFile(MultipartFile file) throws IOException {
        uploadService.uploadRollCallFile(file);
    }

}
