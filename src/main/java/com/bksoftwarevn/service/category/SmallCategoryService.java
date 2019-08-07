package com.bksoftwarevn.service.category;


import com.bksoftwarevn.entities.category.SmallCategory;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SmallCategoryService {

    List<SmallCategory> findAllSmallCategoryPage(Pageable pageable);

    List<SmallCategory> findAllSmallByBigPage(int bigCategoryId, Pageable pageable);

    List<SmallCategory> findAllSmallByBig(int bigCategoryId);

    int sizeOfSmallCategoryByBig(int bigCategoryId);

    SmallCategory findSmallCategoryById(int id);

    boolean saveSmallCategory(SmallCategory smallCategory);

    boolean deleteSmallCategory(SmallCategory smallCategory);
}
