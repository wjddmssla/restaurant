package com.korit.restaurant.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeleteMenusReqDto {
    private List<Integer> userIds;
}
