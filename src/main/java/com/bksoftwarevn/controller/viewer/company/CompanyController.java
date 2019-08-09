package com.bksoftwarevn.controller.viewer.company;

import com.bksoftwarevn.commom.Token;
import com.bksoftwarevn.entities.company.Company;
import com.bksoftwarevn.entities.company.Contact;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.company.CompanyService;
import com.bksoftwarevn.service.company.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ContactService contactService;

    @GetMapping
    public ResponseEntity<List<Company>> findAllCompanies(
            HttpServletResponse response
            , @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            List<Company> companies = companyService.findAllCompany();
            if (companies != null) {
                return new ResponseEntity<>(companies, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/contact/company")
    public ResponseEntity<List<Contact>> findAllContactByCompany(
            HttpServletResponse response,
            @RequestParam("company-id") int companyId
            , @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            Company company = companyService.findById(companyId);
            List<Contact> contacts = contactService.findByCompany(company);
            if (contacts != null) {
                return new ResponseEntity<>(contacts, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/contact")
    public ResponseEntity<List<Contact>> findContactByCompany(
            HttpServletResponse response,
            @RequestParam("company-id") int companyId,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            Company company = companyService.findById(companyId);
            if (company.isStatus()) {
                List<Contact> contacts = contactService.findByCompany(company);
                if (contacts != null) {
                    return new ResponseEntity<>(contacts, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @GetMapping("find-by-id")
    public ResponseEntity<Company> findCompanyById(
            HttpServletResponse response,
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            Company company = companyService.findById(id);

            if (company == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/contact/find-by-id")
    public ResponseEntity<Contact> findContactById(
            HttpServletResponse response,
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            Contact contact = contactService.findById(id);

            if (contact == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(contact, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
