package com.bksoftwarevn.service.company;

import com.bksoftwarevn.entities.company.Company;

import java.util.List;

public interface CompanyService {

    List<Company> findAllCompany();

    Company findById(int id);

    boolean saveCompany(Company company);

    boolean deleteCompany(Company company);

}
