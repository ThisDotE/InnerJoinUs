package org.thisdote.innerjoinus.user.inquiry.dto;

import lombok.Data;

import java.util.Date;

@Data
public class InquiryDTO {
    private Integer inquiryId;
    private Integer inquiryCategory;
    private String inquiryTitle;
    private String inquiryContent;
    private Date inquiryCreateDate;
    private Date inquiryLastUpdateDate;
    private Integer inquiryStatus;
    private Integer userCode;
}
