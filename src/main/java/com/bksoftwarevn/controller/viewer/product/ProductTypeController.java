package com.bksoftwarevn.controller.viewer.product;

import com.bksoftwarevn.commom.Token;
import com.bksoftwarevn.entities.product.ProductType;
import com.bksoftwarevn.service.product.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/public/product-type")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ProductType>> findAllProductType(@RequestHeader("adminbksoftwarevn") String header) {
        if (header.equals(Token.tokenHeader)) {
            List<ProductType> productTypes = productTypeService.findAllProductType();
            return new ResponseEntity<>(productTypes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find-by-id")
    public ResponseEntity<ProductType> findProductTypeById(
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            return new ResponseEntity<>(productTypeService.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
