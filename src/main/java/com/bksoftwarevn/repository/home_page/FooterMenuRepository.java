package com.bksoftwarevn.repository.home_page;

import com.bksoftwarevn.entities.home_page.FooterMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FooterMenuRepository extends JpaRepository<FooterMenu, Integer> {

    List<FooterMenu> findByStatus(boolean status);

    FooterMenu findById(int id);

}
