package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.media.pojos.WmUser;
import com.heima.wemedia.mapper.WmUserMapper;
import com.heima.wemedia.service.WmUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class WmUserServiceImpl extends ServiceImpl<WmUserMapper, WmUser> implements WmUserService {
    /**
     * 保存自媒体
     * @param wmUser
     * @return
     */
    @Override
    public ResponseResult insert(WmUser wmUser) {
        boolean save = save(wmUser);
        if (save){
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST,"保存失败,可能已经存在该频道");
    }

    /**
     * 查询自媒体
     * @param name
     * @return
     */
    @Override
    public WmUser findByName(String name) {
        QueryWrapper<WmUser> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)){
            queryWrapper.lambda().eq(WmUser::getName,name);
        }
        List<WmUser> list = list(queryWrapper);
        if (!list.isEmpty() && list != null){
            return list.get(0);
        }
        return null;
    }
}
