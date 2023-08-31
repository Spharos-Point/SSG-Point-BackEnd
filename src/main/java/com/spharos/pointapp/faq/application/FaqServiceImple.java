package com.spharos.pointapp.faq.application;


import com.spharos.pointapp.faq.domain.Faq;
import com.spharos.pointapp.faq.dto.FaqCreateDto;
import com.spharos.pointapp.faq.infrastructure.FaqRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FaqServiceImple implements FaqService {
    private final FaqRepository faqRepository;

    @Override
    public void createFaq(FaqCreateDto faqCreateDto) {
        faqRepository.save(
                Faq.builder()
                        .title(faqCreateDto.getTitle())
                        .context(faqCreateDto.getContext())
                        .build());
    }

}

