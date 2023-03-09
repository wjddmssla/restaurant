package com.korit.restaurant.service;

import com.korit.restaurant.entity.admin.SearchReservationMst;
import com.korit.restaurant.repository.SearchReservationRepository;
import com.korit.restaurant.web.dto.SearchReservationReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchReservationService {

    private final SearchReservationRepository searchReservationRepository;

    public int getSearchTotalCount(SearchReservationReqDto searchReservationReqDto) {
        return searchReservationRepository.getAdminSearchReservationTotalCount(searchReservationReqDto);
    }

    public List<SearchReservationMst> getSearchReservations(SearchReservationReqDto searchReservationReqDto) {
        searchReservationReqDto.setIndex();
        return searchReservationRepository.adminSearchReservation(searchReservationReqDto);
    }


}