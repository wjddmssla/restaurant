package com.korit.restaurant.entity.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchReservationMst {
    private int reserveId;
    private String reserveName;
    private Date reserveDate;
    private Time reserveTime;
    private String number;
    private String email;
    private int adult;
    private int child;
    private int guest;
    private String request;
}