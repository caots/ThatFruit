package com.bksoftwarevn.service.home_page;

import com.bksoftwarevn.entities.home_page.FooterMenu;
import com.bksoftwarevn.entities.home_page.FooterMenuDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FooterMenuDetailsService {

    List<FooterMenuDetails> findAllFooterMenuDetailsPage(Pageable pageable);

    List<FooterMenuDetails> findByFooterMenu(FooterMenu footerMenu);

    FooterMenuDetails findById(int id);

    boolean saveFooterMenuDetails(FooterMenuDetails footerMenuDetails);

    boolean deleteFooterMenuDetails(FooterMenuDetails footerMenuDetails);

}
