package com.bksoftwarevn.service_impl.product;

import com.bksoftwarevn.entities.product.BuyForm;
import com.bksoftwarevn.entities.product.BuyFormCart;
import com.bksoftwarevn.entities.product.BuyFormHasProduct;
import com.bksoftwarevn.entities.product.Product;
import com.bksoftwarevn.entities.user.AppUser;
import com.bksoftwarevn.repository.product.BuyFormHasProductRepository;
import com.bksoftwarevn.repository.product.BuyFormRepository;
import com.bksoftwarevn.repository.user.AppUserRepository;
import com.bksoftwarevn.service.product.BuyFormService;
import com.bksoftwarevn.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BuyFormService_Impl implements BuyFormService {

    private final static Logger LOGGER = Logger.getLogger(BuyFormService_Impl.class.getName());

    @Autowired
    private BuyFormRepository buyFormRepository;

    @Autowired
    private BuyFormHasProductRepository buyFormHasProductRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ProductService productService;


    @Override
    public List<BuyForm> findAllBuyFormPage(Pageable pageable) {
        try {
            return buyFormRepository.findAllBuyFormPage(pageable).getContent();

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-buy-form-page-error {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<BuyForm> findAllBuyForm() {
        try {
            return buyFormRepository.findByStatus(true);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-buy-form-error {0}", ex.getMessage());
        }
        return null;
    }

    @Override

    public List<BuyFormHasProduct> findAllBuyFormHasProductPage(Pageable pageable) {
        try {
            return buyFormHasProductRepository.findByStatusPage(pageable).getContent();

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-buy-form-has-product-page-error {0}", ex.getMessage());
        }
        return null;
    }


    @Override
    public List<BuyFormHasProduct> findAllBuyFormHasProduct() {
        try {
            return buyFormHasProductRepository.findByStatus(true);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-buy-form-has-product-error {0}", ex.getMessage());
        }

        return null;
    }


    @Override
    public List<BuyForm> findAllUnCheckBuyFormPage(Pageable pageable) {
        try {
            return buyFormRepository.findAllByCheckedPage(false, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-uncheck-buyForm-error {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public int sizeOfUnCheckBuyForm() {
        try {
            return buyFormRepository.findAllByCheckedAndStatus(false, true).size();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "size-of-uncheck-buy-form-error {0}", ex.getMessage());
        }
        return 0;
    }

    @Override
    public List<BuyFormCart> findAllBuyFormByUserPage(int userId, Pageable pageable) {
        try {
            List<BuyFormCart> buyFormCarts = new ArrayList<>();
            List<BuyForm> buyForms = buyFormRepository.findByAppUserPage(userId, pageable).getContent();
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
                        findAllBuyFormHasProductByBuyFormId(buyForm.getId());

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

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-buyForm-by-user-page-error {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public int sizeOfBuyFormByUser(int userId) {
        try {
            AppUser user = appUserRepository.findById(userId);
            return buyFormRepository.findByAppUser(user).size();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "size-of-buyForm-buy-user-error {0}", ex.getMessage());
        }
        return 0;
    }

    @Override
    public boolean checkBuyForm(BuyForm buyForm) {
        try {
            buyForm.setChecked(true);
            buyFormRepository.save(buyForm);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "check-buyForm-error {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public BuyForm findById(int id) {
        try {
            return buyFormRepository.findById(id);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-buyForm-error {0}", ex.getMessage());

        }
        return null;
    }

    @Override
    public BuyFormHasProduct findBuyFormHasProductById(int id) {
        try {
            BuyFormHasProduct buyFormHasProduct = buyFormHasProductRepository.findById(id);
            if (buyFormHasProduct.getStatus()) return buyFormHasProduct;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-buyForm-has-product-by-id-error {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveBuyForm(BuyForm buyForm) {
        try {
            buyFormRepository.save(buyForm);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-buy-form-error: {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public List<BuyFormHasProduct> findAllBuyFormHasProductByBuyFormId(int id) {
        try {
            return buyFormHasProductRepository.findByBuyFormId(id);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-buy-form-has-product-buy-bf-id-error: {0}", ex.getMessage());
            return null;
        }
    }

    @Override
    public BuyFormHasProduct findByBuyFormAndProduct(BuyForm buyForm, Product product) {
        try {
            return buyFormHasProductRepository.findByBuyFormIdAndProductIdCart(buyForm.getId(), product.getId());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-buy-form-has-product-error: {0}", ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean updateBuyFormHasProduct(BuyFormHasProduct buyFormHasProduct) {
        try {
            buyFormHasProductRepository.save(buyFormHasProduct);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "update-buy-form-has-product-error: {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteBuyFormHasProduct(int id) {
        try {
            List<BuyFormHasProduct> buyFormHasProducts = buyFormHasProductRepository.findByBuyFormId(id);
            buyFormHasProducts.forEach(bf -> {
                bf.setStatus(false);
                buyFormHasProductRepository.save(bf);
            });
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-buy-form-has-product-error: {0}", ex.getMessage());
            return false;
        }
    }
}
