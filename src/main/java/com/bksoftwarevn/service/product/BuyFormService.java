package com.bksoftwarevn.service.product;

import com.bksoftwarevn.entities.news.News;
import com.bksoftwarevn.entities.product.BuyForm;
import com.bksoftwarevn.entities.product.BuyFormCart;
import com.bksoftwarevn.entities.product.BuyFormHasProduct;
import com.bksoftwarevn.entities.product.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuyFormService {

    List<BuyForm> findAllBuyFormPage(Pageable pageable);

    List<BuyForm> findAllBuyForm();

    List<BuyFormHasProduct> findAllBuyFormHasProductPage(Pageable pageable);

    List<BuyFormHasProduct> findAllBuyFormHasProduct();

    List<BuyForm> findAllUnCheckBuyFormPage(Pageable pageable);

    int sizeOfUnCheckBuyForm();

    List<BuyFormCart> findAllBuyFormByUserPage(int userId, Pageable pageable);

    int sizeOfBuyFormByUser(int userId);

    boolean checkBuyForm(BuyForm buyForm);

    BuyForm findById(int id);

    BuyFormHasProduct findBuyFormHasProductById(int id);

    boolean saveBuyForm(BuyForm buyForm);

    List<BuyFormHasProduct> findAllBuyFormHasProductByBuyFormId(int id);

    BuyFormHasProduct findByBuyFormAndProduct(BuyForm buyForm, Product product);

    boolean updateBuyFormHasProduct(BuyFormHasProduct buyFormHasProduct);

    boolean deleteBuyFormHasProduct(int id);
}
