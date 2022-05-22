package com.heima.user.feign;

import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 文章作者feign接口,用于通过审核后,创建相应的文章作者
 */
@FeignClient("leadnews-article")
public interface ArticleFeign {
    /**
     * 通过id查询文章作者
     * @param id
     * @return
     */
    @GetMapping("/api/v1/author/findByUserId/{id}")
    public ApAuthor findByUserId(@PathVariable("id")Integer id);

    /**
     * 创建文章作者
     * @param apAuthor
     * @return
     */
    @PostMapping("/api/v1/author/save")
    public ResponseResult save(@RequestBody ApAuthor apAuthor);
}
