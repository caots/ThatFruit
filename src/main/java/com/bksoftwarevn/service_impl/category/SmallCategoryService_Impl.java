package com.bksoftwarevn.service_impl.category;

import com.bksoftwarevn.entities.category.BigCategory;
import com.bksoftwarevn.entities.category.SmallCategory;
import com.bksoftwarevn.repository.category.BigCategoryRepository;
import com.bksoftwarevn.repository.category.SmallCategoryRepository;
import com.bksoftwarevn.service.category.SmallCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class SmallCategoryService_Impl implements SmallCategoryService {

    private final static Logger LOGGER = Logger.getLogger(SmallCategoryService_Impl.class.getName());

    @Autowired
    private SmallCategoryRepository smallCategoryRepository;

    @Autowired
    private BigCategoryRepository bigCategoryRepository;

    @Override
    public List<SmallCategory> findAllSmallCategoryPage(Pageable pageable) {
        try {
            return smallCategoryRepository.findByStatus(true, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-small-category-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<SmallCategory> findAllSmallCategory() {
        return smallCategoryRepository.findAllSmallCategory();
    }

    @Override
    public List<SmallCategory> findAllSmallByBigPage(int bigCategoryId, Pageable pageable) {
        try {
            return smallCategoryRepository.findSmallByBigCategoryPage(bigCategoryId, pageable).getContent();

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-small-by-big-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<SmallCategory> findAllSmallByBig(int bigCategoryId) {
        try {
            BigCategory bigCategory = bigCategoryRepository.findById(bigCategoryId);
            if (bigCategory.isStatus()) return smallCategoryRepository.findByBigCategory(bigCategory);

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-small-by-big--error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public int sizeOfSmallCategoryByBig(int bigCategoryId) {
        try {
            BigCategory bigCategory = bigCategoryRepository.findById(bigCategoryId);
            if (bigCategory.isStatus()) {
                List<SmallCategory> smallCategories = smallCategoryRepository.findByBigCategory(bigCategory)
                        .stream()
                        .filter(SmallCategory::isStatus)
                        .collect(Collectors.toList());
                return smallCategories.size();
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "-error : {0}", ex.getMessage());
        }
        return 0;
    }

    @Override
    public SmallCategory findSmallCategoryById(int id) {
        try {
            SmallCategory smallCategory = smallCategoryRepository.findById(id);
            if (smallCategory.isStatus()) return smallCategory;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-small-category-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveSmallCategory(SmallCategory smallCategory) {
        try {
            smallCategoryRepository.save(smallCategory);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteSmallCategory(SmallCategory smallCategory) {
        try {
            if (smallCategory.isStatus()) {
                smallCategory.setStatus(false);
                smallCategoryRepository.save(smallCategory);
                return true;
            }


        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "-error : {0}", ex.getMessage());
        }
        return false;
    }
}
