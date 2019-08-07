package com.bksoftwarevn.service_impl.company;

import com.bksoftwarevn.entities.company.ContactForm;
import com.bksoftwarevn.repository.company.ContactFormRepository;
import com.bksoftwarevn.service.company.ContactFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ContactFormService_Impl implements ContactFormService {

    private final static Logger LOGGER = Logger.getLogger(ContactFormService_Impl.class.getName());

    @Autowired
    private ContactFormRepository contactFormRepository;

    @Override
    public List<ContactForm> findAllContactFormPage(Pageable pageable) {
        try {
            return contactFormRepository.findByStatus(true, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-contact-form-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public ContactForm findById(int id) {
        try {
            ContactForm contactForm = contactFormRepository.findById(id);
            if (contactForm.isStatus()) return contactForm;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-contact-form-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveContactForm(ContactForm contactForm) {
        try {
            contactFormRepository.save(contactForm);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-contact-form-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteContactForm(ContactForm contactForm) {
        try {
            if (contactForm.isStatus()) {
                contactForm.setStatus(false);
                contactFormRepository.save(contactForm);
                return true;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-contact-form-error : {0}", ex.getMessage());
        }
        return false;
    }
}
