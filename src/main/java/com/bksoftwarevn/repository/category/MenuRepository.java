package com.bksoftwarevn.repository.category;

import com.bksoftwarevn.entities.category.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    Menu findByName(String name);

    Menu findById(int id);

    List<Menu> findByStatus(boolean status);

    @Query("select m from Menu m where m.status=true ")
    Page<Menu> FindAllMenuPage(Pageable pageable);
}
