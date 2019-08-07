package com.bksoftwarevn.repository.company;

import com.bksoftwarevn.entities.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Company findById(int id);

    List<Company> findByStatus(boolean status);

}
