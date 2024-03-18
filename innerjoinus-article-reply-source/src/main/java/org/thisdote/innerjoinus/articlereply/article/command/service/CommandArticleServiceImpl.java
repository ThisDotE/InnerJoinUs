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
import org.thisdote.innerjoinus.articlereply.reply.dto.ReplyDTO;
import org.thisdote.innerjoinus.articlereply.reply.query.service.ReplyQueryService;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommandArticleServiceImpl implements CommandArticleService {
    private final ModelMapper modelMapper;
    private final CommandArticleRepository commandArticleRepository;
    private final UserClient userClient;
    private final ReplyQueryService replyQueryService;

    @Autowired
    public CommandArticleServiceImpl(
            ModelMapper modelMapper,
            CommandArticleRepository commandArticleRepository,
            UserClient userClient,
            ReplyQueryService replyQueryService
    ) {
        this.modelMapper = modelMapper;
        this.commandArticleRepository = commandArticleRepository;
        this.userClient = userClient;
        this.replyQueryService = replyQueryService;
    }

    @Transactional
    public ArticleDTO registArticle(ArticleDTO newArticle){
        newArticle.setArticleCreateDate(new Date());
        newArticle.setArticleLastUpdateDate(new Date());
        ArticleEntity article = new ArticleEntity();
        article.registArticle(newArticle.getArticleTitle(),
                newArticle.getArticleContent(),
                newArticle.getArticleCategory(),
                newArticle.getArticleCreateDate(),
                newArticle.getArticleLastUpdateDate(),
                newArticle.getArticleViewCount(),
                newArticle.getArticleLikeCount(),
                newArticle.getArticleReplyCount(),
                newArticle.getArticleReportStatus(),
                newArticle.getStudygroupMemberMaxCount(),
                newArticle.getStudygroupRecruitmentDeadline(),
                newArticle.getArticleQuestionCategory(),
                newArticle.getUserCode(),
                newArticle.getStudygroupId(),
                newArticle.getStudygroupCurrentMemberCount(),
                newArticle.getStudygroupPendingMemberCount(),
                newArticle.getArticleDeleteStatus());
        commandArticleRepository.save(article);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(article, ArticleDTO.class);
    }

    @Transactional
    @Override
    public ArticleDTO deleteArticle(ArticleDTO articleDTO) {
        ArticleEntity article = commandArticleRepository.findById(articleDTO.getArticleId()).get();
        System.out.println("article = " + article);
        article.deleteArticle(articleDTO.getArticleDeleteStatus());

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(article, ArticleDTO.class);
    }

    @Transactional
    @Override
    public ArticleDTO modifyArticle(ArticleDTO articleDTO) {
        ArticleEntity article = commandArticleRepository.findById(articleDTO.getArticleId()).get();
//        article.setArticleTitle(articleDTO.getArticleTitle());
//        article.setArticleContent(articleDTO.getArticleContent());
//        article.setArticleLastUpdateDate(new Date());
        article.modifyArticle(articleDTO.getArticleTitle(), articleDTO.getArticleContent(), new Date());

        return modelMapper.map(article, ArticleDTO.class);
    }

    @Override
    public ArticleDTO selectArticleUser(int articleId) {
        ArticleEntity article = commandArticleRepository.findById(articleId).get();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);
        ResponseUser userList = userClient.getAllUser(articleDTO.getUserCode());
        articleDTO.setUserList(userList);
        List<ReplyDTO> replyDTOList = replyQueryService.selectRepliesByArticleId(articleId);
        articleDTO.setReplyDTOList(replyDTOList);
        return articleDTO;
    }
}


