package com.bksoftwarevn.controller.admin.company;

import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.company.Company;
import com.bksoftwarevn.entities.company.Contact;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.company.CompanyService;
import com.bksoftwarevn.service.company.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/v1/admin/company")
@RolesAllowed("ROLE_ADMIN")
public class AdminCompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<Object> createCompany(@RequestBody Company company) {
        Record record = recordService.findByName("company");
        company.setStatus(true);
        if (companyService.saveCompany(company)) {
            record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else return new ResponseEntity<>("create company fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<Object> updateCompany(@RequestBody Company company) {
        if (companyService.saveCompany(company)) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else return new ResponseEntity<>("update company fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/delete")
    public ResponseEntity<String> deleteCompany(@RequestParam("id") int id) {
        Company company = companyService.findById(id);
        Record record = recordService.findByName("company");
        if (companyService.deleteCompany(company)) {
            record.setNumber(record.getNumber() - 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete company success", HttpStatus.OK);
        } else return new ResponseEntity<>("delete company fail!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/contact")
    public ResponseEntity<Object> createContactCompany(
            @RequestParam("company-id") int companyId,
            @RequestBody Contact contact) {
        contact.setStatus(true);
        contact.setCompany(companyService.findById(companyId));
        if (contactService.saveContact(contact)) {
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } else return new ResponseEntity<>("create contact company fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/contact")
    public ResponseEntity<Object> updateContactCompany(@RequestBody Contact contact) {
        if (contactService.saveContact(contact)) {
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } else return new ResponseEntity<>("update contact company fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/contact/delete")
    public ResponseEntity<String> deleteContactCompany(@RequestParam("id") int id) {
        Contact contact = contactService.findById(id);
        if (contactService.deleteContact(contact)) {
            return new ResponseEntity<>("delete contact company success", HttpStatus.OK);
        } else return new ResponseEntity<>("delete contact company fail!", HttpStatus.BAD_REQUEST);
    }



}
