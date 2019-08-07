package com.bksoftwarevn.controller.viewer.company;


import com.bksoftwarevn.commom.Token;
import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.company.Partner;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.company.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/partner")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private RecordService recordService;


    @GetMapping(value = "/page")
    public ResponseEntity<List<Partner>> findAllPartnerPage(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (page < 1) page = 1;
        if (size < 0) size = 0;
        if (header.equals(Token.tokenHeader)) {
            Pageable pageable = PageRequest.of(page - 1, size);
            List<Partner> lstPartner = partnerService.findAllPartnerPage(pageable);
            return new ResponseEntity<>(lstPartner, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/size")
    public ResponseEntity<Double> pageNumberPartner(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            Record record = recordService.findByName("partner");
            double result = Math.ceil((double) record.getNumber() / 10);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Partner>> findAllPartner(@RequestHeader("adminbksoftwarevn") String header) {
        if (header.equals(Token.tokenHeader)) {
            Record record = recordService.findByName("partner");

            List<Partner> partners = partnerService.findAllPartner();
            record.setNumber(partners.size());
            recordService.saveRecord(record);
            return new ResponseEntity<>(partners, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find-by-id")
    public ResponseEntity<Partner> findPartnerById(
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {

            return new ResponseEntity<>(partnerService.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
