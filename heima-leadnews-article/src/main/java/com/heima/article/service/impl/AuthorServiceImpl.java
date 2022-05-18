package com.heima.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.AuthorMapper;
import com.heima.article.service.AuthorService;
import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, ApAuthor>implements AuthorService {


    @Override
    public ApAuthor findByUserId(Integer id) {
        ApAuthor byId = getById(id);

        return byId;
    }

    @Override
    public ResponseResult insert(ApAuthor apAuthor) {
        boolean save = save(apAuthor);

        return ResponseResult.okResult(save);
    }
}
