package com.bksoftwarevn.controller.admin.company;

import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.company.Partner;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.company.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/v1/admin/partner")
@RolesAllowed("ROLE_ADMIN")
public class AdminPartnerController {

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private RecordService recordService;

    @PostMapping
    public ResponseEntity<Object> addPartner(@RequestBody Partner partner) {
        Record record = recordService.findByName("partner");
        partner.setStatus(true);
        if (partnerService.savePartner(partner)) {
            record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(partner, HttpStatus.OK);
        } else
            return new ResponseEntity<>("add partner fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<Object> updatePartner(@RequestBody Partner partner) {
        if (partnerService.savePartner(partner))
            return new ResponseEntity<>(partner, HttpStatus.OK);
        else
            return new ResponseEntity<>("update partner fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/delete")
    public ResponseEntity<String> deletePartner(@RequestParam("id") int id) {
        Record record = recordService.findByName("partner");
        Partner partner = partnerService.findById(id);
        if (partnerService.deletePartner(partner)) {
            record.setNumber(record.getNumber() - 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete partner success", HttpStatus.OK);
        } else
            return new ResponseEntity<>("delete partner fail", HttpStatus.BAD_REQUEST);
    }

}
