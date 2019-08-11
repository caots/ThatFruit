package com.bksoftwarevn.service.product;

import com.bksoftwarevn.entities.product.ProductImage;

import java.util.List;

public interface ProductImageService {

    List<ProductImage> findAllProductImage();

    List<ProductImage> findAllProductImageByProductId(int productId);

    ProductImage findById(int id);

    boolean saveProductImage(ProductImage productImage);

    boolean deleteProductImage(ProductImage productImage);

}
