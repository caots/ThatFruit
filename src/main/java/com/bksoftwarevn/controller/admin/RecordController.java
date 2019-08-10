package com.bksoftwarevn.controller.admin;

import com.bksoftwarevn.commom.Token;
import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.product.Product;
import com.bksoftwarevn.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin/record")
@RolesAllowed("ROLE_ADMIN")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/all")
    public ResponseEntity<List<Record>> findAllProduct() {
        List<Record> records = recordService.findAllRecord();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

}
