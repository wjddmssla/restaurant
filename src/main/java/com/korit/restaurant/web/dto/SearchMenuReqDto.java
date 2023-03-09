package com.korit.restaurant.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchMenuReqDto {
    private int page;
    private String searchValue;
    private int count;

    @ApiModelProperty(hidden = true)
    private int index;

    public void setIndex() {
        index = (page - 1) * count;
    }

}
