package com.korit.restaurant.web.api.admin;

import com.korit.restaurant.aop.annotation.ParamsAspect;
import com.korit.restaurant.aop.annotation.ValidAspect;
import com.korit.restaurant.entity.admin.MenuImage;
import com.korit.restaurant.entity.admin.MenuMst;
import com.korit.restaurant.service.MenuService;
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

@Api(tags = {"관리자 메뉴관리 API"})
@RequestMapping("/api/admin")
@RestController
@CrossOrigin(origins = "http://localhost:5500")
public class MenuApi {

    @Autowired
    private MenuService menuService;

    @GetMapping("/menu/{menuCode}")
    public ResponseEntity<CMRespDto<Map<String, Object>>> getMenu(@PathVariable String menuCode) {

        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", menuService.getMenuAndImage(menuCode)));
    }

    @ParamsAspect
    @GetMapping("/menus")
    public ResponseEntity<CMRespDto<List<MenuMst>>> searchMenu(SearchReqDto searchReqDto) {
        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", menuService.searchMenu(searchReqDto)));
    }

    @PostMapping("/menus/totalcount")
    public ResponseEntity<CMRespDto<?>> getMenuTotalCount(SearchNumberListReqDto searchNumberListReqDto) {
        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", menuService.getMenuTotalCount(searchNumberListReqDto)));
    }

    @ParamsAspect
    @ValidAspect
    @PostMapping("/menu")
    public ResponseEntity<CMRespDto<?>> registerMenu(@Valid @RequestBody MenuReqDto menuReqDto, BindingResult bindingResult) {
        menuService.registerMenu(menuReqDto);
        return ResponseEntity
                .created(null)
                .body(new CMRespDto<>(HttpStatus.CREATED.value(), "Successfully", true));
    }

    @ParamsAspect
    @ValidAspect
    @PutMapping("/menu/{menuCode}")
    public ResponseEntity<CMRespDto<?>> modifyMenu(@PathVariable String menuCode, @Valid @RequestBody MenuReqDto menuReqDto, BindingResult bindingResult) {
        menuService.modifyMenu(menuReqDto);
        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", true));
    }

    @ParamsAspect
    @ValidAspect
    @PatchMapping("/menu/{menuCode}")
    public ResponseEntity<CMRespDto<?>> maintainModifyBook(@PathVariable String bookCode, @Valid @RequestBody MenuReqDto menuReqDto, BindingResult bindingResult) {
        menuService.maintainModifyMenu(menuReqDto);

        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", true));
    }

    @ParamsAspect
    @DeleteMapping("/menu/{menuCode}")
    public ResponseEntity<CMRespDto<?>> removeMenu(@PathVariable String menuCode) {
        menuService.removeMenu(menuCode);
        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", true));
    }

    @ParamsAspect
    @PostMapping("/menu/{menuCode}/images")
    public ResponseEntity<CMRespDto<?>> registerMenuImg(@PathVariable String menuCode, @RequestPart List<MultipartFile> files) {
        menuService.registerMenuImages(Integer.parseInt(menuCode), files);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", true));
    }

    @ParamsAspect
    @PostMapping("/menu/{menuCode}/images/modification")
    public ResponseEntity<CMRespDto<?>> modifyMenuImg(@PathVariable String menuCode, @RequestPart List<MultipartFile> files) {
        menuService.registerMenuImages(Integer.parseInt(menuCode), files);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", true));
    }

    @ParamsAspect
    @GetMapping("/menu/{menuCode}/images")
    public ResponseEntity<CMRespDto<List<MenuImage>>> getImages(@PathVariable String menuCode) {
        List<MenuImage> menuImages = menuService.getMenus(menuCode);
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully", menuImages));
    }

    @DeleteMapping("/menu/{menuCode}/image/{imageId}")
    public ResponseEntity<CMRespDto<?>> removeMenuImg(
            @PathVariable String menuCode,
            @PathVariable int imageId) {

        menuService.removeMenuImage(imageId);

        return ResponseEntity
                .ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully" ,null));
    }


}
