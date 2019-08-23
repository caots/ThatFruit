package com.bksoftwarevn.controller.viewer.company;


import com.bksoftwarevn.commom.Token;
import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.company.ContactForm;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.company.ContactFormService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/public/contact-form")
public class ContactFormController {

    @Autowired
    private ContactFormService contactFormService;

    @Autowired
    private RecordService recordService;


    @GetMapping(value = "/find-by-id")
    public ResponseEntity<ContactForm> findContactFormById(
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {

            return new ResponseEntity<>(contactFormService.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //=============================================================================
    @PostMapping
    public ResponseEntity<ContactForm> createContactForm(
            @RequestBody ContactForm contactForm,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            contactForm.setStatus(true);
            contactForm.setChecked(false);
            boolean result = contactFormService.saveContactForm(contactForm);
            System.out.println(result);
            Record record = recordService.findByName("contact-form");
            if (result) {
                record.setNumber(record.getNumber() + 1);
                recordService.saveRecord(record);
                return new ResponseEntity<>(contactForm, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
