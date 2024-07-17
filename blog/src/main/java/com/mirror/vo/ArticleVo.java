package com.mirror.vo;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
@Data
public class ArticleVo{

    /**
     * 标题
     */
    @NotBlank(message = "文章标题不能为空")
    private String title;

    /**
     * 内容
     */
    @NotBlank(message = "文章内容不能为空")
    private String content;


}