package com.bksoftwarevn.service_impl.company;

import com.bksoftwarevn.entities.company.Partner;
import com.bksoftwarevn.repository.company.PartnerRepository;
import com.bksoftwarevn.service.company.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PartnerService_Impl implements PartnerService {

    private final static Logger LOGGER = Logger.getLogger(PartnerService_Impl.class.getName());

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public List<Partner> findAllPartnerPage(Pageable pageable) {
        try {
            return partnerRepository.findByStatus(true, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-partner-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Partner> findAllPartner() {
        try {
            return partnerRepository.findAllPartner();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-partner-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Partner findById(int id) {
        try {
            Partner partner = partnerRepository.findById(id);
            if (partner.isStatus()) return partner;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean savePartner(Partner partner) {
        try {
            partnerRepository.save(partner);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-partner-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deletePartner(Partner partner) {
        try {
            if (partner.isStatus()) {
                partner.setStatus(false);
                partnerRepository.save(partner);
                return true;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-partner-error : {0}", ex.getMessage());
        }
        return false;
    }
}
