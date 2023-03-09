package com.korit.restaurant.repository;

import com.korit.restaurant.entity.admin.MenuImage;
import com.korit.restaurant.entity.admin.MenuMst;
import com.korit.restaurant.web.dto.MenuReqDto;
import com.korit.restaurant.web.dto.SearchNumberListReqDto;
import com.korit.restaurant.web.dto.SearchReqDto;
import org.apache.ibatis.annotations.Mapper;

import javax.validation.Valid;
import java.util.List;

@Mapper
public interface MenuRepository {
    public int getMenuTotalCount(SearchNumberListReqDto searchNumberListReqDto);
    public List<MenuMst> searchMenu(@Valid SearchReqDto searchSReqDto);

    public MenuMst findMenuByMenuCode(String menuCode);

    public int saveMenu(MenuReqDto menuReqDto);

    public int updateMenuByMenuCode(MenuReqDto menuReqDto);
    public int maintainUpdateMenuByMenuCode(MenuReqDto menuReqDto);

    public int deleteMenu(String menuCode);

    public int registerMenuImages(List<MenuImage> menuImages);

    public List<MenuImage> findMenuImageAll(String menuCode);
    public MenuImage findMenuImageByImageId(int imageId);
    public MenuImage findMenuImageByMenuCode(String menuCode);

    public int deleteMenuImage(int imageId);

}
