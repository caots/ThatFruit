package com.bksoftwarevn.repository.product;

import com.bksoftwarevn.entities.product.Product;
import com.bksoftwarevn.entities.product.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

    List<ProductImage> findByStatus(boolean status);

    ProductImage findById(int id);

    List<ProductImage> findByProduct(Product product);
}
