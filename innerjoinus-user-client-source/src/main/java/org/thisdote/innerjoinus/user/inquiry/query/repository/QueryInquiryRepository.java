package org.thisdote.innerjoinus.user.inquiry.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thisdote.innerjoinus.user.inquiry.query.aggregate.QueryInquiryEntity;

import java.util.List;

public interface QueryInquiryRepository extends JpaRepository<QueryInquiryEntity, Integer> {
    List<QueryInquiryEntity> findByUserCode(Integer userCode);
}