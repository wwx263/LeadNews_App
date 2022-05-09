package com.heima.model.admin.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/*
*频道的实体类对象
* */
@Data
@TableName("ad_channel")
public class AdChannel implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 频道名称
     */
    @ApiModelProperty("频道名称")
    @TableField("name")
    private String name;

    /**
     * 频道描述
     */
    @ApiModelProperty("频道描述")
    @TableField("description")
    private String description;

    /**
     * 是否默认频道
     */
    @TableField("is_default")
    @ApiModelProperty("是否默认频道")
    private Boolean isDefault;

    @TableField("status")
    @ApiModelProperty("状态(bool)")
    private Boolean status;

    /**
     * 默认排序
     */
    @ApiModelProperty("默认排序")
    @TableField("ord")
    private Integer ord;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;

}
