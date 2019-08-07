package com.bksoftwarevn.controller.admin.product;

import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.news.Tag;
import com.bksoftwarevn.entities.product.BuyForm;
import com.bksoftwarevn.entities.product.BuyFormDetail;
import com.bksoftwarevn.entities.product.BuyFormHasProduct;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.product.BuyFormService;
import com.bksoftwarevn.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RolesAllowed("ROLE_ADMIN")
@RequestMapping("api/v1/admin")
public class AdminBuyFormController {

    @Autowired
    private BuyFormService buyFormService;

    @Autowired
    private ProductService productService;

    @Autowired
    private RecordService recordService;


    @GetMapping("/buy-form/uncheck")
    public ResponseEntity<List<BuyForm>> findAllUncheckBuyFormPage(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;
        Pageable pageable = PageRequest.of(page - 1, size);
        if (buyFormService.findAllUnCheckBuyFormPage(pageable) != null) {
            return new ResponseEntity<>(buyFormService.findAllUnCheckBuyFormPage(pageable), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "buy-form/size")
    public ResponseEntity<Double> pageNumberMediumCategory(HttpServletResponse response) {
        double result = Math.ceil((double) buyFormService.sizeOfUnCheckBuyForm() / 10);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PutMapping(value = "/buy-form/check", params = "buy-form-id")
    public ResponseEntity<String> checkBuyForm(@RequestParam(name = "buy-form-id") int id) {
        BuyForm buyForm = buyFormService.findById(id);
        List<BuyFormHasProduct> buyFormHasProducts = buyFormService.findAllBuyFormHasProductByBuyFormId(id);
        if (buyFormHasProducts.isEmpty()) return new ResponseEntity<>("no product", HttpStatus.BAD_REQUEST);
        buyFormHasProducts.forEach(buyFormHasProduct -> {
            buyFormHasProduct.setSoldDate(LocalDate.now());
            buyFormService.updateBuyFormHasProduct(buyFormHasProduct);
        });
        if (!buyFormService.checkBuyForm(buyForm))
            return new ResponseEntity<>("check fail", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("check success", HttpStatus.OK);
    }

    @GetMapping(value = "/products-in-buy-form", params = "buy-form-id")
    public ResponseEntity<List<BuyFormDetail>> findAllBuyFormDetailByBuyForm(@RequestParam(value = "buy-form-id") int id) {
        List<BuyFormHasProduct> buyFormHasProducts = buyFormService.findAllBuyFormHasProductByBuyFormId(id);
        List<BuyFormDetail> buyFormDetails = new ArrayList<>();
        buyFormHasProducts.forEach(buyFormHasProduct -> {
            BuyFormDetail buyFormDetail = BuyFormDetail.builder()
                    .product(productService.findById(buyFormHasProduct.getProductId()))
                    .quantity(buyFormHasProduct.getQuantity())
                    .soldDate(buyFormHasProduct.getSoldDate()).build();
            buyFormDetails.add(buyFormDetail);
        });
        if (buyFormDetails.isEmpty()) return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(buyFormDetails, HttpStatus.OK);
    }

    @PutMapping(value = "/buy-form/delete")
    public ResponseEntity<String> deleteTag(@RequestParam("buy-form-id") int buyFormId) {
        Record record = recordService.findByName("buy-form");

        if (buyFormService.deleteBuyFormHasProduct(buyFormId)) {
            record.setNumber(record.getNumber() - 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete buy form success", HttpStatus.OK);
        } else
            return new ResponseEntity<>("delete buy form fails", HttpStatus.BAD_REQUEST);
    }


}
