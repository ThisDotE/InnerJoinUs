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


import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public Map<Integer, List<ArticleDTO>> popularArticle() {
        List<ArticleDTO> articleDTOList = sqlSession.getMapper(ArticleMapper.class).currentDaySelect();
        Map<Integer, List<ArticleDTO>> articleMap = new HashMap<>();

        // 각 카테고리별로 게시글을 조회수 높은 순으로 정렬하여 리스트로 만듦
        for (int category = 1; category <= 3; category++) {
            int finalCategory = category;
            List<ArticleDTO> sortedArticles = articleDTOList.stream()
                    .filter(articleDTO -> articleDTO.getArticleCategory() == finalCategory)
                    .sorted(Comparator.comparingInt(ArticleDTO::getArticleViewCount).reversed()) // 조회수 높은 순으로 정렬
                    .collect(Collectors.toList());
            sortedArticles.forEach(System.out::println);
            // 결과가 있으면 맵에 추가
            if (!sortedArticles.isEmpty()) {
                articleMap.put(finalCategory, sortedArticles);
            }
        }

        return articleMap;
    }
//    @Override
//    public Map<Integer, ArticleDTO> popularArticle() {
//        List<ArticleDTO> articleDTOList = sqlSession.getMapper(ArticleMapper.class).currentDaySelect();
//        Map<Integer, ArticleDTO> articleMap = new HashMap<>();
////        articleDTOList.stream().sorted(Comparator.comparing(ArticleDTO::getArticleCategory)).toList();
//        for (ArticleDTO articleDTO : articleDTOList) {
//            if(articleDTO.getArticleCategory() == 1){
//                articleMap.put(1, articleDTO);
//            } else if(articleDTO.getArticleCategory() == 2){
//                articleMap.put(2, articleDTO);
//            } else {
//                articleMap.put(3, articleDTO);
//            }
//        }
//        List<Map.Entry<Integer, ArticleDTO>> list = new ArrayList<>(articleMap.entrySet());
//        list.sort(Map.Entry.comparingByValue(Comparator.comparing(ArticleDTO::getArticleViewCount)));
//
//        return null;
//    }
}

