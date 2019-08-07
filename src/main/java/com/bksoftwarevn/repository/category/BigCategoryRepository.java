package com.bksoftwarevn.repository.category;

import com.bksoftwarevn.entities.category.BigCategory;
import com.bksoftwarevn.entities.category.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BigCategoryRepository extends JpaRepository<BigCategory, Integer> {


    List<BigCategory> findByMenu(Menu menu);

    BigCategory findById(int id);

    @Query("select b from BigCategory b where b.status = true and  b.menu.id= :id")
    Page<BigCategory> findBigCategoryByMenuPage(@Param("id") int id, Pageable pageable);

    Page<BigCategory> findByStatus(boolean status, Pageable pageable);


    @Query("select b from BigCategory b where b.status=true")
    List<BigCategory> findAllBigCategory();

}
