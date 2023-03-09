package com.korit.restaurant.service;

import com.korit.restaurant.entity.admin.ReservationMst;
import com.korit.restaurant.repository.ReservationRepository;
import com.korit.restaurant.web.dto.ReservationReqDto;
import com.korit.restaurant.web.dto.SearchNumberListReqDto;
import com.korit.restaurant.web.dto.SearchReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Map<String, Object> getReservation(String reserveName) {
        Map<String, Object> result = new HashMap<>();
        result.put("reservationMst", reservationRepository.findReserveByReserveName(reserveName));

        return result;
    }

    public int getReservationTotalCount(SearchNumberListReqDto searchNumberListReqDto) {
        return reservationRepository.getReservationTotalCount(searchNumberListReqDto);
    }

    public List<ReservationMst> searchReservation(SearchReqDto searchReqDto) {
        searchReqDto.setIndex();
        return reservationRepository.searchReservation(searchReqDto);
    }

    public void modifyReservation(ReservationReqDto reservationReqDto) {
        reservationRepository.updateReserveByReserveName(reservationReqDto);
    }

    public void maintainModifyReservation(ReservationReqDto reservationReqDto) {
        reservationRepository.maintainUpdateReserveByReserveName(reservationReqDto);
    }


}
