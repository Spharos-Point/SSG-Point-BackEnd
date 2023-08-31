package com.spharos.pointapp.faq.presentation;

import com.spharos.pointapp.faq.application.FaqService;
import com.spharos.pointapp.faq.dto.FaqCreateDto;
import com.spharos.pointapp.faq.vo.FaqCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class FaqController {

    private final FaqService faqService;

    @PostMapping("/faq")
    public void createFaq(@RequestBody FaqCreate faqCreate) {
        FaqCreateDto faqCreateDto = FaqCreateDto.builder()
                .title(faqCreate.getTitle())
                .context(faqCreate.getContext())
                .build();
        faqService.createFaq(faqCreateDto);
    }



}
