package com.korit.restaurant.entity.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuMst {

    @ApiModelProperty(hidden = true)
    private int menuId;

    @ApiModelProperty(value = "메뉴코드", example = "메뉴-001")
    private String menuCode;

    @ApiModelProperty(value = "메뉴이름", example = "고기님")
    private String menuName;

    @ApiModelProperty(value = "요일", example = "평일")
    private String day;

    @ApiModelProperty(value = "식사시간", example = "석식")
    private String meals;

    @ApiModelProperty(value = "대인 가격", example = "100000")
    private int menuAge1;

    @ApiModelProperty(value = "소인 가격", example = "60000")
    private int menuAge2;

    @ApiModelProperty(value = "판매기간", example = "상시")
    private String salesPride;

    @ApiModelProperty(value = "설명")
    private String explanation;
}
