package com.bksoftwarevn.controller.viewer.product;

import com.bksoftwarevn.commom.Token;
import com.bksoftwarevn.entities.product.ProductImage;
import com.bksoftwarevn.service.product.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/public/product-image")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ProductImage>> findAllProductImage(
            @RequestHeader("adminbksoftwarevn") String header) {
        if (header.equals(Token.tokenHeader)) {
            List<ProductImage> productImages = productImageService.findAllProductImage();
            return new ResponseEntity<>(productImages, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find-by-product")
    public ResponseEntity<List<ProductImage>> findAllProductImageByProductId(
            @RequestParam("product-id") int productId,
            @RequestHeader("adminbksoftwarevn") String header) {
        if (header.equals(Token.tokenHeader)) {
            List<ProductImage> productImages = productImageService.findAllProductImageByProductId(productId);
            return new ResponseEntity<>(productImages, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find-by-id")
    public ResponseEntity<ProductImage> findProductImageById(
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            return new ResponseEntity<>(productImageService.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
