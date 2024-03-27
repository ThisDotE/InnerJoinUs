package org.thisdote.innerjoinus.user.inquiry.query.aggregate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "inquiry")
public class QueryInquiryEntity {
    @Id
    @Column(name = "inquiry_id")
    private Integer inquiryId;
    @Column
    private Integer inquiryCategory;
    @Column
    private String inquiryTitle;
    @Column
    private String inquiryContent;
    @Column
    private Date inquiryCreateDate;
    @Column
    private Date inquiryLastUpdateDate;
    @Column
    private Integer inquiryStatus;
    @Column
    private Integer userCode;
}
