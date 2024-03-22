package org.thisdote.innerjoinus.user.inquiry.query.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thisdote.innerjoinus.user.inquiry.dto.InquiryDTO;
import org.thisdote.innerjoinus.user.inquiry.query.aggregate.QueryInquiryEntity;
import org.thisdote.innerjoinus.user.inquiry.query.repository.QueryInquiryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryInquiryServiceImpl implements QueryInquiryService{

    private final QueryInquiryRepository queryInquiryRepository;
    private final ModelMapper mapper;

    @Autowired
    public QueryInquiryServiceImpl(QueryInquiryRepository queryInquiryRepository,
                                   ModelMapper mapper) {
        this.queryInquiryRepository = queryInquiryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<InquiryDTO> selectAllInquiry() {
        List<QueryInquiryEntity> inquiryEntityList = queryInquiryRepository.findAll();

        List<InquiryDTO> inquiryDTOList;
        inquiryDTOList = inquiryEntityList
                .stream()
                .map(QueryInquiryEntity ->
                        mapper.map(QueryInquiryEntity, InquiryDTO.class))
                .toList();
        return inquiryDTOList;
    }

    @Override
    public List<InquiryDTO> selectInquiriesByUser(Integer userCode) {
        List<QueryInquiryEntity> inquiryEntityList = queryInquiryRepository.findByUserCode(userCode);
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<InquiryDTO> inquiryDTOList = inquiryEntityList
                .stream()
                .map(QueryInquiryEntity ->
                        mapper.map(QueryInquiryEntity, InquiryDTO.class))
                .toList();

        return inquiryDTOList;
    }
}
