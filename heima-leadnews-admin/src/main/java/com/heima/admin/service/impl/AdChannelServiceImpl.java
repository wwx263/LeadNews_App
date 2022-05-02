package com.heima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.admin.mapper.AdChannelMapper;
import com.heima.admin.service.AdChannelService;
import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 这个service注解写在哪里,请求就往哪里走,一般还有写在interface类当中的
 * 这里使用到了mybatisplus的服务,所以需要继承对应的serviceImpl<XXXMapper,xxx(对应实体类)>
 */
@Service
public class AdChannelServiceImpl extends ServiceImpl<AdChannelMapper, AdChannel> implements AdChannelService {

    /**
     * 自定义页数来查询数据,page 1 size 10 ; 来根据dto 模糊查询 AdChannel
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findByNameAndPage(ChannelDto dto) {

        //1.参数检测
        if (null == dto) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //分页参数检查,如果没有设置页数,则自动设置
        dto.checkParam();

        //2.安装名称模糊分页查询
        Page page = new Page(dto.getPage(),dto.getSize());
        //查询条件构造器
        LambdaQueryWrapper<AdChannel> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(dto.getName())) {
            lambdaQueryWrapper.like(AdChannel::getName,dto.getName());
        }
        //page方法来自ServiceImpl,用于执行分页查询:按照条件构造器,pag参数
        IPage result = page(page, lambdaQueryWrapper);

        //3.结果封装
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(),dto.getSize(),(int)result.getTotal());

        return responseResult;
    }
}
