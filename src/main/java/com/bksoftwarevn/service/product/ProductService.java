package com.bksoftwarevn.service.product;

import com.bksoftwarevn.entities.category.BigCategory;
import com.bksoftwarevn.entities.product.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

public interface ProductService {

    List<Product> findAllProductPage(Pageable pageable);

    List<Product> findAllProduct();

    List<Product> findProductByMenuPage(int idMenu, Pageable pageable);

    int sizeOfProductByMenu(int idMenu);

    List<Product> findProductByBigCategoryPage(int idBigCategory, Pageable pageable);

    int sizeOfProductByBigCategory(int idBigCategory);

    List<Product> findProductBySmallCategoryPage(int idSmallCategory, Pageable pageable);

    int sizeOfProductBySmallCategory(int idSmallCategory);

    List<Product> findAllProductByPartnerPage(int idPartner, Pageable pageable);

    int sizeOfProductByPartner(int idPartner);

    List<Product> findHotProducts(Pageable pageable);

    List<Product> findSpecialProducts(Pageable pageable);

    Set<String> findAllSmallCategoryByHotProducts(int max, Pageable pageable);

    List<Product> findProductSale(Pageable pageable);

    List<Product> findProductByNamePage(String name, Pageable pageable);

    int sizeOfProductByName(String name);

    List<Product> findProductByTypePage(int productType, Pageable pageable);

    int sizeOfProductByType(int productType);

    List<Product> findNewProducts(Pageable pageable);

    List<Product> findAllProductByTag(int idTag);

    List<Product> findAllHotProductByMonthPage(Pageable pageable);

    Product findById(int id);

    boolean saveProduct(Product product);

    boolean deleteProduct(Product product);

    Sort sortData(String sort, String field);

    //List<Product> findProductByMenuPartner(int idMenu, int partner,Pageable pageable);

    //int sizeOfProductByMenuPartner(int idMenu, int partner);

    //List<Product> findProductByBigPartner(int idBigCategory, int partner,Pageable pageable);

    //int sizeOfProductByByBigPartner(int idBigCategory, int partner);

    //List<Product> showProductBySmallPartner(int idSmallCategory, int partner,Pageable pageable);

    //int sizeOfProductByBySmallPartner(int idSmallCategory, int partner);

    //List<Product> findProductByPrice( int low, int high, Pageable pageable);

    //int sizeOfProductByPrice(int low, int high);

    //List<Product> findProductByPricePartner( int low, int high, int partner, Pageable pageable);

    //int sizeOfProductByPricePartner(int low, int high, int partner);


}
