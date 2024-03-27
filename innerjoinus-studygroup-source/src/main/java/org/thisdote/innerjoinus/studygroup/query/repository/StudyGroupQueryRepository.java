package org.thisdote.innerjoinus.studygroup.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thisdote.innerjoinus.studygroup.command.entity.StudyGroupEntity;

public interface StudyGroupQueryRepository extends JpaRepository<StudyGroupEntity, Integer> {
}
