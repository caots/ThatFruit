package com.bksoftwarevn.repository.company;

import com.bksoftwarevn.entities.company.Company;
import com.bksoftwarevn.entities.company.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findByCompany(Company company);

    List<Contact> findByStatus(boolean status);

    Contact findById(int id);

}
