package org.thisdote.innerjoinus.articlereply.article.query.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thisdote.innerjoinus.articlereply.article.dto.ArticleDTO;
import org.thisdote.innerjoinus.articlereply.article.query.aggregate.ArticleQueryEntity;
import org.thisdote.innerjoinus.articlereply.article.query.repository.ArticleMapper;
import org.thisdote.innerjoinus.articlereply.article.query.repository.ArticleQueryRepository;


import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final SqlSessionTemplate sqlSession;
    private final ArticleQueryRepository articleQueryRepository;
    private final ModelMapper mapper;

    @Autowired
    public ArticleServiceImpl(SqlSessionTemplate sqlSession, ArticleQueryRepository articleQueryRepository, ModelMapper mapper) {
        this.sqlSession = sqlSession;
        this.articleQueryRepository = articleQueryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ArticleDTO> selectAllArticle() {
        return sqlSession.getMapper(ArticleMapper.class).selectAllArticle();
    }

    @Override
    public List<ArticleDTO> selectAllQuestionArticle() {
        return sqlSession.getMapper(ArticleMapper.class).selectAllQuestionArticle();
    }

    @Override
    public List<ArticleDTO> selectArticleByUser(int userCode) {
        return sqlSession.getMapper(ArticleMapper.class).selectArticleByUser(userCode);
    }

    @Override
    public List<ArticleDTO> selectStudyArticleInfo(int articleId, int studyCate) {return sqlSession.getMapper(ArticleMapper.class).selectStudyArticleInfo(articleId, studyCate);}

    @Override
    public List<ArticleDTO> selectArticleByCriteria(Map<String, Object> criteria) {
        return sqlSession.getMapper(ArticleMapper.class).selectArticleByCriteria(criteria);
    }

    @Override
    public ArticleDTO selectArticleByArticleId(int articleId) {
        ArticleQueryEntity articleQueryEntity = articleQueryRepository.findById(articleId).get();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(articleQueryEntity, ArticleDTO.class);
    }
}

