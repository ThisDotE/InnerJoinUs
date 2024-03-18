package org.thisdote.innerjoinus.articlereply.reply.command.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thisdote.innerjoinus.articlereply.reply.command.service.ReplyCommandService;
import org.thisdote.innerjoinus.articlereply.reply.command.vo.*;
import org.thisdote.innerjoinus.articlereply.reply.dto.ReplyDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/reply")
    @Operation(summary = "댓글 등록", description = "댓글 등록 API")
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

    @PutMapping("/reply")
    @Operation(summary = "댓글 수정", description = "댓글 수정 API")
    public ResponseEntity<Map<String, Object>> modifyReply(@RequestBody RequestModifyReply modifyReply) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ReplyDTO replyDTO = modelMapper.map(modifyReply, ReplyDTO.class);

        ReplyDTO returnedReplyDTO = replyCommandService.modifyReply(replyDTO);
        System.out.println("returnedReplyDTO = " + returnedReplyDTO);
        Map<String, Object> modifyResultResponse = new HashMap<>();

        if (returnedReplyDTO == null) {
            modifyResultResponse.put("message", "댓글 수정 실패");
        } else {
            modifyResultResponse.put("resultCode", 200);
            modifyResultResponse.put("message", "댓글 수정 성공");
            modifyResultResponse.put("result", modelMapper.map(returnedReplyDTO, ResponseModifyReply.class));
        }

        return ResponseEntity.status(HttpStatus.OK).body(modifyResultResponse);
    }

    @DeleteMapping("/reply")
    @Operation(summary = "댓글 삭제", description = "댓글 삭제 API")
    public ResponseEntity<ResponseDeleteReply> deleteReply(@RequestBody RequestDeleteReply deleteReply) {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ReplyDTO replyDTO = modelMapper.map(deleteReply, ReplyDTO.class);
        ResponseDeleteReply responseDeleteReply = new ResponseDeleteReply();

        responseDeleteReply.setMessage(replyCommandService.deleteReply(replyDTO));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDeleteReply);
    }
}
