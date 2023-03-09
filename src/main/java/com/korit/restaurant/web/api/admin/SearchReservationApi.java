package com.korit.restaurant.web.api.admin;

import com.korit.restaurant.aop.annotation.ParamsAspect;
import com.korit.restaurant.service.SearchReservationService;
import com.korit.restaurant.web.dto.CMRespDto;
import com.korit.restaurant.web.dto.SearchReservationReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SearchReservationApi {

    private final SearchReservationService searchReservationService;

    @GetMapping("/search/reservation")
    public ResponseEntity<CMRespDto<?>> search(SearchReservationReqDto searchReservationReqDto) {

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(),
                        "Successfully",
                        searchReservationService.getSearchReservations(searchReservationReqDto)));

    }



    @ParamsAspect
    @GetMapping("/search/reservation/totalcount")
    public ResponseEntity<CMRespDto<Integer>> getSearchReservationTotalCount(SearchReservationReqDto searchReservationReqDto) {
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(),
                        "Successfully",
                        searchReservationService.getSearchTotalCount(searchReservationReqDto)));
    }

}
