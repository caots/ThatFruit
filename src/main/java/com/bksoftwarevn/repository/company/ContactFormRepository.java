package com.bksoftwarevn.repository.company;

import com.bksoftwarevn.entities.company.ContactForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactFormRepository extends JpaRepository<ContactForm, Integer> {

    //@Query("select cf from ContactForm cf where cf.status = true and  b.menu.id= :id")
    Page<ContactForm> findByStatus(boolean status, Pageable pageable);

    ContactForm findById(int id);


}
