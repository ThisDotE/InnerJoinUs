package org.thisdote.innerjoinus.articlereply.reply.query.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thisdote.innerjoinus.articlereply.reply.dto.ReplyDTO;
import org.thisdote.innerjoinus.articlereply.reply.query.service.ReplyQueryService;
import org.thisdote.innerjoinus.articlereply.reply.query.vo.ResponseSelectAllReply;
import org.thisdote.innerjoinus.articlereply.reply.query.vo.ResponseSelectReplyByReplyId;
import org.thisdote.innerjoinus.articlereply.reply.query.vo.ResponseSelectReplyByReplyIdFeignUser;
import org.thisdote.innerjoinus.articlereply.reply.query.vo.ResponseSelectReplyByUser;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reply")
public class ReplyQueryController {

    private final ReplyQueryService replyQueryService;
    private final ModelMapper mapper;

    @Autowired
    public ReplyQueryController(ReplyQueryService replyQueryService, ModelMapper mapper) {
        this.replyQueryService = replyQueryService;
        this.mapper = mapper;
    }

    @GetMapping("/select_all")
    public ResponseEntity<List<ResponseSelectAllReply>> selectAllReply() {
        List<ReplyDTO> replyDTOList = replyQueryService.findAllReply();
        List<ResponseSelectAllReply> responseList = replyDTOToResponseSelectAllReply(replyDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping("/select_by_user/{userCode}")
    public ResponseEntity<List<ResponseSelectReplyByUser>> selectReplyByUser(@PathVariable("userCode") int user_code) {
        List<ReplyDTO> replyDTOList = replyQueryService.selectReplyByUser(user_code);
        List<ResponseSelectReplyByUser> responseList = new ArrayList<>();

        if (replyDTOList != null) {
            responseList = replyDTOList.stream().map(ReplyDTO -> mapper.map(ReplyDTO, ResponseSelectReplyByUser.class)).toList();
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping("/select/with_user_info/{replyId}")
    public ResponseEntity<ResponseSelectReplyByReplyIdFeignUser> selectReplyByReplyIdFeignUser(@PathVariable("replyId") int replyId) {
        ReplyDTO replyDTO = replyQueryService.selectReplyByReplyIdFeignUser(replyId);

        ResponseSelectReplyByReplyIdFeignUser returnValue = mapper.map(replyDTO, ResponseSelectReplyByReplyIdFeignUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @GetMapping("/select/{replyId}")
    public ResponseEntity<ResponseSelectReplyByReplyId> selectReplyByReplyId(@PathVariable("replyId") int replyId) {
        ReplyDTO replyDTO = replyQueryService.selectReplyByReplyId(replyId);

        ResponseSelectReplyByReplyId returnValue = mapper.map(replyDTO, ResponseSelectReplyByReplyId.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    private List<ResponseSelectAllReply> replyDTOToResponseSelectAllReply(List<ReplyDTO> replyDTOList) {
        List<ResponseSelectAllReply> responseList = new ArrayList<>();
        for (ReplyDTO replyDTO : replyDTOList) {
            ResponseSelectAllReply responseSelectAllReply = new ResponseSelectAllReply();
            responseSelectAllReply.setReplyId(replyDTO.getReplyId());
            responseSelectAllReply.setReplyReportStatus(replyDTO.getReplyReportStatus());
            responseSelectAllReply.setReplyContent(replyDTO.getReplyContent());
            responseSelectAllReply.setReplyCreatedDate(replyDTO.getReplyCreatedDate());
            responseSelectAllReply.setReplyLastUpdateDate(replyDTO.getReplyLastUpdateDate());
            responseSelectAllReply.setReplyLikeCount(replyDTO.getReplyLikeCount());
            responseSelectAllReply.setUserCode(replyDTO.getUserCode());
            responseSelectAllReply.setArticleId(replyDTO.getArticleId());
            responseSelectAllReply.setReplyDeleteStatus(replyDTO.getReplyDeleteStatus());

            responseList.add(responseSelectAllReply);
        }

        return responseList;
    }
}
