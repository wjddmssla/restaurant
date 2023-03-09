package com.korit.restaurant.service;

import com.korit.restaurant.entity.admin.SearchMenuMst;
import com.korit.restaurant.repository.SearchMenuRepository;
import com.korit.restaurant.web.dto.SearchMenuReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchMenuService {

    private final SearchMenuRepository searchMenuRepository;

    public int getSearchMenuTotalCount(SearchMenuReqDto searchMenuReqDto) {
        return searchMenuRepository.getAdminSearchMenuTotalCount(searchMenuReqDto);
    }

    public List<SearchMenuMst> getSearchMenus(SearchMenuReqDto searchMenuReqDto) {
        searchMenuReqDto.setIndex();
        return searchMenuRepository.adminSearchMenu(searchMenuReqDto);
    }


}