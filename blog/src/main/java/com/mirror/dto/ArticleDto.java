package com.mirror.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ArticleDto {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;
    /**
     * 创建人
     */
    private String username;
    /**
     * 创建时间
     */
    private LocalDateTime created;
    /**
     * 修改时间
     */
    private LocalDateTime lastModified;

}