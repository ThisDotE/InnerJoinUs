package org.thisdote.innerjoinus.articlereply.article.query.controller;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thisdote.innerjoinus.articlereply.article.query.aggregate.SearchCriteriaEnum;
import org.thisdote.innerjoinus.articlereply.article.query.vo.*;
import org.thisdote.innerjoinus.articlereply.article.dto.ArticleDTO;
import org.thisdote.innerjoinus.articlereply.article.query.service.ArticleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/")
public class ArticleController {
    private ArticleService articleService;

    private final ModelMapper mapper;

    @Autowired
    public ArticleController(ArticleService articleService, ModelMapper mapper) {
        this.articleService = articleService;
        this.mapper = mapper;
    }

    @GetMapping("/select")
    public ResponseEntity<List<ResponseArticle>> selectAllArticle(){
        List<ArticleDTO> articleDTOList = articleService.selectAllArticle();
        List<ResponseArticle> returnValue = articleDTOToTesponseOrder(articleDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @GetMapping("/select/{articleId}")
    public ResponseEntity<ResponseArticle> selectArticleByArticleId(@PathVariable("articleId") int articleId) {
        ArticleDTO articleDTO = articleService.selectArticleByArticleId(articleId);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseArticle returnValue = mapper.map(articleDTO, ResponseArticle.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @GetMapping("/question")
    public ResponseEntity<List<ResponseQuestionArticle>> selectAllQuestionArticle(){
        List<ArticleDTO> articleDTOList = articleService.selectAllQuestionArticle();
        List<ResponseQuestionArticle> returnValue = new ArrayList<>();

        if (articleDTOList != null) {
            returnValue = articleDTOList.stream().map(ArticleDTO -> mapper.map(ArticleDTO, ResponseQuestionArticle.class)).toList();
        }

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @GetMapping("/select/article/{user_code}")
    public ResponseEntity<List<ResponseArticleByUser>> selectArticleByUser(@PathVariable("user_code") int user_code){
        List<ArticleDTO> articleDTOList = articleService.selectArticleByUser(user_code);
        List<ResponseArticleByUser> returnValue = new ArrayList<>();

        if (articleDTOList != null) {
            returnValue = articleDTOList.stream().map(ArticleDTO -> mapper.map(ArticleDTO, ResponseArticleByUser.class)).toList();
        }

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @GetMapping("/select/study/{articleId}/{studyCate}")
    public ResponseEntity<List<ResponseStudyArticleInfo>> selectStudyArticleInfo
            (@PathVariable("articleId") int article_id, @PathVariable("studyCate") int study_cate){
        List<ArticleDTO> articleDTOList = articleService.selectStudyArticleInfo(article_id, study_cate);
        List<ResponseStudyArticleInfo> returnValue = articleAndReplyDTOToTesponseOrder(articleDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @PostMapping("/select/criteria")
    public ResponseEntity<List<ResponseSelectEnum>> selectArticleByCriteria(@RequestBody RequestSelectEnum requestCriteria) {
        Map<String, Object> criteria = new HashMap<>();
        if(SearchCriteriaEnum.TITLE.toString().equals(requestCriteria.getCriteriaEnum())){
            criteria.put("TITLE", requestCriteria.getSelect());
        } else if (SearchCriteriaEnum.CATEGORY.toString().equals(requestCriteria.getCriteriaEnum())) {
            criteria.put(requestCriteria.getCriteriaEnum(), requestCriteria.getSelect());
        } else if (SearchCriteriaEnum.WRITER.toString().equals(requestCriteria.getCriteriaEnum())) {
            criteria.put(requestCriteria.getCriteriaEnum(), requestCriteria.getSelect());
        } else{
            log.info("검색할 수 없습니다.");
        }
        List<ArticleDTO> articleDTOList = articleService.selectArticleByCriteria(criteria);
        List<ResponseSelectEnum> returnValue = new ArrayList<>();

        if(articleDTOList != null){
            returnValue = articleDTOList.stream().map(ArticleDTO -> mapper.map(ArticleDTO, ResponseSelectEnum.class)).toList();
        }
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    private List<ResponseArticle> articleDTOToTesponseOrder(List<ArticleDTO> articleDTOList) {
        List<ResponseArticle> responseArticles = new ArrayList<>();
        for (ArticleDTO articleDTO : articleDTOList) {
            ResponseArticle responseArticle = new ResponseArticle();
            responseArticle.setArticleId(articleDTO.getArticleId());
            responseArticle.setArticleTitle(articleDTO.getArticleTitle());
            responseArticle.setArticleContent(articleDTO.getArticleContent());
            responseArticle.setArticleCategory(articleDTO.getArticleCategory());
            responseArticle.setArticleCreateDate(articleDTO.getArticleCreateDate());
            responseArticle.setArticleLastUpdateDate(articleDTO.getArticleLastUpdateDate());
            responseArticle.setArticleViewCount(articleDTO.getArticleViewCount());
            responseArticle.setArticleLikeCount(articleDTO.getArticleLikeCount());
            responseArticle.setArticleReplyCount(articleDTO.getArticleReplyCount());
            responseArticle.setArticleReportStatus(articleDTO.getArticleReportStatus());
            responseArticle.setStudygroupMemberMaxCount(articleDTO.getStudygroupMemberMaxCount());
            responseArticle.setStudygroupRecruitmentDeadline(articleDTO.getStudygroupRecruitmentDeadline());
            responseArticle.setArticleQuestionCategory(articleDTO.getArticleQuestionCategory());
            responseArticle.setUserCode(articleDTO.getUserCode());
            responseArticle.setStudygroupId(articleDTO.getStudygroupId());
            responseArticle.setStudygroupCurrentMemberCount(articleDTO.getStudygroupCurrentMemberCount());
            responseArticle.setStudygroupPendingMemberCount(articleDTO.getStudygroupPendingMemberCount());
            responseArticle.setArticleDeleteStatus(articleDTO.getArticleDeleteStatus());

            responseArticles.add(responseArticle);
        }

        return responseArticles;
    }

    private List<ResponseStudyArticleInfo> articleAndReplyDTOToTesponseOrder(List<ArticleDTO> articleDTOList) {
        List<ResponseStudyArticleInfo> responseArticles = new ArrayList<>();
        for (ArticleDTO articleDTO : articleDTOList) {
            ResponseStudyArticleInfo responseArticle = new ResponseStudyArticleInfo();

            responseArticle.setArticleId(articleDTO.getArticleId());
            responseArticle.setArticleTitle(articleDTO.getArticleTitle());
            responseArticle.setArticleContent(articleDTO.getArticleContent());
            responseArticle.setArticleCategory(articleDTO.getArticleCategory());
            responseArticle.setArticleLastUpdateDate(articleDTO.getArticleLastUpdateDate());
            responseArticle.setArticleViewCount(articleDTO.getArticleViewCount());
            responseArticle.setArticleLikeCount(articleDTO.getArticleLikeCount());
            responseArticle.setArticleReplyCount(articleDTO.getArticleReplyCount());
            responseArticle.setStudygroupMemberMaxCount(articleDTO.getStudygroupMemberMaxCount());
            responseArticle.setStudygroupRecruitmentDeadline(articleDTO.getStudygroupRecruitmentDeadline());
            responseArticle.setStudygroupCurrentMemberCount(articleDTO.getStudygroupCurrentMemberCount());
            responseArticle.setStudygroupPendingMemberCount(articleDTO.getStudygroupPendingMemberCount());
            responseArticle.setReplyDTOList(articleDTO.getReplyDTOList());

            responseArticles.add(responseArticle);
        }
        return responseArticles;
    }

}
