package com.bksoftwarevn.service_impl.home_page;

import com.bksoftwarevn.entities.home_page.FooterMenu;
import com.bksoftwarevn.entities.home_page.FooterMenuDetails;
import com.bksoftwarevn.repository.home_page.FooterMenuDetailsRepository;
import com.bksoftwarevn.service.home_page.FooterMenuDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class FooterMenuDetailsService_Impl implements FooterMenuDetailsService {

    private final static Logger LOGGER = Logger.getLogger(FooterMenuDetailsService_Impl.class.getName());

    @Autowired
    private FooterMenuDetailsRepository footerMenuDetailsRepository;

    @Override
    public List<FooterMenuDetails> findAllFooterMenuDetailsPage(Pageable pageable) {
        try {
            return footerMenuDetailsRepository.findByStatus(true, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-footer-menu-details-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<FooterMenuDetails> findByFooterMenu(FooterMenu footerMenu) {
        try {
            return footerMenuDetailsRepository.findByFooterMenu(footerMenu).stream()
                    .filter(FooterMenuDetails::isStatus)
                    .collect(Collectors.toList());

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-footer-menu-details-by-footer-menu-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public FooterMenuDetails findById(int id) {
        try {
            FooterMenuDetails footerMenuDetails = footerMenuDetailsRepository.findById(id);
            if (footerMenuDetails.isStatus()) return footerMenuDetails;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-footer-menu-details-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveFooterMenuDetails(FooterMenuDetails footerMenuDetails) {
        try {
            footerMenuDetailsRepository.save(footerMenuDetails);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-footer-menu-details-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteFooterMenuDetails(FooterMenuDetails footerMenuDetails) {
        try {
            if (footerMenuDetails.isStatus()) {
                footerMenuDetails.setStatus(false);
                footerMenuDetailsRepository.save(footerMenuDetails);
                return true;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-footer-menu-details-error : {0}", ex.getMessage());
        }
        return false;
    }
}
