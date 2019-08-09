package com.bksoftwarevn.service.category;

import com.bksoftwarevn.entities.category.BigCategory;

import com.bksoftwarevn.entities.category.Menu;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface BigCategoryService {

    List<BigCategory> findAllBigCategoryPage(Pageable pageable);

    List<BigCategory> findAllBigCategory();

    List<BigCategory> findAllBigByMenuPage(int menuId, Pageable pageable);

    List<BigCategory> findAllBigCategoryByMenu(int menuId);

    int sizeOfBigCategoryByMenu(int menuId);

    int sizeOfAllBigCategory();

    BigCategory findBigCategoryById(int id);

    boolean saveBigCategory(BigCategory bigCategory);

    boolean deleteBigCategory(BigCategory bigCategory);
}
