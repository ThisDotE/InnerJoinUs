package org.thisdote.innerjoinus.articlereply.article.command.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
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
    @Operation(summary = "게시글 등록", description = "게시글 등록 API")
    public ResponseEntity<ResponseRegistArticle> registArticle(@RequestBody RequestRegistArticle registArticle){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ArticleDTO articleDTO = modelMapper.map(registArticle, ArticleDTO.class);
        ArticleDTO article = commandArticleService.registArticle(articleDTO);

        ResponseRegistArticle responseRegistArticle = new ResponseRegistArticle();
        responseRegistArticle.setMessage(article.toString());
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseRegistArticle);
    }

    @DeleteMapping("/article")
    @Operation(summary = "게시글 삭제", description = "게시글 삭제 API")
    public ResponseEntity<ResponseDeleteArticle> deleteArticle(@RequestBody RequestDeleteArticle deleteArticle){
        ArticleDTO articleDTO = modelMapper.map(deleteArticle, ArticleDTO.class);
        ArticleDTO article = commandArticleService.deleteArticle(articleDTO);
        ResponseDeleteArticle returnValue = modelMapper.map(article, ResponseDeleteArticle.class);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
    }

    @PutMapping("/article")
    @Operation(summary = "게시글 수정", description = "게시글 수정 API")
    public ResponseEntity<ResponseModifyArticle> modifyArticle(@RequestBody RequestModifyArticle modifyArticle){
        ArticleDTO articleDTO = modelMapper.map(modifyArticle, ArticleDTO.class);
        ArticleDTO article = commandArticleService.modifyArticle(articleDTO);

        ResponseModifyArticle responseModifyArticle = modelMapper.map(article, ResponseModifyArticle.class);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseModifyArticle);
    }

    @GetMapping("/article/{articleId}")
    @Operation(summary = "회원 정보를 포함한 게시글 조회", description = "회원 정보를 포함한 게시글 조회 API")
    public ResponseEntity<ResponseArticleUser> selectArticleUser(@PathVariable("articleId") int articleId) {
        ArticleDTO articleDTO = commandArticleService.selectArticleUser(articleId);

        ResponseArticleUser returnValue = modelMapper.map(articleDTO, ResponseArticleUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
