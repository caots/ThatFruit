package com.bksoftwarevn.controller.viewer.home_page;


import com.bksoftwarevn.commom.Token;
import com.bksoftwarevn.entities.home_page.FooterMenu;
import com.bksoftwarevn.service.home_page.FooterMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/footer-menu")
public class FooterMenuController {

    @Autowired
    private FooterMenuService footerMenuService;

    @GetMapping("/all")
    public ResponseEntity<List<FooterMenu>> findAllFooterMenu(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header) {

        if (header.equals(Token.tokenHeader)) {
            List<FooterMenu> lstFooter = footerMenuService.findAllFooterMenu();
            return new ResponseEntity<>(lstFooter, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<FooterMenu> findFooterMenuById(
            HttpServletResponse response,
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            FooterMenu footer = footerMenuService.findById(id);
            return new ResponseEntity<>(footer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
