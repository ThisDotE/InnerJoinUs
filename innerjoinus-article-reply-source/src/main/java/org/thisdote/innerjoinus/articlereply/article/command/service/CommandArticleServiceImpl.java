package org.thisdote.innerjoinus.articlereply.article.command.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thisdote.innerjoinus.articlereply.article.command.aggregate.ArticleEntity;
import org.thisdote.innerjoinus.articlereply.article.command.repository.CommandArticleRepository;
import org.thisdote.innerjoinus.articlereply.article.command.vo.ResponseUser;
import org.thisdote.innerjoinus.articlereply.article.dto.ArticleDTO;
import org.thisdote.innerjoinus.articlereply.client.UserClient;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class CommandArticleServiceImpl implements CommandArticleService {
    private final ModelMapper modelMapper;
    private final CommandArticleRepository commandArticleRepository;
    private final UserClient userClient;

    @Autowired
    public CommandArticleServiceImpl(ModelMapper modelMapper
            , CommandArticleRepository commandArticleRepository
            , UserClient userClient) {
        this.modelMapper = modelMapper;
        this.commandArticleRepository = commandArticleRepository;
        this.userClient = userClient;
    }

    @Transactional
    public ArticleDTO registArticle(ArticleDTO newArticle){
        newArticle.setArticleCreateDate(new Date());
        newArticle.setArticleLastUpdateDate(new Date());
        ArticleEntity article = commandArticleRepository.save(ArticleEntity.builder()
                .articleId(newArticle.getArticleId())
                .articleTitle(newArticle.getArticleTitle())
                .articleContent(newArticle.getArticleContent())
                .articleCategory(newArticle.getArticleCategory())
                .articleCreateDate(newArticle.getArticleCreateDate())
                .articleLastUpdateDate(newArticle.getArticleLastUpdateDate())
                .articleViewCount(newArticle.getArticleViewCount())
                .articleLikeCount(newArticle.getArticleLikeCount())
                .articleReplyCount(newArticle.getArticleReplyCount())
                .articleReportStatus(newArticle.getArticleReportStatus())
                .studygroupMemberMaxCount(newArticle.getStudygroupMemberMaxCount())
                .studygroupRecruitmentDeadline(newArticle.getStudygroupRecruitmentDeadline())
                .articleQuestionCategory(newArticle.getArticleQuestionCategory())
                .userCode(newArticle.getUserCode())
                .studygroupId(newArticle.getStudygroupId())
                .studygroupCurrentMemberCount(newArticle.getStudygroupCurrentMemberCount())
                .studygroupPendingMemberCount(newArticle.getStudygroupPendingMemberCount())
                .articleDeleteStatus(newArticle.getArticleDeleteStatus())
                .build());

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(article, ArticleDTO.class);
    }

    @Transactional
    @Override
    public ArticleDTO deleteArticle(ArticleDTO articleDTO) {
        ArticleEntity article = commandArticleRepository.findById(articleDTO.getArticleId()).get();
        article.deleteArticle(articleDTO.getArticleDeleteStatus());

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(article, ArticleDTO.class);
    }

    @Transactional
    @Override
    public ArticleDTO modifyArticle(ArticleDTO articleDTO) {
        ArticleEntity article = commandArticleRepository.findById(articleDTO.getArticleId()).get();
        article.setArticleTitle(articleDTO.getArticleTitle());
        article.setArticleContent(articleDTO.getArticleContent());
        article.setArticleLastUpdateDate(new Date());

        return modelMapper.map(article, ArticleDTO.class);
    }

    @Override
    public ArticleDTO selectArticleUser(int articleId) {
        ArticleEntity article = commandArticleRepository.findById(articleId).get();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);

//        List<ResponseUser> userList = userClient.getAllUser(articleDTO.getUserCode());
        ResponseUser userList = userClient.getAllUser(articleDTO.getUserCode());
        articleDTO.setUserList(userList);
        return articleDTO;
    }
}
