package com.bksoftwarevn.controller.viewer.home_page;

import com.bksoftwarevn.commom.Token;
import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.home_page.FooterMenu;
import com.bksoftwarevn.entities.home_page.FooterMenuDetails;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.home_page.FooterMenuDetailsService;
import com.bksoftwarevn.service.home_page.FooterMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/footer-menu-details")
public class FooterMenuDetailsController {

    @Autowired
    private FooterMenuDetailsService footerMenuDetailsService;

    @Autowired
    private FooterMenuService footerMenuService;

    @Autowired
    private RecordService recordService;

    @GetMapping("/all")
    public ResponseEntity<List<FooterMenuDetails>> showFooterSmall(
            @RequestParam("footer-menu-id") int footerMenuId,
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        if (header.equals(Token.tokenHeader)) {
            FooterMenu footerMenu = footerMenuService.findById(footerMenuId);
            List<FooterMenuDetails> lstFooterDetail = footerMenuDetailsService.findByFooterMenu(footerMenu);
            return new ResponseEntity<>(lstFooterDetail, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<List<FooterMenuDetails>> findAllFooterMenuDetailsPage(
            HttpServletResponse response,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            if (page < 1) page = 1;
            if (size < 0) size = 0;
            Pageable pageable = PageRequest.of(page - 1, size);
            List<FooterMenuDetails> footerMenuDetails = footerMenuDetailsService.findAllFooterMenuDetailsPage(pageable);
            return new ResponseEntity<>(footerMenuDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/size")
    public ResponseEntity<Double> pageNumberMediumCategory(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        Record record = recordService.findByName("footer-menu-details");
        if (header.equals(Token.tokenHeader)) {
            double result = Math.ceil((double) record.getNumber() / 10);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<FooterMenuDetails> findFooterBigById(
            HttpServletResponse response,
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            FooterMenuDetails footerMenuDetails = footerMenuDetailsService.findById(id);
            return new ResponseEntity<>(footerMenuDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
