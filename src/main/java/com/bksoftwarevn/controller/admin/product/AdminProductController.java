package com.bksoftwarevn.controller.admin.product;

import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.news.Tag;
import com.bksoftwarevn.entities.product.Product;
import com.bksoftwarevn.entities.product.ProductImage;
import com.bksoftwarevn.entities.product.ProductType;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.category.SmallCategoryService;
import com.bksoftwarevn.service.company.PartnerService;
import com.bksoftwarevn.service.product.ProductImageService;
import com.bksoftwarevn.service.product.ProductService;
import com.bksoftwarevn.service.product.ProductTypeService;
import com.bksoftwarevn.service.product.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/admin")
@RolesAllowed("ROLE_ADMIN")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private TagService tagService;

    @Autowired
    private SmallCategoryService smallCategoryService;

    @Autowired
    private PartnerService partnerService;


    //=========================== Product Type =====================================

    @PostMapping("/product-type")
    public ResponseEntity<Object> createProductType(
            @RequestBody ProductType productType,
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        productType.setStatus(true);
        Record record = recordService.findByName("product-type");
        boolean result = productTypeService.saveProductType(productType);
        if (result) {
            record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(productType, HttpStatus.OK);
        }
        return new ResponseEntity<>("create product type fail", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/product-type")
    public ResponseEntity<Object> updateProductType(
            @RequestBody ProductType productType,
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        boolean result = productTypeService.saveProductType(productType);
        if (result) return new ResponseEntity<>(productType, HttpStatus.OK);
        return new ResponseEntity<>("update product type fail", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/product-type/delete")
    public ResponseEntity<Object> deleteProductType(@RequestParam("id") int id,
                                                    HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Record record = recordService.findByName("product-type");
        ProductType productType = productTypeService.findById(id);
        boolean result = productTypeService.deleteProductType(productType);
        if (result) {
            record.setNumber(record.getNumber() - 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete product type success", HttpStatus.OK);
        }
        return new ResponseEntity<>("delete product type fail", HttpStatus.NOT_FOUND);
    }

    //=========================== Product Image ====================================

    @PostMapping("/product-image")
    public ResponseEntity<Object> createProductImage(
            @RequestParam("product_id") int productId,
            @RequestBody ProductImage productImage,
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        productImage.setStatus(true);
        productImage.setProduct(productService.findById(productId));
        boolean result = productImageService.saveProductImage(productImage);
        if (result) {
            return new ResponseEntity<>(productImage, HttpStatus.OK);
        }
        return new ResponseEntity<>("create product image fail", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/product-image")
    public ResponseEntity<Object> updateProductImage(
            @RequestBody ProductImage productImage,
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        boolean result = productImageService.saveProductImage(productImage);
        if (result) return new ResponseEntity<>(productImage, HttpStatus.OK);
        return new ResponseEntity<>("update product image fail", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/product-image/delete")
    public ResponseEntity<Object> deleteProductImage(
            @RequestParam("id") int id,
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        ProductImage productImage = productImageService.findById(id);
        boolean result = productImageService.deleteProductImage(productImage);
        if (result)
            return new ResponseEntity<>("delete product image success", HttpStatus.OK);

        return new ResponseEntity<>("delete product image fail", HttpStatus.NOT_FOUND);
    }

    //=========================== Product ==========================================

    @PostMapping(value = "/product")
    public ResponseEntity<Object> addProduct(
            @RequestBody Product product,
            @RequestParam(name = "small-category-id") int smallCategoryId,
            @RequestParam(defaultValue = "-1", required = false, value = "partner-id") int partnerId,
            @RequestParam("tag") String tagString
    ) {
        Record record = recordService.findByName("product");
        product.setStatus(true);
        product.setView(0);
        product.setInitDate(LocalDate.now());
        product.setProductStatus(true);
        product.setSaleNumber(0);
        product.setSmallCategory(smallCategoryService.findSmallCategoryById(smallCategoryId));
        if (partnerId > 0) product.setPartner(partnerService.findById(partnerId));

        Set<Integer> listTagId = productService.listTagAdd(tagString);
        List<Tag> tagList = new ArrayList<>();
        listTagId.forEach(id -> {
            Tag tag = tagService.findById(id);
            if (tag != null) {
                tagList.add(tag);
            }
        });
        product.setTags(tagList);

        if (productService.saveProduct(product)) {
            record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else return new ResponseEntity<>("add product fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/product")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product,
                                                @RequestParam("list-tag") String listTag) {

        Set<Integer> listTagId = productService.listTagAdd(listTag);
        List<Tag> tagList = new ArrayList<>();
        listTagId.forEach(id -> {
            Tag tag = tagService.findById(id);
            if (tag != null) {
                tagList.add(tag);
            }
        });
        product.setTags(tagList);

        if (productService.saveProduct(product))
            return new ResponseEntity<>(product, HttpStatus.OK);
        else return new ResponseEntity<>("update product fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/product/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam("id") int idProduct) {
        Product product = productService.findById(idProduct);
        Record record = recordService.findByName("product");
        if (productService.deleteProduct(product)) {
            record.setNumber(record.getNumber() - 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete product success", HttpStatus.OK);
        } else return new ResponseEntity<>("delete product fail", HttpStatus.BAD_REQUEST);
    }


}
