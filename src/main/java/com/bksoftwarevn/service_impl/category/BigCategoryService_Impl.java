package com.bksoftwarevn.service_impl.category;

import com.bksoftwarevn.entities.category.BigCategory;
import com.bksoftwarevn.entities.category.Menu;
import com.bksoftwarevn.repository.category.BigCategoryRepository;
import com.bksoftwarevn.repository.category.MenuRepository;
import com.bksoftwarevn.service.category.BigCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BigCategoryService_Impl implements BigCategoryService {

    private final static Logger LOGGER = Logger.getLogger(BigCategoryService.class.getName());

    @Autowired
    private BigCategoryRepository bigCategoryRepository;

    @Autowired
    private MenuRepository menuRepository;


    @Override
    public List<BigCategory> findAllBigCategoryPage(Pageable pageable) {
        try {
            return bigCategoryRepository.findByStatus(true, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-big-category-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<BigCategory> findAllBigCategory() {
        return bigCategoryRepository.findAllBigCategory();
    }

    @Override
    public List<BigCategory> findAllBigByMenuPage(int menuId, Pageable pageable) {
        try {
            return bigCategoryRepository.findBigCategoryByMenuPage(menuId, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-big-by-menu-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<BigCategory> findAllBigCategoryByMenu(int menuId) {
        try {
            Menu menu = menuRepository.findById(menuId);
            if (menu.isStatus()) return bigCategoryRepository.findByMenu(menu);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-big-by-menu-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public int sizeOfBigCategoryByMenu(int menuId) {
        try {
            Menu menu = menuRepository.findById(menuId);
            if (menu.isStatus()) {
                return bigCategoryRepository.findByMenu(menu).size();
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "size-of-big-category-by-menu-error : {0}", ex.getMessage());
        }
        return 0;
    }

    @Override
    public int sizeOfAllBigCategory() {
        return bigCategoryRepository.findAllBigCategory().size();
    }

    @Override
    public BigCategory findBigCategoryById(int id) {
        try {
            BigCategory bigCategory = bigCategoryRepository.findById(id);
            if (bigCategory.isStatus()) return bigCategory;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-big-category-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveBigCategory(BigCategory bigCategory) {
        try {

            bigCategoryRepository.save(bigCategory);
            return true;

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-big-category-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteBigCategory(BigCategory bigCategory) {
        try {
            if (bigCategory.isStatus()) {
                bigCategory.setStatus(false);
                bigCategoryRepository.save(bigCategory);
                return true;
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-big-category-error : {0}", ex.getMessage());
        }
        return false;
    }
}
