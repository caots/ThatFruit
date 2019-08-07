package com.bksoftwarevn.service_impl.company;

import com.bksoftwarevn.entities.company.Company;
import com.bksoftwarevn.repository.company.CompanyRepository;
import com.bksoftwarevn.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CompanyService_Impl implements CompanyService {

    private final static Logger LOGGER = Logger.getLogger(CompanyService_Impl.class.getName());


    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> findAllCompany() {
        try {
            return companyRepository.findByStatus(true);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-company-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Company findById(int id) {
        try {
            Company company = companyRepository.findById(id);
            if (company.isStatus()) return company;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveCompany(Company company) {
        try {
            companyRepository.save(company);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-company-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteCompany(Company company) {
        try {
            if (company.isStatus()) {
                company.setStatus(false);
                companyRepository.save(company);
                return true;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-company-error : {0}", ex.getMessage());
        }
        return false;
    }
}
