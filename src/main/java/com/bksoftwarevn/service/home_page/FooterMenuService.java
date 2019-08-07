package com.bksoftwarevn.service.home_page;

import com.bksoftwarevn.entities.home_page.FooterMenu;

import java.util.List;

public interface FooterMenuService {

    List<FooterMenu> findAllFooterMenu();

    FooterMenu findById(int id);

    boolean saveFooterMenu(FooterMenu footerMenu);

    boolean deleteFooterMenu(FooterMenu footerMenu);
}
