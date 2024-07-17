package com.mirror.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mirror.dto.ArticleDto;
import com.mirror.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mirror.vo.ArticleVo;

import java.util.List;

/**
* @author Administrator
* @description 针对表【article】的数据库操作Service
* @createDate 2024-07-16 22:26:08
*/
public interface ArticleService extends IService<Article> {
    /**
     * 根据文章id获取文章
     * @param id
     * @return
     */
    ArticleDto getInfoById(int id);

    /**
     * 添加文章
     * @param articleVo
     * @return
     */
    boolean addArticle(ArticleVo articleVo);

    /**
     * 修改文章
     * @param id
     * @param articleVo
     * @return
     */
    boolean updateArticle(int id, ArticleVo articleVo);

    /**
     * 删除文章
     * @param id
     * @return
     */
    boolean deleteArticle(int id);

    /**
     * 根据id查询文章
     * @param uid 文章id
     * @param page 页码
     * @param size 页数据大小
     * @param sort 排序
     * @return
     */
    List<ArticleDto> queryArticleByUid(int uid, int page, int size, String sort);
}
