package com.zhouy.login.service;

import com.zhouy.login.model.Result;
import com.zhouy.login.model.UsualScore;
import com.zhouy.login.params.StudentKindParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface UploadService {
    /**
     * 将上传文件写入本地
     * @param files 上传文件
     * @param response 响应
     */
    void FileUpload(MultipartFile[] files, HttpServletResponse response) throws IOException;

    /**
     * 获取正常学生的excel
     * @param response 响应
     */
    void getFile(HttpServletResponse response);

    /**
     * 获取不同类型学生的总分比例
     * @param param 参数
     */
    Result getStudentKindProportion(StudentKindParam param);

    /**
     * 获取重修学生的excel
     * @param response 写回的响应
     */
    void getReBuildStuFile(HttpServletResponse response);

    /**
     * 上传学习通导出的作业excle
     * @param file 上传文件
     * @param response 写回的响应
     */
    void homeworkUpload(MultipartFile file, HttpServletResponse response) throws IOException;

    /**
     * 获取平时成绩表
     * @param response 写回的响应
     */
    void getUsualScoreFile(HttpServletResponse response);

    /**
     * 获取默认空的输入课堂表现表
     * @param response 写回的响应
     */
    void getInputClassPerformFile(HttpServletResponse response);

    /**
     * 获取默认的在线学习表
     * @param response 写回的响应
     */
    void getInputOnlineLearningFile(HttpServletResponse response);

    /**
     * 获取默认的实验表
     * @param response 写回的响应
     */
    void getInputExperimentFile(HttpServletResponse response);

    /**
     * 单独上传点名册
     * @param file 点名册
     */
    void uploadRollCallFile(MultipartFile file) throws IOException;

    /**
     * 上传正确的课堂表现表
     */
    void uploadInputClassPerformFile(MultipartFile file) throws IOException;
    /**
     * 上传正确的在线学习表
     */
    void uploadInputOnlineLearningFile(MultipartFile file) throws IOException;
    /**
     * 上传正确的实验表
     */
    void uploadInputExperimentFile(MultipartFile file) throws IOException;
}
