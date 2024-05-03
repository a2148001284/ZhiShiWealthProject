package com.zhouy.login.service;

import com.zhouy.login.dao.pojo.Class;
import com.zhouy.login.model.Result;

public interface ClassService {

    /**
     * 添加班级
     * @param newClass 班级
     * @return  Result
     */
    Result addClass(Class newClass);

    /**
     * 删除班级
     * @param delId 要删除的班级
     * @return 删除结果
     */
    Result deleteClass(Integer delId);

    /**
     * 根据用户id,
     * @param userId 用户id
     * @return  该用户的班级
     */
    Result getClassByUserId(Integer userId);

}
