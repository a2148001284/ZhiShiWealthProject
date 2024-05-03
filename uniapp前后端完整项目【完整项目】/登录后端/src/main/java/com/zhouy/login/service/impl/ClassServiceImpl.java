package com.zhouy.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhouy.login.dao.mapper.ClassMapper;
import com.zhouy.login.dao.pojo.Class;
import com.zhouy.login.model.Result;
import com.zhouy.login.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Resource
    private ClassMapper classMapper;

    @Override
    public Result addClass(Class newClass) {
//        newClass.setTeacherId();
        int insert = classMapper.insert(newClass);
        if(insert<=0){
            return Result.fail("插入失败",208);
        }
        return Result.success(newClass,"添加成功");
    }

    @Override
    public Result deleteClass(Integer delId) {
        LambdaQueryWrapper<Class> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(Class::getId,delId);
        int delete = classMapper.delete(queryWrapper);
        if(delete<=0){
            return Result.fail("删除失败",209);
        }
        return Result.success(delId,"删除成功");
    }

    @Override
    public Result getClassByUserId(Integer userId) {
        LambdaQueryWrapper<Class> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(Class::getTeacherId,userId);
        List<Class> classes = classMapper.selectList(queryWrapper);
        if(classes.size()<=0){
            return Result.fail("获取失败,暂无班级",300);
        }
        return Result.success(classes,"获取成功");
    }

}
