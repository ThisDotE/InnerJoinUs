package org.thisdote.innerjoinus.user.inquiry.query.vo;

import lombok.Data;

import java.util.Date;
@Data
public class ResponseQueryInquiry {
    private Integer inquiryId;
    private Integer inquiryCategory;
    private String inquiryTitle;
    private String inquiryContent;
    private Date inquiryCreateDate;
    private Date inquiryLastUpdateDate;
    private Integer inquiryStatus;
    private Integer userCode;
}
