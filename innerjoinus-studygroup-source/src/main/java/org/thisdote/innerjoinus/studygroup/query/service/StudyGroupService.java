package org.thisdote.innerjoinus.studygroup.query.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thisdote.innerjoinus.studygroup.dto.StudyGroupDTO;
import org.thisdote.innerjoinus.studygroup.query.repository.StudyGroupMapper;

import java.util.List;

@Service
public class StudyGroupService {

    private final SqlSessionTemplate sqlSession;

    @Autowired
    public StudyGroupService(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    List<StudyGroupDTO> findAllStudyGroup() {
        return sqlSession.getMapper(StudyGroupMapper.class).selectAllStudyGroup();
    }

    List<StudyGroupDTO> findAllEnglishStudyGroup() {
        return sqlSession.getMapper(StudyGroupMapper.class).selectAllEnglishStudyGroup();
    }

    List<StudyGroupDTO> findAllLicenseStudyGroup() {
        return sqlSession.getMapper(StudyGroupMapper.class).selectAllLicenseStudyGroup();
    }

    List<StudyGroupDTO> findStudyGroupByStatus(int activationStatus) {
        return sqlSession.getMapper(StudyGroupMapper.class).selectStudyGroupByStatus(activationStatus);
    }

    public List<StudyGroupDTO> selectStudyGroupsByType(int studyGroupType) {
        return sqlSession.getMapper(StudyGroupMapper.class).selectStudyGroupsByType(studyGroupType);
    }

    public List<StudyGroupDTO> viewAllStudyGroup() {
        return sqlSession.getMapper(StudyGroupMapper.class).selectAllStudyGroup();
    }

    public List<StudyGroupDTO> selectAllStudyGroupByUser(Integer userCode) {
        return sqlSession.getMapper(StudyGroupMapper.class).selectAllStudyGroupByUser(userCode);
    }
}
