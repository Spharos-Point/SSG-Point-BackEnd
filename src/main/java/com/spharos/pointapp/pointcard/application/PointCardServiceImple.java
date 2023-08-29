//package com.spharos.pointapp.pointcard.application;
//
//import com.spharos.pointapp.pointcard.dto.PointCardCreateDto;
//import com.spharos.pointapp.pointcard.dto.PointCardOutDto;
//import com.spharos.pointapp.pointcard.infrastructure.PointCardRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//import javax.swing.plaf.OptionPaneUI;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class PointCardServiceImple implements PointCardService {
//
//    private final PointCardRepository pointCardRepository;
//    private final ModelMapper modelMapper;
//
//
//    /**
//     *
//     * 1. 포인트 카드 생성
//     *
//     */
//
//    @Override
//    public void createPointCard(PointCardCreateDto pointCardCreateDto, String uuid) {
//        String uuidString = uuid.toString();
//
////        User user = User.builder()
////                .loginId(userSignUpDto.getLoginId())
////                .uuid(uuidString)
////                .name(userSignUpDto.getName())
////                .password(userSignUpDto.getPassword())
////                .email(userSignUpDto.getEmail())
////                .phone(userSignUpDto.getPhone())
////                .address(userSignUpDto.getAddress())
////                .status(1)
////                .build();
////        userRepository.save(user);
//    }
//
//    @Override
//    @Transactional(readOnly = True)
//    public PointCardOutDto getPointcardByUser() {
//        ModelMapper modelMapper = new ModelMapper();
//        List<PointCardOutDto> pointCars = PointCardRepository.findbyUuid(uuid);
//        List<UserGetDto> userGetDtos = users.stream().map(
//                user -> modelMapper.map(user, UserGetDto.class)
//        ).toList();
//        return userGetDtos;
//
//
//    }
//
//}