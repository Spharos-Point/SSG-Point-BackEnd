package com.spharos.pointapp.user.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    private String address;
    private String email;

}
