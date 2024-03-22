package org.thisdote.innerjoinus.user.inquiry.command.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "inquiry")
@Data
public class CommandInquiryEntity {
    @Id
    @Column(name = "inquiry_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inquiryId;

    @Column
    private int inquiryCategory;

    @Column
    private String  inquiryTitle;

    @Column
    private String inquiryContent;

    @Column
    private Date inquiryCreateDate;

    @Column
    private Date inquiryLastUpdateDate;

    @Column
    private int inquiryStatus;

    @Column
    private int userCode;
}
