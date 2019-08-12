package com.bksoftwarevn.controller.admin.company;

import com.bksoftwarevn.commom.Token;
import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.company.ContactForm;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.company.ContactFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin/contact-form")
@RolesAllowed("ROLE_ADMIN")
public class AdminContactFormController {

    @Autowired
    private ContactFormService contactFormService;

    @Autowired
    private RecordService recordService;

    @GetMapping(value = "/page")
    public ResponseEntity<List<ContactForm>> showBigCategory(
            HttpServletResponse response,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;

        Pageable pageable = PageRequest.of(page - 1, size);
        List<ContactForm> contactForms = contactFormService.findAllContactFormPage(pageable);
        return new ResponseEntity<>(contactForms, HttpStatus.OK);
    }

    @GetMapping(value = "/size")
    public ResponseEntity<Double> pageNumberContactForm(
            HttpServletResponse response
    ) {
        Record record = recordService.findByName("contact-form");
        double result = Math.ceil((double) record.getNumber() / 10);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ContactForm> updateContactForm(
            @RequestBody ContactForm contactForm
    ) {
        boolean result = contactFormService.saveContactForm(contactForm);
        if (result) return new ResponseEntity<>(contactForm, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/delete")
    public ResponseEntity<Object> deleteContactForm(
            @RequestParam("id") int contactId
    ) {
        Record record = recordService.findByName("contact-form");
        boolean result = contactFormService.deleteContactForm(contactFormService.findById(contactId));
        if (result) {
            record.setStatus(false);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete success!", HttpStatus.OK);
        }
        return new ResponseEntity<>("delete fail!", HttpStatus.BAD_REQUEST);
    }

}
