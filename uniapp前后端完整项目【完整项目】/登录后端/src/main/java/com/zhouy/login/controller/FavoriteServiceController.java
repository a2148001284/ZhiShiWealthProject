package com.zhouy.login.controller;

import com.zhouy.login.dao.pojo.Favorite;
import com.zhouy.login.model.Result;
import com.zhouy.login.service.FavoriteService;
import com.zhouy.login.vo.FavoriteVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "红星接口")
@RestController
public class FavoriteServiceController {

    @Autowired
    private FavoriteService favoriteService;

    @ApiOperation("添加收藏的股票")
    @PostMapping("/addFavorite")
    public Result addFavorite(@RequestBody Favorite favorite){
        return favoriteService.addFavorite(favorite);
    }

    @ApiOperation("根据用户id查询所有的收藏")
    @GetMapping("/showFavoriteListById/{userId}")
    public Result listFavorite(@PathVariable String userId){
        return favoriteService.listFavorite(userId);
    }

    @ApiOperation("根据用户id和要删除的股票编号删除收藏")
    @PostMapping("/deleteFavoriteById")
    public Result deleteFavorite(@RequestBody FavoriteVo favoriteVo){
        return favoriteService.deleteFavorite(favoriteVo);
    }
}
