package com.bksoftwarevn.controller.admin.home_page;

import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.home_page.FooterMenu;
import com.bksoftwarevn.entities.home_page.FooterMenuDetails;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.home_page.FooterMenuDetailsService;
import com.bksoftwarevn.service.home_page.FooterMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/v1/admin/footer-menu")
@RolesAllowed("ROLE_ADMIN")
public class AdminFooterMenuController {

    @Autowired
    private FooterMenuService footerMenuService;

    @Autowired
    private FooterMenuDetailsService footerMenuDetailsService;

    @Autowired
    private RecordService recordService;

    //============================= Footer Menu ================================
    @PostMapping
    public ResponseEntity<Object> createFooterMenu(@RequestBody FooterMenu footerMenu) {
        Record record = recordService.findByName("footer-menu");
        footerMenu.setStatus(true);
        if (footerMenuService.saveFooterMenu(footerMenu)) {
            record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(footerMenu, HttpStatus.OK);
        } else return new ResponseEntity<>("create footer menu fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<Object> updateFooterMenu(@RequestBody FooterMenu footerMenu) {
        if (footerMenuService.saveFooterMenu(footerMenu)) {
            return new ResponseEntity<>(footerMenu, HttpStatus.OK);
        } else return new ResponseEntity<>("update footer menu fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/delete")
    public ResponseEntity<String> deleteFooterMenu(@RequestParam("id") int id) {
        FooterMenu footerMenu = footerMenuService.findById(id);
        if (footerMenuService.deleteFooterMenu(footerMenu)) {
            return new ResponseEntity<>("delete footer menu success", HttpStatus.OK);
        } else return new ResponseEntity<>("delete footer menu error", HttpStatus.BAD_REQUEST);
    }
    //============================= Footer Menu Details ====================================

    @PostMapping(value = "/details")
    public ResponseEntity<Object> createFooterMenuDetails(@RequestBody FooterMenuDetails footerMenuDetails,
                                                          @RequestParam(name = "footer-menu-id") int footerMenuId) {
        footerMenuDetails.setStatus(true);
        footerMenuDetails.setFooterMenu(footerMenuService.findById(footerMenuId));
        Record record = recordService.findByName("details-footer-menu");
        record.setNumber(record.getNumber() + 1);
        recordService.saveRecord(record);
        if (footerMenuDetailsService.saveFooterMenuDetails(footerMenuDetails)) {
            return new ResponseEntity<>(footerMenuDetails, HttpStatus.OK);
        } else return new ResponseEntity<>("save  footer menu details error", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/details")
    public ResponseEntity<Object> updateFooterMenuDetails(@RequestBody FooterMenuDetails footerMenuDetails) {
        if (footerMenuDetailsService.saveFooterMenuDetails(footerMenuDetails)) {
            return new ResponseEntity<>(footerMenuDetails, HttpStatus.OK);
        } else return new ResponseEntity<>( "update  footer menu details error", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/details/delete")
    public ResponseEntity<String> deleteFooterMenuDetails(@RequestParam("id") int id) {
        Record record = recordService.findByName("details-footer-menu");
        record.setNumber(record.getNumber() - 1);
        recordService.saveRecord(record);
        FooterMenuDetails footerMenuDetails = footerMenuDetailsService.findById(id);
        if (footerMenuDetailsService.deleteFooterMenuDetails(footerMenuDetails)) {
            return new ResponseEntity<>("delete footer menu details success", HttpStatus.OK);
        } else return new ResponseEntity<>("delete  footer menu details error", HttpStatus.BAD_REQUEST);
    }

}
