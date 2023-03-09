package com.korit.restaurant.web.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminDto {
    private String adminId;
    private String adminPw;
}
