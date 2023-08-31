package com.spharos.pointapp.faq.presentation;

import com.spharos.pointapp.faq.application.FaqService;
import com.spharos.pointapp.faq.dto.FaqCreateDto;
import com.spharos.pointapp.faq.dto.FaqGetDto;
import com.spharos.pointapp.faq.vo.FaqCreate;
import com.spharos.pointapp.faq.vo.FaqGetOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class FaqController {

    private final FaqService faqService;

//    faq 생성
    @PostMapping("/faq")
    public void createFaq(@RequestBody FaqCreate faqCreate) {
        FaqCreateDto faqCreateDto = FaqCreateDto.builder()
                .title(faqCreate.getTitle())
                .context(faqCreate.getContext())
                .build();
        faqService.createFaq(faqCreateDto);
    }

//    faq 조회
    @GetMapping("/fqg")
    public List<FaqGetOut> getAllFaqs() {
        ModelMapper mapper = new ModelMapper();
        List<FaqGetDto> faqGetDtoList = faqService.getFaqs();
        log.info("{}", faqGetDtoList);
        List<FaqGetOut> faqGetOutList = new ArrayList<>();
        faqGetDtoList.forEach(
                faqGetDto -> faqGetOutList.add(
                        mapper.map(faqGetDto, FaqGetOut.class)
                )
        );
        return faqGetOutList;
    }



}
