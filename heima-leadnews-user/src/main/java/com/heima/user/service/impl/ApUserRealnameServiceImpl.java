package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.user.dtos.AuthDto;
import com.heima.model.user.pojos.ApUserRealname;
import com.heima.user.mapper.ApUserRealnameMapper;
import com.heima.user.service.ApUserRealnameService;
import org.springframework.stereotype.Service;

@Service
public class ApUserRealnameServiceImpl extends ServiceImpl<ApUserRealnameMapper, ApUserRealname> implements ApUserRealnameService {
    /**
     * 根据状态查询需要认证相关的用户信息
     *
     * @param dto
     * @return
     */
    @Override
    public PageResponseResult loadListByStatus(AuthDto dto) {
        //设置默认页数
        dto.checkParam();

        QueryWrapper<ApUserRealname> queryWrapper = new QueryWrapper<>();
        if (dto.getStatus() != null) {
            //使用适配器的eq方法,查询dto中与表匹配的数据
            queryWrapper.lambda().eq(ApUserRealname::getStatus,dto.getStatus());
        }
        //初始化分页查询参数
        IPage pageParam = new Page(dto.getPage(), dto.getSize());
        //分页查询符合条件的数据
        IPage page = page(pageParam, queryWrapper);
        //分页查询结果
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int)page.getTotal());
        responseResult.setCode(0);
        responseResult.setData(page.getRecords());

        return responseResult;
    }
}
