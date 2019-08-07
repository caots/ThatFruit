package com.bksoftwarevn.controller.admin.category;

import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.category.BigCategory;
import com.bksoftwarevn.entities.category.Menu;
import com.bksoftwarevn.entities.category.SmallCategory;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.category.BigCategoryService;
import com.bksoftwarevn.service.category.MenuService;
import com.bksoftwarevn.service.category.SmallCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/v1/admin/category")
@RolesAllowed("ROLE_ADMIN")
public class AdminCategoryController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private BigCategoryService bigCategoryService;

    @Autowired
    private SmallCategoryService smallCategoryService;

    @Autowired
    private RecordService recordService;

    //=========================== Menu ==========================

    @PostMapping(value = "/menu")
    public ResponseEntity<Object> createMenu(@RequestBody Menu menu) {
        Record record = recordService.findByName("menu");
        menu.setStatus(true);
        if (menuService.saveBMenu(menu)) {
            record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else
            return new ResponseEntity<>("create menu fail !", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/menu")
    public ResponseEntity<Object> updateMenu(@RequestBody Menu menu) {
        if (menuService.saveBMenu(menu))
            return new ResponseEntity<>(menu, HttpStatus.OK);
        else
            return new ResponseEntity<>("update menu fail!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/menu/delete")
    public ResponseEntity<String> deleteMenu(@RequestParam("id") int menuId) {
        Record record = recordService.findByName("menu");

        Menu menu = menuService.findMenuById(menuId);
        if (menuService.deleteBMenu(menu)) {
            record.setNumber(record.getNumber() - 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete menu success", HttpStatus.OK);
        } else
            return new ResponseEntity<>("delete menu fails", HttpStatus.BAD_REQUEST);
    }


    //=========================== Big Category ===========================

    @PostMapping(value = "/big")
    public ResponseEntity<Object> createBigCategory(@RequestBody BigCategory bigCategory,
                                                    @RequestParam(value = "menu-id") int menuId) {
        Record record = recordService.findByName("big-category");

        Menu menu = menuService.findMenuById(menuId);
        bigCategory.setMenu(menu);
        bigCategory.setStatus(true);
        if (bigCategoryService.saveBigCategory(bigCategory)) {
            record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(bigCategory, HttpStatus.OK);
        } else
            return new ResponseEntity<>("create big category fail!", HttpStatus.BAD_REQUEST);

    }

    @PutMapping(value = "/big")
    public ResponseEntity<Object> updateBigCategory(@RequestBody BigCategory bigCategory) {
        if (bigCategoryService.saveBigCategory(bigCategory))
            return new ResponseEntity<>(bigCategory, HttpStatus.OK);
        else
            return new ResponseEntity<>("update big category fail!", HttpStatus.BAD_REQUEST);
    }


    @PutMapping(value = "big/delete")
    public ResponseEntity<String> deleteMediumCategory(@RequestParam("id") int idBigCategory) {
        Record record = recordService.findByName("big-category");

        BigCategory bigCategory = bigCategoryService.findBigCategoryById(idBigCategory);
        if (bigCategoryService.deleteBigCategory(bigCategory)) {
            record.setNumber(record.getNumber() - 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete big category success", HttpStatus.OK);
        } else
            return new ResponseEntity<>("delete big category fails", HttpStatus.BAD_REQUEST);
    }

    //=========================== Small Category ==========================

    @PostMapping(value = "/small")
    public ResponseEntity<Object> createSmallCategory(@RequestBody SmallCategory smallCategory,
                                                      @RequestParam(value = "big-id") int bigId) {
        Record record = recordService.findByName("small-category");
        BigCategory bigCategory = bigCategoryService.findBigCategoryById(bigId);
        smallCategory.setBigCategory(bigCategory);
        smallCategory.setStatus(true);
        if (smallCategoryService.saveSmallCategory(smallCategory)) {
            record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(smallCategory, HttpStatus.OK);
        } else
            return new ResponseEntity<>("create small category fail!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/small")
    public ResponseEntity<Object> updateSmallCategory(@RequestBody SmallCategory smallCategory) {
        if (smallCategoryService.saveSmallCategory(smallCategory))
            return new ResponseEntity<>(smallCategory, HttpStatus.OK);
        else
            return new ResponseEntity<>("update small category fail!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/delete/small")
    public ResponseEntity<String> deleteSmallCategory(@RequestParam("id") int idSmallCategory) {
        Record record = recordService.findByName("small-category");

        SmallCategory smallCategory = smallCategoryService.findSmallCategoryById(idSmallCategory);
        if (smallCategoryService.deleteSmallCategory(smallCategory)){
            record.setNumber(record.getNumber()-1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete small category success", HttpStatus.OK);}
        else
            return new ResponseEntity<>("delete small category fail!", HttpStatus.BAD_REQUEST);
    }

}
