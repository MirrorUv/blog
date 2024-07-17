package com.mirror.mapper;

import com.mirror.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mirror.vo.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【article】的数据库操作Mapper
* @createDate 2024-07-16 22:26:08
* @Entity com.mirror.pojo.Article
*/
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    Article getInfoById(int id);

    int addArticle(Article article);

    Article checkArticle(@Param("postId") int postId, @Param("userId") int userId);

    int updateArticle(Article article);

    int deleteArticle(int postId);
}




