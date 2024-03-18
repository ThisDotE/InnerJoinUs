package org.thisdote.innerjoinus.studygroup.command.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="studygroup")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudyGroupEntity {

    @Id
    @Column(name="studygroup_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer studygroupId;

    @Column(name="studygroup_type")
    private Integer studygroupType;

    @Column(name="studygroup_create_date")
    private java.util.Date studygroupCreateDate;

    @Column(name="studygroup_member_count")
    private Integer studygroupMemberCount;

    @Column(name="studygroup_activation_status")
    private Integer studygroupActivationStatus;

    @Column(name="studygroup_study_time")
    private java.util.Date studygroupStudyTime;

    @Column(name="studygroup_content")
    private String studygroupContent;

    @Column(name="studygroup_delete_status")
    private Integer studygroupDeleteStatus;

    public void insertStudyGroup(
            int studygroupType,
            Date studygroupCreateDate,
            int studygroupMemberCount,
            int studygroupActivationStatus,
            Date studygroupStudyTime,
            String studygroupContent,
            int studygroupDeleteStatus) {
        this.studygroupType = studygroupType;
        this.studygroupCreateDate = studygroupCreateDate;
        this.studygroupMemberCount = studygroupMemberCount;
        this.studygroupActivationStatus = studygroupActivationStatus;
        this.studygroupStudyTime = studygroupStudyTime;
        this.studygroupContent = studygroupContent;
        this.studygroupDeleteStatus = studygroupDeleteStatus;
    }

    public void updateStudyGroup(int studygroupId, int studygroupType
            , Date studygroupCreateDate, int studygroupMemberCount
            , int studygroupActivationStatus, Date studygroupStudyTime
            , String studygroupContent, int studygroupDeleteStatus) {
        this.studygroupId = studygroupId;
        this.studygroupType = studygroupType;
        this.studygroupCreateDate = studygroupCreateDate;
        this.studygroupMemberCount = studygroupMemberCount;
        this.studygroupActivationStatus = studygroupActivationStatus;
        this.studygroupStudyTime = studygroupStudyTime;
        this.studygroupContent = studygroupContent;
        this.studygroupDeleteStatus = studygroupDeleteStatus;
    }

    public void removeStudyGroup(int studygroupId, int studygroupDeleteStatus) {
        this.studygroupId = studygroupId;
        this.studygroupDeleteStatus = studygroupDeleteStatus;
    }
}
