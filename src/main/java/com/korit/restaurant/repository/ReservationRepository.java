package com.korit.restaurant.repository;

import com.korit.restaurant.entity.admin.ReservationMst;
import com.korit.restaurant.web.dto.ReservationReqDto;
import com.korit.restaurant.web.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationRepository {
    public int getReservationTotalCount(SearchNumberListReqDto searchNumberListReqDto);
    public List<ReservationMst> searchReservation(SearchReqDto searchReqDto);

    public ReservationMst findReserveByReserveName(String reserveName);

    public int updateReserveByReserveName(ReservationReqDto reservationReqDto);
    public int maintainUpdateReserveByReserveName(ReservationReqDto reservationReqDto);

}
