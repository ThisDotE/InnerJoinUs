package org.thisdote.innerjoinus.articlereply.article.command.controller;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thisdote.innerjoinus.articlereply.article.command.service.CommandArticleService;
import org.thisdote.innerjoinus.articlereply.article.command.vo.*;
import org.thisdote.innerjoinus.articlereply.article.dto.ArticleDTO;

@Slf4j
@RestController
@RequestMapping("/")
public class CommandArticleController {
    private final ModelMapper modelMapper;
    private final CommandArticleService commandArticleService;

    public CommandArticleController(ModelMapper modelMapper, CommandArticleService commandArticleService) {
        this.modelMapper = modelMapper;
        this.commandArticleService = commandArticleService;
    }


    @PostMapping("/article")
    public ResponseEntity<ResponseRegistArticle> registArticle(@RequestBody RequestRegistArticle registArticle){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ArticleDTO articleDTO = modelMapper.map(registArticle, ArticleDTO.class);
        ArticleDTO article = commandArticleService.registArticle(articleDTO);

        ResponseRegistArticle responseRegistArticle = new ResponseRegistArticle();
        responseRegistArticle.setMessage(article.toString());
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseRegistArticle);
    }

    @DeleteMapping("/article")
    public ResponseEntity<ResponseDeleteArticle> deleteArticle(@RequestBody RequestDeleteArticle deleteArticle){
        ArticleDTO articleDTO = modelMapper.map(deleteArticle, ArticleDTO.class);
        ArticleDTO article = commandArticleService.deleteArticle(articleDTO);
        ResponseDeleteArticle returnValue = modelMapper.map(article, ResponseDeleteArticle.class);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
    }

    @PutMapping("/article")
    public ResponseEntity<ResponseModifyArticle> modifyArticle(@RequestBody RequestModifyArticle modifyArticle){
        ArticleDTO articleDTO = modelMapper.map(modifyArticle, ArticleDTO.class);
        ArticleDTO article = commandArticleService.modifyArticle(articleDTO);

        ResponseModifyArticle responseModifyArticle = modelMapper.map(article, ResponseModifyArticle.class);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseModifyArticle);
    }

    @GetMapping("/article/{articleId}")
    public ResponseEntity<ResponseArticleUser> selectArticleUser(@PathVariable("articleId") int articleId){
        ArticleDTO articleDTO = commandArticleService.selectArticleUser(articleId);

        ResponseArticleUser returnValue = modelMapper.map(articleDTO, ResponseArticleUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @PutMapping("/article/{articleId}")
    public ResponseEntity<ResponseArticleUser> increaseViewCount(@PathVariable("articleId") int articleId){
        ArticleDTO articleDTO = commandArticleService.increaseViewCount(articleId);

        ResponseArticleUser returnValue = modelMapper.map(articleDTO, ResponseArticleUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
