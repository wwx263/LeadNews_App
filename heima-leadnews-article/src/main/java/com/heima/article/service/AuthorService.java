package com.heima.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.common.dtos.ResponseResult;

/**
 * 文章作者服务
 */
public interface AuthorService extends IService<ApAuthor> {

    public ApAuthor findByUserId(Integer id);

    public ResponseResult insert(ApAuthor apAuthor);

    }
