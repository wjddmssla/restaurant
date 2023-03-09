package com.korit.restaurant.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class ReservationReqDto {

    @NotBlank
    public int reserveId;

    @ApiModelProperty(value = "예약자", example = "김민지", required = true)
    @NotBlank
    public String reserveName;

    @ApiModelProperty(value = "예약일", example = "2023-01-01")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    public LocalDate reserveDate;

    @ApiModelProperty(value = "예약시간", example = "17시", required = true)
    @NotBlank
    public Time reserveTime;

    @ApiModelProperty(value = "이메일", example = "aaa@gmail.com", required = true)
    @NotBlank
    public String number;

    @ApiModelProperty(value = "이메일", example = "aaa@gmail.com", required = true)
    @NotBlank
    public String email;

    @ApiModelProperty(value = "대인 수", example = "3", required = true)
    @NotBlank
    public int adult;

    @ApiModelProperty(value = "소인 수", example = "2", required = true)
    @NotBlank
    public int child;

    @ApiModelProperty(value = "총 인원수", example = "5", required = true)
    public int guest;

    @ApiModelProperty(value = "요청사항", example = "null", required = true)
    public String request;
}
