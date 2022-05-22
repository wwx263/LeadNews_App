package com.heima.user.feign;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.media.pojos.WmUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 自媒体的feign接口,这里由用户模块调用,通过审核后用于创建自媒体频道
 */
@FeignClient("leadnews-wemedia")
public interface WemediaFeign {
    @PostMapping("/api/v1/user/save")
    public ResponseResult save(@RequestBody WmUser wmUser);

    @GetMapping("/api/v1/user/findByName/{name}")
    public ResponseResult findByName(@PathVariable("name") String name);
}
