package com.bksoftwarevn.controller.viewer.home_page;


import com.bksoftwarevn.commom.Token;
import com.bksoftwarevn.entities.home_page.ImagePage;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.home_page.ImagePageService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/image-page")
public class ImagePageController {

    @Autowired
    private ImagePageService imagePageService;

    @Autowired
    private RecordService recordService;

    @GetMapping(value = "/page")
    public ResponseEntity<List<ImagePage>> findAllImagePagePage(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            return new ResponseEntity<>(imagePageService.findAllImage(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ImagePage>> findAllImagePageBetweenId(
            @RequestParam("start-id") int startId,
            @RequestParam("end-id") int endId,
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            return new ResponseEntity<>(imagePageService.findAllImage(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @GetMapping(value = "/find-by-id")
    public ResponseEntity<ImagePage> findImagePageById(
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {

            return new ResponseEntity<>(imagePageService.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
