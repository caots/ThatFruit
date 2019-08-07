package com.bksoftwarevn.service_impl.product;

import com.bksoftwarevn.entities.product.ProductType;
import com.bksoftwarevn.repository.product.ProductTypeRepository;
import com.bksoftwarevn.service.product.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductTypeService_Impl implements ProductTypeService {

    private final static Logger LOGGER = Logger.getLogger(ProductTypeService_Impl.class.getName());

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public List<ProductType> findAllProductType() {
        try {
            return productTypeRepository.findByStatus(true);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-product-type-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public ProductType findById(int id) {
        try {
            ProductType productType = productTypeRepository.findById(id);
            if (productType.isStatus()) return productType;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveProductType(ProductType productType) {
        try {
            productTypeRepository.save(productType);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-product-type-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteProductType(ProductType productType) {
        try {
            if (productType.isStatus()) {
                productType.setStatus(false);
                productTypeRepository.save(productType);
                return true;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-product-type-error : {0}", ex.getMessage());
        }
        return false;
    }
}
