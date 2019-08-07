package com.bksoftwarevn.service.company;

import com.bksoftwarevn.entities.company.Company;
import com.bksoftwarevn.entities.company.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> findByCompany(Company company);

    Contact findById(int id);

    boolean saveContact(Contact contact);

    boolean deleteContact(Contact contact);
}
