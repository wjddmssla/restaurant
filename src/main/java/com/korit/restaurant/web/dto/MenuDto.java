package com.korit.restaurant.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuDto {

    private int menuCode;
    private String menuName;
    private String day;
    private String meals;
    private int menuAge1;
    private int menuAge2;
    private String salesPride;


}
