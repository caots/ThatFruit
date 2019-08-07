package com.bksoftwarevn.repository.category;

import com.bksoftwarevn.entities.category.BigCategory;
import com.bksoftwarevn.entities.category.SmallCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmallCategoryRepository extends JpaRepository<SmallCategory, Integer> {

    List<SmallCategory> findByBigCategory(BigCategory bigCategory);

    SmallCategory findById(int id);

    @Query("select s from SmallCategory s where s.status = true and s.bigCategory.id= :id")
    Page<SmallCategory> findSmallByBigCategoryPage(@Param("id") int id, Pageable pageable);


    Page<SmallCategory> findByStatus(boolean status, Pageable pageable);

    @Query("select s from SmallCategory s where s.status=true")
    List<SmallCategory> findAllSmallCategory();

}
