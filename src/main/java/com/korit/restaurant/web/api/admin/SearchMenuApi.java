package com.korit.restaurant.web.api.admin;

import com.korit.restaurant.aop.annotation.ParamsAspect;
import com.korit.restaurant.service.SearchMenuService;
import com.korit.restaurant.web.dto.CMRespDto;
import com.korit.restaurant.web.dto.SearchMenuReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class SearchMenuApi {

    private final SearchMenuService searchMenuService;

    @GetMapping("/search/menu")
    public ResponseEntity<CMRespDto<?>> search(SearchMenuReqDto searchMenuReqDto) {

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(),
                        "Successfully",
                        searchMenuService.getSearchMenus(searchMenuReqDto)));

    }



    @ParamsAspect
    @GetMapping("/search/menu/totalcount")
    public ResponseEntity<CMRespDto<Integer>> getSearchMenuTotalCount(SearchMenuReqDto searchMenuReqDto) {
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(),
                        "Successfully",
                        searchMenuService.getSearchMenuTotalCount(searchMenuReqDto)));
    }

}
