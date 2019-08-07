package com.bksoftwarevn.repository.product;

import com.bksoftwarevn.entities.product.ProductImage;
import com.bksoftwarevn.entities.product.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {

    ProductType findById(int id);

    List<ProductType> findByStatus(boolean status);

}
