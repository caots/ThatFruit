package com.bksoftwarevn.service_impl.product;

import com.bksoftwarevn.entities.product.ProductImage;
import com.bksoftwarevn.repository.product.ProductImageRepository;
import com.bksoftwarevn.service.product.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductImageService_Impl implements ProductImageService {

    private final static Logger LOGGER = Logger.getLogger(ProductImageService_Impl.class.getName());

    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public List<ProductImage> findAllProductImage() {
        try {
            return productImageRepository.findByStatus(true);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-product-image-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public ProductImage findById(int id) {
        try {
            ProductImage productImage = productImageRepository.findById(id);
            if (productImage.isStatus()) return productImage;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveProductImage(ProductImage productImage) {
        try {
            productImageRepository.save(productImage);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-product-image-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteProductImage(ProductImage productImage) {
        try {
            if (productImage.isStatus()) {
                productImage.setStatus(false);
                productImageRepository.save(productImage);
                return true;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-product-image-error : {0}", ex.getMessage());
        }
        return false;
    }
}
