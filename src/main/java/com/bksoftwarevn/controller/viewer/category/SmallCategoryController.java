package com.bksoftwarevn.controller.viewer.category;


import com.bksoftwarevn.commom.Token;
import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.category.BigCategory;
import com.bksoftwarevn.entities.category.SmallCategory;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.category.SmallCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/small-category")
public class SmallCategoryController {

    @Autowired
    private SmallCategoryService smallCategoryService;

    @Autowired
    private RecordService recordService;


    @GetMapping(value = "/page")
    public ResponseEntity<List<SmallCategory>> findAllSmallCategoryPage(
            HttpServletResponse response,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;

        if (header.equals(Token.tokenHeader)) {
            Pageable pageable = PageRequest.of(page - 1, size);
            List<SmallCategory> lstSmallCategory = smallCategoryService.findAllSmallCategoryPage(pageable);
            return new ResponseEntity<>(lstSmallCategory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<SmallCategory>> findAllSmallCategoryAll(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            List<SmallCategory> lstSmallCategory = smallCategoryService.findAllSmallCategory();
            return new ResponseEntity<>(lstSmallCategory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/size")
    public ResponseEntity<Double> pagesNumberSmallCategory(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            Record record = recordService.findByName("small-category");
            double result = Math.ceil((double) record.getNumber() / 10);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "find-by-big")
    public ResponseEntity<List<SmallCategory>> allSmallCategory(
            @RequestHeader("adminbksoftwarevn") String header,
            @RequestParam("big-id") int bigCategoryId
    ) {
        if (header.equals(Token.tokenHeader)) {
            List<SmallCategory> lstSmallCategory = smallCategoryService.findAllSmallByBig(bigCategoryId);
            return new ResponseEntity<>(lstSmallCategory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find-by-id")
    public ResponseEntity<SmallCategory> findSmallCategoryById(
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {

            return new ResponseEntity<>(smallCategoryService.findSmallCategoryById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
