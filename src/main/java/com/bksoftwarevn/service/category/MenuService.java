package com.bksoftwarevn.service.category;

import com.bksoftwarevn.entities.category.Menu;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface MenuService {


    List<Menu> findAllMenuPage(Pageable pageable);

    List<Menu> findAllMenu();

    Menu findMenuById(int id);

    boolean saveBMenu(Menu menu);

    boolean deleteBMenu(Menu menu);


}
