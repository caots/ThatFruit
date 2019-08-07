package com.bksoftwarevn.service.product;

import com.bksoftwarevn.entities.product.ProductType;

import java.util.List;

public interface ProductTypeService {

    List<ProductType> findAllProductType();

    ProductType findById(int id);

    boolean saveProductType(ProductType productType);

    boolean deleteProductType(ProductType productType);

}
