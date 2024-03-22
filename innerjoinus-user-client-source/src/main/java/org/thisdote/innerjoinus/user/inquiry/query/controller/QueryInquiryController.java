package org.thisdote.innerjoinus.user.inquiry.query.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thisdote.innerjoinus.user.inquiry.dto.InquiryDTO;
import org.thisdote.innerjoinus.user.inquiry.query.service.QueryInquiryService;
import org.thisdote.innerjoinus.user.inquiry.query.vo.ResponseQueryInquiry;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class QueryInquiryController {
    private final QueryInquiryService queryInquiryService;
    private final ModelMapper mapper;

    @Autowired
    public QueryInquiryController(QueryInquiryService queryInquiryService,
                                  ModelMapper mapper) {
        this.queryInquiryService = queryInquiryService;
        this.mapper = mapper;
    }

    @GetMapping("/inquiry")
    public ResponseEntity<List<ResponseQueryInquiry>> selectAllInquiry() {
        List<InquiryDTO> inquiryDTOList = queryInquiryService.selectAllInquiry();
        List<ResponseQueryInquiry> responseInquiryList = new ArrayList<>();

        if (inquiryDTOList != null) {
            responseInquiryList = inquiryDTOList
                    .stream()
                    .map(InquiryDTO ->
                            mapper.map(InquiryDTO, ResponseQueryInquiry.class))
                    .toList();
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseInquiryList);
    }

    @GetMapping("/inquiry/{inquiryId}")
    public ResponseEntity<ResponseQueryInquiry> selectInquiryById(@PathVariable("inquiryId") Integer inquiryId) {

        return null;
    }

    @GetMapping("/inquiry/user/{userCode}")
    public ResponseEntity<List<ResponseQueryInquiry>> selectInquiriesByUser(@PathVariable("userCode") Integer userCode) {
        List<InquiryDTO> inquiryDTOList = queryInquiryService.selectInquiriesByUser(userCode);
        List<ResponseQueryInquiry> responseInquiryList = new ArrayList<>();

        if(inquiryDTOList != null) {
            responseInquiryList = inquiryDTOList
                    .stream()
                    .map(InquiryDTO ->
                            mapper.map(InquiryDTO, ResponseQueryInquiry.class))
                    .toList();
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseInquiryList);
    }
}
