package com.bksoftwarevn.service_impl.category;

import com.bksoftwarevn.entities.category.Menu;
import com.bksoftwarevn.repository.category.MenuRepository;
import com.bksoftwarevn.service.category.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MenuService_Impl implements MenuService {

    private final static Logger LOGGER = Logger.getLogger(MenuService_Impl.class.getName());

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findAllMenuPage(Pageable pageable) {
        try {
            return menuRepository.FindAllMenuPage(pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-menu-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Menu> findAllMenu() {
        try {
            return menuRepository.findByStatus(true);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-menu-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Menu findMenuById(int id) {
        try {
            Menu menu = menuRepository.findById(id);
            if (menu.isStatus()) return menu;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-menu-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveBMenu(Menu menu) {
        try {
            menuRepository.save(menu);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-menu-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteBMenu(Menu menu) {
        try {
            if (menu.isStatus()) {
                menu.setStatus(false);
                menuRepository.save(menu);
                return true;
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-menu-error : {0}", ex.getMessage());
        }

        return false;
    }
}
