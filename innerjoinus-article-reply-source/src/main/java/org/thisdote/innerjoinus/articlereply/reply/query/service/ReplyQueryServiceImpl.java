package org.thisdote.innerjoinus.articlereply.reply.query.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thisdote.innerjoinus.articlereply.client.UserClient;
import org.thisdote.innerjoinus.articlereply.reply.dto.ReplyDTO;
import org.thisdote.innerjoinus.articlereply.reply.query.aggregate.ReplyQueryEntity;
import org.thisdote.innerjoinus.articlereply.reply.query.repository.ReplyMapper;
import org.thisdote.innerjoinus.articlereply.reply.query.repository.ReplyQueryRepository;
import org.thisdote.innerjoinus.articlereply.article.command.vo.ResponseUser;

import java.util.List;

@Service
public class ReplyQueryServiceImpl implements ReplyQueryService{

    private final SqlSessionTemplate sqlSession;
    private final ReplyQueryRepository replyQueryRepository;
    private final ModelMapper mapper;
    private final UserClient userClient;

    @Autowired
    public ReplyQueryServiceImpl(SqlSessionTemplate sqlSession, ReplyQueryRepository replyQueryRepository, ModelMapper mapper, UserClient userClient) {
        this.sqlSession = sqlSession;
        this.replyQueryRepository = replyQueryRepository;
        this.mapper = mapper;
        this.userClient = userClient;
    }

    public List<ReplyDTO> findAllReply() {
        return sqlSession.getMapper(ReplyMapper.class).selectAllReply();
    }

    public List<ReplyDTO> selectReplyByUser(int userCode) {
        return sqlSession.getMapper(ReplyMapper.class).selectReplyByUser(userCode);
    }

    @Override
    public ReplyDTO selectReplyByReplyIdFeignUser(int replyId) {
        ReplyQueryEntity replyQueryEntity = replyQueryRepository.findById(replyId).get();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ReplyDTO replyDTO = mapper.map(replyQueryEntity, ReplyDTO.class);

        ResponseUser responseUser = userClient.getAllUser(replyDTO.getUserCode());
        replyDTO.setResponseUser(responseUser);

        return replyDTO;
    }

    @Override
    public ReplyDTO selectReplyByReplyId(int replyId) {
        ReplyQueryEntity replyQueryEntity = replyQueryRepository.findById(replyId).get();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(replyQueryEntity, ReplyDTO.class);
    }
}
