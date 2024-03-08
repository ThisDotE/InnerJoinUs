package org.thisdote.innerjoinus.articlereply.reply.command.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thisdote.innerjoinus.articlereply.reply.command.service.ReplyCommandService;
import org.thisdote.innerjoinus.articlereply.reply.command.vo.RequestRegistReply;
import org.thisdote.innerjoinus.articlereply.reply.command.vo.ResponseRegistReply;
import org.thisdote.innerjoinus.articlereply.reply.dto.ReplyDTO;

import java.util.Date;

@RestController
@RequestMapping("/")
public class ReplyCommandController {

    private final Environment env;
    private final ModelMapper modelMapper;
    private final ReplyCommandService replyCommandService;

    @Autowired
    public ReplyCommandController(Environment env,
                                  ModelMapper modelMapper,
                                  ReplyCommandService replyCommandService) {
        this.env = env;
        this.modelMapper = modelMapper;
        this.replyCommandService = replyCommandService;
    }

    /* 설명. health_check */
    @GetMapping("/health_check")
    public String status() {
        return "Server at " + env.getProperty("local.server.port")
                + ", swcamp.message: " + env.getProperty("swcamp.message");
    }

    @GetMapping("/regist-reply")
    public void registReply() {
    }

    @PostMapping("/regist-reply")
    public ResponseEntity<ResponseRegistReply> registReply(@RequestBody RequestRegistReply inputReply) {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ReplyDTO inputReplyDTO = modelMapper.map(inputReply, ReplyDTO.class);

        ReplyDTO responseReplyDTO = replyCommandService.registReply(inputReplyDTO);

        ResponseRegistReply responseRegistReply = new ResponseRegistReply();

        responseRegistReply.setReplyContent(inputReply.getReplyContent());
        responseRegistReply.setUserCode(inputReply.getUserCode());
        responseRegistReply.setArticleId(inputReply.getArticleId());

        responseRegistReply.setReplyReportStatus(responseReplyDTO.getReplyReportStatus());
        responseRegistReply.setReplyCreatedDate(responseReplyDTO.getReplyCreatedDate());
        responseRegistReply.setReplyLastUpdateDate(responseReplyDTO.getReplyLastUpdateDate());
        responseRegistReply.setReplyLikeCount(responseReplyDTO.getReplyLikeCount());
        responseRegistReply.setReplyDeleteStatus(responseReplyDTO.getReplyDeleteStatus());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseRegistReply);
    }
}
