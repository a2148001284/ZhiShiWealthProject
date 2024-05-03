package com.zhouy.login.controller;

import com.zhouy.login.dao.pojo.Class;
import com.zhouy.login.model.Result;
import com.zhouy.login.service.ClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "班级接口")

@RestController
public class ClassController {

    @Autowired
    private ClassService classService;

    @ApiOperation("添加班级")
    @PostMapping("/addClass")
    public Result addClass(@RequestBody Class newClass){
        return classService.addClass(newClass);
    }

    @ApiOperation("删除班级")
    @GetMapping("/deleteClass/{delId}")
    public Result deleteClass(@PathVariable Integer delId){
        return classService.deleteClass(delId);
    }

    @ApiOperation("获取user对应班级")
    @GetMapping("/getClasses/{userId}")
    public Result getClassByUserId(@PathVariable Integer userId){
        return classService.getClassByUserId(userId);
    }

}
