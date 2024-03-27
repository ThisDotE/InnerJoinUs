package org.thisdote.innerjoinus.studygroup.command.service;

import org.thisdote.innerjoinus.studygroup.dto.StudyGroupCommandDTO;

public interface StudyGroupCommandService {


    StudyGroupCommandDTO updateStudyGroup(StudyGroupCommandDTO studyGroupCommandDTO);

    StudyGroupCommandDTO removeStudyGroup(StudyGroupCommandDTO studyGroupCommandDTO);

    StudyGroupCommandDTO insertStudygroup(StudyGroupCommandDTO studyGroupCommandDTO);
}
