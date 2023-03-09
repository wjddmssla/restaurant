package com.korit.restaurant.web.api.admin;

import com.korit.restaurant.aop.annotation.ParamsAspect;
import com.korit.restaurant.aop.annotation.ValidAspect;
import com.korit.restaurant.entity.admin.ReservationMst;
import com.korit.restaurant.service.ReservationService;
import com.korit.restaurant.web.dto.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Api(tags = {"관리자 예약관리 API"})
@RequestMapping("/api/admin")
@RestController
@CrossOrigin(origins = "http://localhost:5500")
public class ReservationApi {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservation/{reserveName}")
    public ResponseEntity<CMRespDto<Map<String, Object>>> getReservation(@PathVariable String reserveName) {

        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", reservationService.getReservation(reserveName)));
    }

    @ParamsAspect
    @ValidAspect
    @GetMapping("/reservations")
    public ResponseEntity<CMRespDto<List<ReservationMst>>> searchReservation(@Valid SearchReqDto searchReqDto, BindingResult bindingResult) {
        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", reservationService.searchReservation(searchReqDto)));
    }

    @GetMapping("/reservations/totalcount")
    public ResponseEntity<CMRespDto<?>> getReservationTotalCount(SearchNumberListReqDto searchNumberListReqDto) {
        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", reservationService.getReservationTotalCount(searchNumberListReqDto)));
    }

    @ParamsAspect
    @ValidAspect
    @PutMapping("/reservation/{reserveName}")
    public ResponseEntity<CMRespDto<?>> modifyReservation(@PathVariable String reserveName, @Valid @RequestBody ReservationReqDto reservationReqDto, BindingResult bindingResult) {
        reservationService.modifyReservation(reservationReqDto);
        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", true));
    }

    @ParamsAspect
    @ValidAspect
    @PatchMapping("/reservation/{reserveName}")
    public ResponseEntity<CMRespDto<?>> maintainModifyReservation(@PathVariable String bookCode, @Valid @RequestBody ReservationReqDto reservationReqDto, BindingResult bindingResult) {
        reservationService.maintainModifyReservation(reservationReqDto);

        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", true));
    }

}

