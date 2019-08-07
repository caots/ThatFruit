package com.bksoftwarevn.controller.viewer.product;


import com.bksoftwarevn.commom.Token;
import com.bksoftwarevn.entities.Cart;
import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.product.BuyForm;
import com.bksoftwarevn.entities.product.BuyFormCart;
import com.bksoftwarevn.entities.product.BuyFormHasProduct;
import com.bksoftwarevn.entities.product.Product;
import com.bksoftwarevn.entities.user.Buyer;
import com.bksoftwarevn.entities.user.UserMail;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.SendMailService;
import com.bksoftwarevn.service.product.BuyFormService;
import com.bksoftwarevn.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "api/v1/public/buy-form")
public class BuyFormController {

    @Autowired
    private BuyFormService buyFormService;

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private ProductService productService;

    @Autowired
    private RecordService recordService;


    private Set<BuyFormCart> buyFormCarts = new HashSet<>();

    private Set<BuyFormCart> findAllBuyFormCart(List<BuyFormHasProduct> buyFormHasProducts) {

        buyFormHasProducts.forEach(buyFormHasProduct -> {
            BuyFormCart buyFormCart = new BuyFormCart();
            BuyForm buyForm = buyFormService.findById(buyFormHasProduct.getBuyFormId());
            Buyer buyer = new Buyer();
            if (buyForm.getAppUser() != null) {
                buyer.setId(buyForm.getAppUser().getId());
                buyer.setName(buyForm.getAppUser().getFullName());
                buyer.setAddress(buyForm.getAppUser().getAddress());
                buyer.setEmail(buyForm.getAppUser().getEmail());
                buyer.setPhone(buyForm.getAppUser().getPhone());
                buyer.setStatus(buyForm.getAppUser().isStatus());
            }
            buyFormCart.setId(buyForm.getId());
            buyFormCart.setStatus(buyForm.isStatus());
            buyFormCart.setName(buyer.getName());
            buyFormCart.setEmail(buyer.getEmail());
            buyFormCart.setPhoneNumber(buyer.getPhone());
            buyFormCart.setDate(buyForm.getDate());
            buyFormCart.setNote(buyForm.getNote());
            buyFormCart.setAddress(buyer.getAddress());
            Set<String> nameProduct = new HashSet<>();
            buyForm.getProducts().forEach(product -> {
                nameProduct.add(product.getName());
            });
            buyFormCart.setProducts(nameProduct);
            long total = buyForm.getProducts()
                    .stream()
                    .mapToLong(bf -> (long) bf.getSaleCost()).sum();
            buyFormCart.setPrice(total);
            buyFormCart.setChecked(false);
            List<Integer> quantities = new ArrayList<>();

            for (BuyFormHasProduct bf : buyFormHasProducts) {
                quantities.add(bf.getQuantity());
            }
            buyFormCart.setQuantity(quantities);

            buyFormCarts.add(buyFormCart);
        });
        return buyFormCarts;
    }

    @GetMapping
    public ResponseEntity<Set<BuyFormCart>> findAllBuyFormHasProduct(
            @RequestHeader("adminbksoftwarevn") String header,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ) {
        if (header.equals(Token.tokenHeader)) {
            if (page < 1) page = 1;
            if (size < 0) size = 0;
            Pageable pageable = PageRequest.of(page - 1, size);
            List<BuyFormHasProduct> buyFormHasProducts = buyFormService.findAllBuyFormHasProductPage(pageable);
            return new ResponseEntity<>(findAllBuyFormCart(buyFormHasProducts), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("size")
    public ResponseEntity<Double> findAllBuyFormHasProductSize(
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {

            List<BuyFormHasProduct> buyFormHasProducts = buyFormService.findAllBuyFormHasProduct();
            double sizePage = findAllBuyFormCart(buyFormHasProducts).size();
            double result = Math.ceil(sizePage / 10);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("find-buy-form")
    public ResponseEntity<BuyForm> findBuyFormBuy(
            @RequestHeader("adminbksoftwarevn") String header,
            @RequestParam("id") int id
    ) {
        if (header.equals(Token.tokenHeader)) {
            BuyFormHasProduct buyFormHasProduct = buyFormService.findBuyFormHasProductById(id);
            return new ResponseEntity<>(buyFormService.findById(buyFormHasProduct.getBuyFormId()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("find-by-id")
    public ResponseEntity<BuyFormCart> findBuyFormBuyHasProductById(
            @RequestHeader("adminbksoftwarevn") String header,
            @RequestParam("id") int id
    ) {
        if (header.equals(Token.tokenHeader)) {
            for (BuyFormCart buyFormCart : buyFormCarts) {
                if (buyFormCart.getId() == id)
                    return new ResponseEntity<>(buyFormCart, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/delete")
    public ResponseEntity<Object> deleteBuyFormHasProduct(
            @RequestParam("id-product") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            boolean result = buyFormService.deleteBuyFormHasProduct(id);

            if (result) return new ResponseEntity<>("delete success", HttpStatus.OK);
        }
        return new ResponseEntity<>("delete fail", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-form")
    public ResponseEntity<Object> addBuyForm(
            @RequestBody BuyForm buyForm,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            buyForm.setStatus(true);
            buyForm.setChecked(false);
            buyForm.setDate(LocalDate.now());
            buyForm.setProducts(null);
            Record record = recordService.findByName("buy-form");
            if (buyFormService.saveBuyForm(buyForm))
                record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(buyForm, HttpStatus.OK);
        }
        return new ResponseEntity<>("saved fail", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/add-products")
    public ResponseEntity<String> addProductsToBuyForm(
            @RequestBody List<Cart> carts,
            @RequestParam(value = "buy-form-id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            BuyForm buyForm = buyFormService.findById(id);
            if (buyForm == null)
                return new ResponseEntity<>("buy form id is wrong", HttpStatus.BAD_REQUEST);
            List<Product> products = new ArrayList<>();
            carts.forEach(cart -> {
                Product product = productService.findById(cart.getProduct().getId());
                products.add(product);
            });
            buyForm.setProducts(products);
            buyForm.setStatus(true);
            buyFormService.saveBuyForm(buyForm);
            carts.forEach(cart -> {
                BuyFormHasProduct buyFormHasProduct = buyFormService.findByBuyFormAndProduct(buyForm, cart.getProduct());
                buyFormHasProduct.setQuantity(cart.getQuantity());
                buyFormHasProduct.setStatus(true);
                buyFormHasProduct.setSoldDate(LocalDate.now());
                buyFormService.updateBuyFormHasProduct(buyFormHasProduct);
            });
//            ===================== send-email==========================================

            String email = "honghoang1232@gmail.com";
            String title = "Đơn mua hàng mới";
            String content = "Bạn có đơn đặt hàng mới: MÃ " + buyForm.getId();
            UserMail userMail = new UserMail();
            userMail.setEmailAddress(email);
            boolean result = sendMailService.sendEMail(userMail.getEmailAddress());
            if (result)
                return new ResponseEntity<>("Congratulations! Your mail has been send to the user.", HttpStatus.OK);
            //==========================================================================
            return new ResponseEntity<>("add products success", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
