package com.bksoftwarevn.service_impl.company;


import com.bksoftwarevn.entities.company.Company;
import com.bksoftwarevn.entities.company.Contact;
import com.bksoftwarevn.repository.company.ContactRepository;
import com.bksoftwarevn.service.company.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ContactService_Impl implements ContactService {

    private final static Logger LOGGER = Logger.getLogger(ContactService_Impl.class.getName());

    @Autowired
    private ContactRepository contactRepository;


    @Override
    public List<Contact> findByCompany(Company company) {
        try {
            return contactRepository.findByCompany(company)
                    .stream()
                    .filter(Contact::isStatus)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-list-contact-by-company-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Contact> findAllContact() {
        try {
            return contactRepository.findByStatus(true);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-list-contact-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Contact findById(int id) {
        try {
            Contact contact = contactRepository.findById(id);
            if (contact.isStatus()) return contact;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-contact-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveContact(Contact contact) {
        try {
            contactRepository.save(contact);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-contact-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteContact(Contact contact) {
        try {
            if (contact.isStatus()) {
                contact.setStatus(false);
                contactRepository.save(contact);
                return true;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-contact-error : {0}", ex.getMessage());
        }
        return false;
    }
}
