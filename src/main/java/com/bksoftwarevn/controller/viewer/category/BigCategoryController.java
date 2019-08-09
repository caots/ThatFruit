package com.bksoftwarevn.controller.viewer.category;

import com.bksoftwarevn.commom.Token;
import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.category.BigCategory;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.category.BigCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/big-category")
public class BigCategoryController {

    @Autowired
    private BigCategoryService bigCategoryService;

    @Autowired
    private RecordService recordService;

    @GetMapping(value = "/page")
    public ResponseEntity<List<BigCategory>> findAllBigCategoryPage(
            HttpServletResponse response,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            if (page < 1) page = 1;
            if (size < 0) size = 0;
            Pageable pageable = PageRequest.of(page - 1, size);
            List<BigCategory> bigCategories = bigCategoryService.findAllBigCategoryPage(pageable);
            return new ResponseEntity<>(bigCategories, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<BigCategory>> findAllBigCategory(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            List<BigCategory> bigCategories = bigCategoryService.findAllBigCategory();
            return new ResponseEntity<>(bigCategories, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/size")
    public ResponseEntity<Double> pageNumberMediumCategory(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        Record record = recordService.findByName("big-category");
        if (header.equals(Token.tokenHeader)) {

            double result = Math.ceil((double) record.getNumber() / 10);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find-by-menu")
    public ResponseEntity<List<BigCategory>> findAllBigCategoryByMenu(
            @RequestParam("menu-id") int menuId,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            List<BigCategory> bigCategories = bigCategoryService.findAllBigCategoryByMenu(menuId);
            return new ResponseEntity<>(bigCategories, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find-by-id")
    public ResponseEntity<BigCategory> findBigCategoryById(
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {

            return new ResponseEntity<>(bigCategoryService.findBigCategoryById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
