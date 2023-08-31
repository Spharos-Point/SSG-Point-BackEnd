package com.spharos.pointapp.faq.application;


import com.spharos.pointapp.faq.domain.Faq;
import com.spharos.pointapp.faq.dto.FaqCreateDto;
import com.spharos.pointapp.faq.dto.FaqGetDto;
import com.spharos.pointapp.faq.infrastructure.FaqListRepository;
import com.spharos.pointapp.faq.infrastructure.FaqRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FaqServiceImple implements FaqService {
    private final FaqRepository faqRepository;
    private final FaqListRepository faqListRepository;

    // faq 생성
    @Override
    public void createFaq(FaqCreateDto faqCreateDto) {
        faqRepository.save(
                Faq.builder()
                        .title(faqCreateDto.getTitle())
                        .context(faqCreateDto.getContext())
                        .build());
    }

//    // faq 개별 조회
//    @Override
//    public FaqGetDto getFaq(Long faqId) {
//        Faq event = faqRepository.findById(faqId).get();
//        log.info("{}", faq);
//        ModelMapper mapper = new ModelMapper();
//        FaqGetDto faqGetDto = mapper.map(faq, FaqGetDto.class);
//        log.info("{}", faqGetDto);
//        return faqGetDto;
//    }

    //faq 전체 조회
    @Override
    public List<FaqGetDto> getFaqs() {
        List<Faq> faqList = faqRepository.findAll();
        log.info("{}", faqList);
        List<FaqGetDto> faqGetDtoList = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        faqList.forEach(
                faq -> faqGetDtoList.add(
                        mapper.map(faq, FaqGetDto.class)
                )
        );
        log.info("{}", faqGetDtoList);
        return faqGetDtoList;
    }
}

