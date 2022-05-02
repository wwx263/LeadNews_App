package com.heima.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.admin.pojos.AdChannel;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 *频道数据的持久层 直接和数据库连接,连接的数据;这些实体类是和数据库打交道的,类似dao,此处命名不规范
 */
@Mapper
public interface AdChannelMapper extends BaseMapper<AdChannel> {
}
