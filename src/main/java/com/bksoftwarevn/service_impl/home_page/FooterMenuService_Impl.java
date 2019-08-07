package com.bksoftwarevn.service_impl.home_page;

import com.bksoftwarevn.entities.home_page.FooterMenu;
import com.bksoftwarevn.repository.home_page.FooterMenuRepository;
import com.bksoftwarevn.service.home_page.FooterMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FooterMenuService_Impl implements FooterMenuService {

    private final static Logger LOGGER = Logger.getLogger(FooterMenuService_Impl.class.getName());

    @Autowired
    private FooterMenuRepository footerMenuRepository;


    @Override
    public List<FooterMenu> findAllFooterMenu() {
        try {
            return footerMenuRepository.findByStatus(true);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-footer-menu-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public FooterMenu findById(int id) {
        try {
            FooterMenu footerMenu = footerMenuRepository.findById(id);
            if (footerMenu.isStatus()) return footerMenu;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-footer-menu-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveFooterMenu(FooterMenu footerMenu) {
        try {
            footerMenuRepository.save(footerMenu);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-footer-menu-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteFooterMenu(FooterMenu footerMenu) {
        try {
            if (footerMenu.isStatus()) {
                footerMenu.setStatus(false);
                footerMenuRepository.save(footerMenu);
                return true;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-footer-menu-error : {0}", ex.getMessage());
        }
        return false;
    }
}
