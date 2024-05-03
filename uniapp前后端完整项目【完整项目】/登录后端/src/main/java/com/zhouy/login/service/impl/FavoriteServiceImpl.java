package com.zhouy.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhouy.login.dao.mapper.FavoriteMapper;
import com.zhouy.login.dao.pojo.Favorite;
import com.zhouy.login.model.Result;
import com.zhouy.login.service.FavoriteService;
import com.zhouy.login.vo.FavoriteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    @Resource
    private FavoriteMapper favoriteMapper;

    @Override
    public Result addFavorite(Favorite favorite) {
        int insert = favoriteMapper.insert(favorite);
        return Result.success(favorite,"添加成功");
    }

    @Override
    public Result listFavorite(String userId) {
        LambdaQueryWrapper<Favorite> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(Favorite::getUserId,userId);
        List<Favorite> favorites = favoriteMapper.selectList(queryWrapper);
        return Result.success(favorites,"红星列表");
    }

    @Override
    public Result deleteFavorite(FavoriteVo favoriteVo) {
        LambdaQueryWrapper<Favorite> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(Favorite::getItemId,favoriteVo.getItemId());
        queryWrapper.eq(Favorite::getUserId,favoriteVo.getUserId());
        int delete = favoriteMapper.delete(queryWrapper);
        if(delete<=0){
            return Result.fail("删除失败",203);
        }
        return Result.success(delete,"删除成功");
    }


}
