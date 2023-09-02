package com.spharos.pointapp.faq.application;

import com.spharos.pointapp.faq.dto.FaqCreateDto;
import com.spharos.pointapp.faq.dto.FaqGetDto;

import java.util.List;
// 1. faq 생성
// 2. faq  조회 **//

public interface FaqService {
    // faq 생성
    void createFaq(FaqCreateDto faqCreateDto);

    // faq 전체 조회
    List<FaqGetDto> getFaqs();
}
