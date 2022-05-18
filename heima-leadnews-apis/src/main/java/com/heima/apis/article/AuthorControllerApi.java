package com.heima.apis.article;

import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "文章作者",tags = "Author",description = "文章作者API")
public interface AuthorControllerApi {

    /**
     *根据用户id查询作者信息
     * @param id
     * @return
     */
    @ApiOperation("根据用户id查询作者信息")
    public ApAuthor findByUserId(@PathVariable("id") Integer id);

    /**
     * 保存作者
     * @param apAuthor
     * @return
     */
    @ApiOperation("保存作者")
    public ResponseResult save(@RequestBody ApAuthor apAuthor);
}
