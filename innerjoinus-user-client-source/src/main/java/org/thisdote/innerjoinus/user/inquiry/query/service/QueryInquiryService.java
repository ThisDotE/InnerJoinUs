package org.thisdote.innerjoinus.user.inquiry.query.service;

import org.thisdote.innerjoinus.user.inquiry.dto.InquiryDTO;

import java.util.List;

public interface QueryInquiryService {
    List<InquiryDTO> selectAllInquiry();

    List<InquiryDTO> selectInquiriesByUser(Integer userCode);
}
