package com.bksoftwarevn.controller.viewer.product;


import com.bksoftwarevn.commom.MD5;
import com.bksoftwarevn.commom.Token;
import com.bksoftwarevn.entities.Cart;
import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.product.BuyForm;
import com.bksoftwarevn.entities.product.BuyFormCart;
import com.bksoftwarevn.entities.product.BuyFormHasProduct;
import com.bksoftwarevn.entities.product.Product;
import com.bksoftwarevn.entities.user.AppUser;
import com.bksoftwarevn.entities.user.UserMail;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.SendMailService;
import com.bksoftwarevn.service.product.BuyFormService;
import com.bksoftwarevn.service.product.ProductService;
import com.bksoftwarevn.service.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

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

    @Autowired
    private AppUserService appUserService;


    private List<BuyFormCart> findAllBuyFormCart(List<BuyForm> buyForms) {
        List<BuyFormCart> buyFormCarts = new ArrayList<>();
        buyForms.forEach(buyForm -> {
            BuyFormCart buyFormCart = new BuyFormCart();
            buyFormCart.setId(buyForm.getId());
            buyFormCart.setStatus(buyForm.isStatus());
            buyFormCart.setName(buyForm.getName());
            buyFormCart.setEmail(buyForm.getEmail());
            buyFormCart.setPhoneNumber(buyForm.getPhone());
            buyFormCart.setDate(buyForm.getDate());
            buyFormCart.setNote(buyForm.getNote());
            buyFormCart.setAddress(buyForm.getAddress());
            List<Product> products = new ArrayList<>();
            List<Integer> quantities = new ArrayList<>();
            List<String> nameProduct = new ArrayList<>();

            List<BuyFormHasProduct> buyFormHasProducts =
                    buyFormService.findAllBuyFormHasProductByBuyFormId(buyForm.getId());

            buyFormHasProducts.forEach(buyFormHasProduct -> {
                nameProduct.add(productService.findById(buyFormHasProduct.getProductId()).getName());
                products.add(productService.findById(buyFormHasProduct.getProductId()));
                quantities.add(buyFormHasProduct.getQuantity());

            });

            buyFormCart.setProducts(products);

            buyFormCart.setChecked(buyForm.isChecked());

            buyFormCart.setQuantity(quantities);

            buyFormCart.setNameProduct(nameProduct);

            buyFormCart.setPrice(buyForm.getTotalPrice());

            buyFormCarts.add(buyFormCart);
        });

        return buyFormCarts;
    }


    @GetMapping
    public ResponseEntity<List<BuyFormCart>> findAllBuyFormHasProduct(
            @RequestHeader("adminbksoftwarevn") String header,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "15") int size
    ) {
        if (header.equals(Token.tokenHeader)) {
            if (page < 1) page = 1;
            if (size < 0) size = 0;
            Pageable pageable = PageRequest.of(page - 1, size);
            List<BuyForm> buyForms = buyFormService.findAllBuyFormPage(pageable);
            return new ResponseEntity<>(findAllBuyFormCart(buyForms), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("size")
    public ResponseEntity<Double> findAllBuyFormHasProductSize(
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {

            List<BuyForm> buyFormHasProducts = buyFormService.findAllBuyForm();
            double sizePage = findAllBuyFormCart(buyFormHasProducts).size();
            double result = Math.ceil(sizePage / 15);
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
    public ResponseEntity<BuyForm> findBuyFormBuyHasProductById(
            @RequestHeader("adminbksoftwarevn") String header,
            @RequestParam("id") int id
    ) {
        if (header.equals(Token.tokenHeader)) {
            return new ResponseEntity<>(buyFormService.findById(id), HttpStatus.OK);
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

    @PostMapping("/add-buy-form")
    public ResponseEntity<BuyForm> addBuyForm(
            @RequestBody BuyForm buyForm,
            @RequestParam(defaultValue = "-1", value = "user-id", required = false) int userId,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            if (userId > 0) {
                buyForm.setAppUser(appUserService.findById(userId));
            }
            buyForm.setStatus(true);
            buyForm.setChecked(false);
            buyForm.setDate(LocalDate.now());
            buyForm.setProducts(null);
            buyForm.setCodeBuyForm(generateCode());
            Record record = recordService.findByName("buy-form");
            if (buyFormService.saveBuyForm(buyForm))
                record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(buyForm, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private String generateCode() {
        Random random = new Random();
        int numberOne = random.nextInt();
        int numberTwo = random.nextInt();
        int numberThree = random.nextInt();
        int numberFour = random.nextInt();
        return MD5.encode(numberOne + "TH" + numberTwo + "A" + numberThree + "T" + numberFour);
    }

    @PostMapping(value = "/add-products")
    public ResponseEntity<String> addProductsToBuyForm(
            @RequestBody List<Cart> carts,
            @RequestParam(value = "buy-form-id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            System.out.println(carts);
            BuyForm buyForm = buyFormService.findById(id);
            if (buyForm == null)
                return new ResponseEntity<>("buy form id is wrong", HttpStatus.BAD_REQUEST);
            List<Product> products = new ArrayList<>();
            carts.forEach(cart -> {
                Product product = productService.findById(cart.getProductId());
                product.setSaleNumber(product.getSaleNumber() + 1);
                products.add(product);
            });
            if (buyForm.getAppUser() != null) {
                AppUser appUser = buyForm.getAppUser();
                appUser.setAccumulatedPoints(appUser.getAccumulatedPoints() + 1);
                appUserService.saveAppUser(appUser);
            }
            buyForm.setProducts(products);
            buyForm.setStatus(true);
            buyFormService.saveBuyForm(buyForm);
            carts.forEach(cart -> {
                Product product = productService.findById(cart.getProductId());
                BuyFormHasProduct buyFormHasProduct = buyFormService.findByBuyFormAndProduct(buyForm, product);
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
            userMail.setTitle(title);
            userMail.setContent(content);
            boolean result = sendMailService.sendEmail(userMail);
            if (result)
                return new ResponseEntity<>("Congratulations! Your mail has been send to the user.", HttpStatus.OK);
            //==========================================================================
            return new ResponseEntity<>("add products success", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
