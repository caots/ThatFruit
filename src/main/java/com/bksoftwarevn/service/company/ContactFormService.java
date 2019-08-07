package com.bksoftwarevn.service.company;

import com.bksoftwarevn.entities.company.ContactForm;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContactFormService {

    List<ContactForm> findAllContactFormPage(Pageable pageable);

    ContactForm findById(int id);

    boolean saveContactForm(ContactForm contactForm);

    boolean deleteContactForm(ContactForm contactForm);
}
