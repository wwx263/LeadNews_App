package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.media.pojos.WmUser;

public interface WmUserService extends IService<WmUser> {
    /**
     * 保存自媒体
     * @param wmUser
     * @return
     */
    public ResponseResult insert(WmUser wmUser);

    /**
     * 查询自媒体
     * @param name
     * @return
     */
    public WmUser findByName(String name);
}
