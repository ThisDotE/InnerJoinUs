package org.thisdote.innerjoinus.studygroup.command.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thisdote.innerjoinus.studygroup.command.entity.StudyGroupEntity;
import org.thisdote.innerjoinus.studygroup.command.repository.StudyGroupRepository;
import org.thisdote.innerjoinus.studygroup.dto.StudyGroupCommandDTO;

import java.util.Date;

@Service
public class StudyGroupCommandServiceImpl implements StudyGroupCommandService{
    private ModelMapper mapper;
    private StudyGroupRepository studygroupRepository;

    public StudyGroupCommandServiceImpl(ModelMapper mapper, StudyGroupRepository studygroupRepository) {
        this.mapper = mapper;
        this.studygroupRepository = studygroupRepository;
    }

    @Transactional
    @Override
    public StudyGroupCommandDTO insertStudygroup(StudyGroupCommandDTO studyGroupCommandDTO) {
        Date createdDate = new Date();
        studyGroupCommandDTO.setStudygroupCreateDate(createdDate);
        studyGroupCommandDTO.setStudygroupActivationStatus(1);
        studyGroupCommandDTO.setStudygroupDeleteStatus(0);

        StudyGroupEntity studyGroupEntity = new StudyGroupEntity();
        studyGroupEntity.insertStudyGroup(studyGroupCommandDTO.getStudygroupCreateDate()
                        , studyGroupCommandDTO.getStudygroupActivationStatus()
                        , studyGroupCommandDTO.getStudygroupDeleteStatus());

        studygroupRepository.save(studyGroupEntity);
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(studyGroupEntity, StudyGroupCommandDTO.class);
    }

    @Transactional
    @Override
    public StudyGroupCommandDTO updateStudyGroup(StudyGroupCommandDTO studyGroupCommandDTO) {
        StudyGroupEntity studyGroup = studygroupRepository
                .findById(studyGroupCommandDTO.getStudygroupId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        studyGroup.setStudygroupId(studyGroupCommandDTO.getStudygroupId());
//        studyGroup.setStudygroupType(studyGroupCommandDTO.getStudygroupType());
//        studyGroup.setStudygroupCreateDate(studyGroupCommandDTO.getStudygroupCreateDate());
//        studyGroup.setStudygroupMemberCount(studyGroupCommandDTO.getStudygroupMemberCount());
//        studyGroup.setStudygroupActivationStatus(studyGroupCommandDTO.getStudygroupActivationStatus());
//        studyGroup.setStudygroupStudyTime(studyGroupCommandDTO.getStudygroupStudyTime());
//        studyGroup.setStudygroupContent(studyGroupCommandDTO.getStudygroupContent());
//        studyGroup.setStudygroupDeleteStatus(studyGroupCommandDTO.getStudygroupDeleteStatus());
        studyGroup.updateStudyGroup(studyGroupCommandDTO.getStudygroupId()
                , studyGroupCommandDTO.getStudygroupType()
                , studyGroupCommandDTO.getStudygroupCreateDate()
                , studyGroupCommandDTO.getStudygroupMemberCount()
                , studyGroupCommandDTO.getStudygroupActivationStatus()
                , studyGroupCommandDTO.getStudygroupStudyTime()
                , studyGroupCommandDTO.getStudygroupContent()
                , studyGroupCommandDTO.getStudygroupDeleteStatus());

        return mapper.map(studyGroup, StudyGroupCommandDTO.class);
    }

    @Transactional
    @Override
    public StudyGroupCommandDTO removeStudyGroup(StudyGroupCommandDTO studyGroupCommandDTO) {
        StudyGroupEntity studyGroup = studygroupRepository
                .findById(studyGroupCommandDTO.getStudygroupId()).orElseThrow(
                        () -> new UsernameNotFoundException("User not found")
                );
//        studyGroup.setStudygroupId(studyGroupCommandDTO.getStudygroupId());
//        studyGroup.setStudygroupDeleteStatus(studyGroupCommandDTO.getStudygroupDeleteStatus());

        studyGroup.removeStudyGroup(studyGroupCommandDTO.getStudygroupId()
                , studyGroupCommandDTO.getStudygroupDeleteStatus());
        return mapper.map(studyGroup, StudyGroupCommandDTO.class);
    }
}
