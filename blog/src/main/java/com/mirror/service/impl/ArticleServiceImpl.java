package com.mirror.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mirror.dto.ArticleDto;
import com.mirror.mapper.UserMapper;
import com.mirror.pojo.Article;
import com.mirror.service.ArticleService;
import com.mirror.mapper.ArticleMapper;
import com.mirror.utils.AuthenticationUtil;
import com.mirror.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @description 针对表【article】的数据库操作Service实现
 * @createDate 2024-07-16 22:26:08
 */
@Service

public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ArticleDto getInfoById(int id) {
        Article article = articleMapper.getInfoById(id);
        if (article == null) return null;
        String name = userMapper.getNameById(article.getUserId());
        return ArticleDto.builder().username(name).content(article.getContent())
                .created(article.getCreated()).lastModified(article.getLastModified())
                .title(article.getTitle()).build();
    }

    @Override
    public boolean addArticle(ArticleVo articleVo) {
        String userName = AuthenticationUtil.getUser();
        int userId = userMapper.queryUidByName(userName);
        Article article = Article.builder().title(articleVo.getTitle()).content(articleVo.getContent())
                .userId(userId).build();
        int res = articleMapper.addArticle(article);
        return res > 0;
    }

    @Override
    public boolean updateArticle(int postId, ArticleVo articleVo) {
        String userName = AuthenticationUtil.getUser();
        int userId = userMapper.queryUidByName(userName);
        if (articleMapper.checkArticle(postId, userId) == null) {
            throw new RuntimeException("文章并不属于此人或文章不存在");
        }
        Article article = Article.builder().postId(postId).title(articleVo.getTitle()).content(articleVo.getContent()).build();
        int res = articleMapper.updateArticle(article);
        return res > 0;
    }

    @Override
    public boolean deleteArticle(int postId) {
        String userName = AuthenticationUtil.getUser();
        int userId = userMapper.queryUidByName(userName);
        if (articleMapper.checkArticle(postId, userId) == null) {
            throw new RuntimeException("文章并不属于此人或文章不存在");
        }
        int res = articleMapper.deleteArticle(postId);
        return res > 0;
    }

    @Override
    public List<ArticleDto> queryArticleByUid(int uid, int page, int size, String sort) {
        //1.设置分页参数
        Page<Article> objectPage = new Page<>();
        objectPage.setCurrent(page+1);
        objectPage.setSize(size);
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        //2.根据条件查询
        if (sort.equals("desc")) {
            wrapper.eq("user_id", uid).orderByDesc("created");
        } else {
            wrapper.eq("user_id", uid).orderByAsc("created");
        }
        IPage<Article> p = articleMapper.selectPage(objectPage, wrapper);
        if (p == null) return null;
        //3.封装结果
        ArrayList<ArticleDto> articleDtos = new ArrayList<>();
        for (Article record : p.getRecords()) {
            String nameById = userMapper.getNameById(record.getUserId());
            articleDtos.add(ArticleDto.builder().title(record.getTitle()).content(record.getContent())
                    .lastModified(record.getLastModified()).created(record.getCreated())
                    .username(nameById).build());

        }
        return articleDtos;
    }

}




