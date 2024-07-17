package com.mirror.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @TableName article
 */
@TableName(value ="article")
@Data
@Builder
public class Article implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer postId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 作者id
     */
    private Integer userId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime created;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime lastModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}