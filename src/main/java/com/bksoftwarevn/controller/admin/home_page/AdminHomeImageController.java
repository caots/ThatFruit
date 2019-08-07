package com.bksoftwarevn.controller.admin.home_page;

import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.home_page.ImagePage;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.home_page.ImagePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/v1/admin/home-image")
@RolesAllowed("ROLE_ADMIN")
public class AdminHomeImageController {

    @Autowired
    private ImagePageService imagePageService;

    @Autowired
    private RecordService recordService;

    @PostMapping
    public ResponseEntity<Object> createHomeImage(@RequestBody ImagePage imagePage) {
        imagePage.setStatus(true);
        Record record = recordService.findByName("image-page");
        if (imagePageService.saveImagePage(imagePage)) {
            record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(imagePage, HttpStatus.OK);
        } else return new ResponseEntity<>("create home image error", HttpStatus.BAD_REQUEST);
    }


    @PutMapping
    public ResponseEntity<Object> updateHomeImage(@RequestBody ImagePage imagePage) {
        if (imagePageService.saveImagePage(imagePage)) {
            return new ResponseEntity<>(imagePage, HttpStatus.OK);
        } else return new ResponseEntity<>("update home image error", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/delete")
    public ResponseEntity<String> deleteHomeImage(@RequestBody ImagePage imagePage) {
        Record record = recordService.findByName("image-page");
        if (imagePageService.deleteImagePage(imagePage)) {
            record.setNumber(record.getNumber() - 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete home image success", HttpStatus.OK);
        } else return new ResponseEntity<>("delete home image error", HttpStatus.BAD_REQUEST);
    }


}
