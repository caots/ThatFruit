package com.bksoftwarevn.repository.home_page;

import com.bksoftwarevn.entities.home_page.FooterMenu;
import com.bksoftwarevn.entities.home_page.FooterMenuDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FooterMenuDetailsRepository extends JpaRepository<FooterMenuDetails, Integer> {

    Page<FooterMenuDetails> findByStatus(boolean status, Pageable pageable);

    List<FooterMenuDetails> findByFooterMenu(FooterMenu footerMenu);

    FooterMenuDetails findById(int id);

}
