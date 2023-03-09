
package com.korit.restaurant.repository;

import com.korit.restaurant.entity.admin.SearchMenuMst;
import com.korit.restaurant.web.dto.SearchMenuReqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMenuRepository {
    public int getAdminSearchMenuTotalCount(SearchMenuReqDto searchMenuReqDto);
    public List<SearchMenuMst> adminSearchMenu(SearchMenuReqDto searchMenuReqDto);
}