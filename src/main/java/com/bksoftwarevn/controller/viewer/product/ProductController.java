package com.bksoftwarevn.controller.viewer.product;


import com.bksoftwarevn.commom.Token;
import com.bksoftwarevn.entities.product.Product;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.company.PartnerService;
import com.bksoftwarevn.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/public/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    PartnerService partnerService_imp;

    @Autowired
    RecordService recordService;

    private static String productNameSearch = "";


    @GetMapping("/page")
    public ResponseEntity<List<Product>> findAllProductPage(
            HttpServletResponse response,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;
        if (header.equals(Token.tokenHeader)) {
            Pageable pageable = PageRequest.of(page - 1, size);
            List<Product> products = productService.findNewProducts(pageable);
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/size")
    public ResponseEntity<Double> pageNumberProduct() {
        float pageNumber = recordService.findByName("product").getNumber();
        double result = Math.ceil((double) pageNumber / 10);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct(@RequestHeader("adminbksoftwarevn") String header) {
        if (header.equals(Token.tokenHeader)) {
            List<Product> products = productService.findAllProduct();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name/page")
    public ResponseEntity<List<Product>> findProductByNamePage(
            HttpServletResponse response,
            @RequestParam("name") String name,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort,
            @RequestParam(name = "field", required = false, defaultValue = "id") String field,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            productNameSearch = name;
            Sort sortable = productService.sortData(sort, field);
            if (page < 1) page = 1;
            if (size < 0) size = 0;
            Pageable pageable = PageRequest.of(page - 1, size, sortable);
            List<Product> productsByName = productService.findProductByNamePage(name, pageable);
            return new ResponseEntity<>(productsByName, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name/size")
    public ResponseEntity<Double> findProductByNameSize(
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {

            double result = Math.ceil((double) productService.sizeOfProductByName(productNameSearch) / size);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/by-small-category/page")
    public ResponseEntity<List<Product>> findAllProductBySmallCategoryPage(
            HttpServletResponse response,
            @RequestParam(name = "id-small") int id,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "18") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "id") String field,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            Sort sortable = productService.sortData(type, field);
            if (page < 1) page = 1;
            if (size < 0) size = 0;
            Pageable pageable = PageRequest.of(page - 1, size, sortable);
            List<Product> lstProduct = productService.findProductBySmallCategoryPage(id, pageable);
            return new ResponseEntity<>(lstProduct, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/by-small-category/size")
    public ResponseEntity<Double> findProductBySmallCategorySize(
            HttpServletResponse response,
            @RequestParam(name = "id-small") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            double result = Math.ceil((double) productService.sizeOfProductBySmallCategory(id) / 18);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/by-big-category/page")
    public ResponseEntity<List<Product>> findProductByBigCategoryPage(
            HttpServletResponse response,
            @RequestParam(name = "id-big") int id,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "18") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "id") String field,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            Sort sortable = productService.sortData(type, field);
            if (page < 1) page = 1;
            if (size < 0) size = 0;
            Pageable pageable = PageRequest.of(page - 1, size, sortable);
            List<Product> lstProductByBigCategory = productService.findProductByBigCategoryPage(id, pageable);
            return new ResponseEntity<>(lstProductByBigCategory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/by-big-category/size")
    public ResponseEntity<Double> findProductBigCategorySize(
            HttpServletResponse response,
            @RequestParam(name = "id-big") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            double result = Math.ceil((double) productService.sizeOfProductByBigCategory(id) / 18);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/by-menu/page")
    public ResponseEntity<List<Product>> findProductByMenuPage(
            HttpServletResponse response,
            @RequestParam(name = "id-menu") int id,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "18") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "id") String field,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            Sort sortable = productService.sortData(type, field);
            if (page < 1) page = 1;
            if (size < 0) size = 0;
            Pageable pageable = PageRequest.of(page - 1, size, sortable);
            List<Product> lstProductBig = productService.findProductByMenuPage(id, pageable);
            return new ResponseEntity<>(lstProductBig, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/by-menu/size")
    public ResponseEntity<Double> findProductByBigSize(
            HttpServletResponse response,
            @RequestParam(name = "id-menu") int id) {
        double result = Math.ceil((double) productService.sizeOfProductByMenu(id) / 18);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/by-partner/page")
    public ResponseEntity<List<Product>> findProductByPartner(
            HttpServletResponse response,
            @RequestParam(name = "id-partner") int idPartner,
            @RequestHeader("adminbksoftwarevn") String header,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "100") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field
    ) {
        if (header.equals(Token.tokenHeader)) {
            if (page < 1) page = 1;
            if (size < 0) size = 0;
            Pageable pageable = PageRequest.of(page - 1, size);
            List<Product> lstProductBig = productService.findAllProductByPartnerPage(idPartner, pageable);
            return new ResponseEntity<>(lstProductBig, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/by-partner/size")
    public ResponseEntity<Double> findProductByPartnerSize(
            HttpServletResponse response,
            @RequestParam(name = "id-partner") int id) {
        double result = Math.ceil((double) productService.sizeOfProductByPartner(id) / 18);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-sale")
    public ResponseEntity<List<Product>> showProductSale(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "id") String field
            , @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            Sort sortable = productService.sortData(type, field);
            Pageable pageable = PageRequest.of(page, size, sortable);
            List<Product> lstProductSale = productService.findProductSale(pageable);
            return new ResponseEntity<>(lstProductSale, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<Product> findProductById(
            @RequestParam("id") int id,
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            Product product = productService.findById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/by-product-type/page")
    public ResponseEntity<List<Product>> findProductByProductTypePage(
            HttpServletResponse response,
            @RequestParam(name = "id-product-type") int id,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "18") Integer size,
            @RequestParam(name = "type", required = false, defaultValue = "ASC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "id") String field,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            Sort sortable = productService.sortData(type, field);
            if (page < 1) page = 1;
            if (size < 0) size = 0;
            Pageable pageable = PageRequest.of(page - 1, size, sortable);
            List<Product> lstProductByProductType = productService.findProductByTypePage(id, pageable);
            return new ResponseEntity<>(lstProductByProductType, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/by-product-type/size")
    public ResponseEntity<Double> findProductByProductTypeSize(
            HttpServletResponse response,
            @RequestParam(name = "id-product-type") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            double result = Math.ceil((double) productService.sizeOfProductByType(id) / 18);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
