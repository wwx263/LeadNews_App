package com.heima.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.admin.pojos.AdSensitive;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 用于查询数据库中的敏感词汇
 */
public interface AdSensitiveMapper extends BaseMapper<AdSensitive> {

}
