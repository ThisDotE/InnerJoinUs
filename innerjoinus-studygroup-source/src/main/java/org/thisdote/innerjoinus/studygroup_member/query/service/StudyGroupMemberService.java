package org.thisdote.innerjoinus.studygroup_member.query.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thisdote.innerjoinus.studygroup_member.dto.StudyGroupMemberDTO;
import org.thisdote.innerjoinus.studygroup_member.query.repository.StudyGroupMemberMapper;

import java.util.List;

@Service
public class StudyGroupMemberService {

    private final SqlSessionTemplate sqlSession;
    @Autowired
    public StudyGroupMemberService(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

//    public List<StudyGroupMemberDTO> selectAllStudyGroupMember() {
//        return sqlSession.getMapper(StudyGroupMemberMapper.class).selectAllStudyGroupMember();
//    }

    public List<StudyGroupMemberDTO> selectAllStudyGroupLeader() {
        return sqlSession.getMapper(StudyGroupMemberMapper.class).selectAllStudyGroupLeader();
    }

    public List<StudyGroupMemberDTO> selectAllStudyGroupFollower() {
        return sqlSession.getMapper(StudyGroupMemberMapper.class).selectAllStudyGroupFollower();
    }

    public List<StudyGroupMemberDTO> viewAllStudyGroupMember() {
        return sqlSession.getMapper(StudyGroupMemberMapper.class).selectAllStudyGroupMember();
    }
}
