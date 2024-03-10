package org.thisdote.innerjoinus.articlereply.reply.query.service;

import org.thisdote.innerjoinus.articlereply.reply.dto.ReplyDTO;

import java.util.List;

public interface ReplyQueryService {

    public List<ReplyDTO> findAllReply();

    public List<ReplyDTO> selectReplyByUser(int userCode);

    ReplyDTO selectReplyByReplyIdFeignUser(int replyId);

    ReplyDTO selectReplyByReplyId(int replyId);
}
