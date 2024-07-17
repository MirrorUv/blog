package com.mirror.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mirror.anno.RequireLogin;
import com.mirror.common.Result;
import com.mirror.common.ResultEnum;
import com.mirror.dto.ArticleDto;
import com.mirror.pojo.Article;
import com.mirror.service.ArticleService;
import com.mirror.vo.ArticleVo;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @GetMapping("/{id}")
    public Result<ArticleDto> getInfoById(@PathVariable int id){
        try {
            ArticleDto articleVo = articleService.getInfoById(id);
            log.info("文章id{},获取文章成功",id);
            return Result.<ArticleDto>builder().code(ResultEnum.SUCCESS.getCode())
                    .message(ResultEnum.SUCCESS.getMessage()).data(articleVo).build();
        } catch (Exception e) {
            log.info("文章id{},获取文章失败",id);
            return Result.<ArticleDto>builder().code(ResultEnum.ERROR.getCode())
                    .message(ResultEnum.ERROR.getMessage()).build();
        }
    }
    @PostMapping()
    @RequireLogin
    public Result<Boolean> createArticle(@RequestBody @Validated ArticleVo articleVo){
        try {
            boolean res =articleService.addArticle(articleVo);
            log.info("创建文章成功");
            return Result.<Boolean>builder().code(ResultEnum.SUCCESS.getCode())
                    .message(ResultEnum.SUCCESS.getMessage()).data(res).build();
        } catch (Exception e) {
            log.info("创建文章失败");
            return Result.<Boolean>builder().code(ResultEnum.CREATE_ARTICLE_ERROR.getCode())
                    .message(ResultEnum.CREATE_ARTICLE_ERROR.getMessage()).build();
        }
    }

    @PutMapping("/{id}")
    @RequireLogin
    public Result<Boolean> updateArticle(@PathVariable int id,@RequestBody @Validated ArticleVo articleVo){
        try {
            boolean res = articleService.updateArticle(id,articleVo);
            if (!res){
                log.info("文章id{}更新失败",id);
                return Result.<Boolean>builder().code(ResultEnum.UPDATE_ARTICLE_ERROR.getCode())
                        .message(ResultEnum.UPDATE_ARTICLE_ERROR.getMessage()).data(res).build();
            }
            log.info("文章id{}更新成功",id);
            return Result.<Boolean>builder().code(ResultEnum.SUCCESS.getCode())
                    .message(ResultEnum.SUCCESS.getMessage()).data(res).build();
        } catch (Exception e) {
            log.info("文章id：{}更新失败,文章不存在或不属于此人",id);
            return Result.<Boolean>builder().code(ResultEnum.UPDATE_ARTICLE_ERROR.getCode())
                    .message(ResultEnum.UPDATE_ARTICLE_ERROR.getMessage()).data(false).build();
        }
    }
    @GetMapping()
    public Result<List<ArticleDto>> queryArticleByUid(@RequestParam int uid,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestParam(defaultValue = "asc") String sort){
        try {
            List<ArticleDto> articleDto = articleService.queryArticleByUid(uid,page,size,sort);
            log.info("用户id:{},文章查询成功",uid);
            return Result.<List<ArticleDto>>builder().code(ResultEnum.SUCCESS.getCode())
                    .message(ResultEnum.SUCCESS.getMessage()).data(articleDto).build();
        } catch (Exception e) {
            log.info("用户id:{},文章查询失败",uid);
            return Result.<List<ArticleDto>>builder().code(ResultEnum.QUERY_ARTICLE_ERROR.getCode())
                    .message(ResultEnum.QUERY_ARTICLE_ERROR.getMessage()).build();
        }
    }

    @DeleteMapping("/{id}")
    @RequireLogin
    public Result<Boolean> updateArticle(@PathVariable int id){
        try {
            boolean res = articleService.deleteArticle(id);
            if (!res){
                log.info("文章id{}更新失败",id);
                return Result.<Boolean>builder().code(ResultEnum.DELETE_ARTICLE_ERROR.getCode())
                        .message(ResultEnum.DELETE_ARTICLE_ERROR.getMessage()).data(res).build();
            }
            log.info("文章id{}更新成功",id);
            return Result.<Boolean>builder().code(ResultEnum.SUCCESS.getCode())
                    .message(ResultEnum.SUCCESS.getMessage()).data(res).build();
        } catch (Exception e) {
            log.info("文章id：{}更新失败,文章不存在或不属于此人",id);
            return Result.<Boolean>builder().code(ResultEnum.DELETE_ARTICLE_ERROR.getCode())
                    .message(ResultEnum.DELETE_ARTICLE_ERROR.getMessage()).data(false).build();
        }
    }
}
