package org.thisdote.innerjoinus.articlereply.reply.query.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thisdote.innerjoinus.articlereply.reply.dto.ReplyDTO;
import org.thisdote.innerjoinus.articlereply.reply.query.repository.ReplyMapper;

import java.util.List;

@Service
public class ReplyQueryServiceImpl implements ReplyQueryService{

    private final SqlSessionTemplate sqlSession;

    @Autowired
    public ReplyQueryServiceImpl(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<ReplyDTO> findAllReply() {
        return sqlSession.getMapper(ReplyMapper.class).selectAllReply();
    }

    public List<ReplyDTO> selectReplyByUser(int userCode) {
        return sqlSession.getMapper(ReplyMapper.class).selectReplyByUser(userCode);
    }
}
