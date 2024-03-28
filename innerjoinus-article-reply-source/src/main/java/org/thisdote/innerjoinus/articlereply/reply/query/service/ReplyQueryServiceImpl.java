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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public List<ReplyDTO> selectRepliesByArticleId(int articleId) {
        List<ReplyQueryEntity> replyList = replyQueryRepository.findByArticleId(articleId);
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<ReplyDTO> replyDTOList = new ArrayList<>();
        for (ReplyQueryEntity reply: replyList) {
            ReplyDTO replyDTO = mapper.map(reply, ReplyDTO.class);
            ResponseUser responseUser = userClient.getAllUser(replyDTO.getUserCode());
            replyDTO.setResponseUser(responseUser);
            replyDTOList.add(replyDTO);
        }

        Collections.sort(replyDTOList, new Comparator<ReplyDTO>() {
            @Override
            public int compare(ReplyDTO o1, ReplyDTO o2) {
                // 내림차순 정렬을 위해 o2와 o1의 순서를 바꿈
                return o2.getReplyCreatedDate().compareTo(o1.getReplyCreatedDate());
            }
        });

        return replyDTOList;
    }

    @Override
    public ReplyDTO selectReplyByReplyId(int replyId) {
        ReplyQueryEntity replyQueryEntity = replyQueryRepository.findById(replyId).get();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(replyQueryEntity, ReplyDTO.class);
    }
}
