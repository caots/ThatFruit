package com.bksoftwarevn.repository.product;

import com.bksoftwarevn.entities.product.BuyFormHasProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyFormHasProductRepository extends JpaRepository<BuyFormHasProduct, Integer> {

    @Query("select bf from BuyFormHasProduct bf where bf.status=true")
    Page<BuyFormHasProduct> findByStatusPage(Pageable pageable, boolean status);

    @Query(value = "select * from buy_form_has_product bf where bf.buy_form_id = ?1 and  bf.product_id= ?2", nativeQuery = true)
    BuyFormHasProduct findByBuyFormIdAndProductIdCart(int buyFormId, int productId);

    List<BuyFormHasProduct> findByBuyFormId(int buyFormId);

    BuyFormHasProduct findById(int id);

    List<BuyFormHasProduct> findByStatus(boolean status);
}
