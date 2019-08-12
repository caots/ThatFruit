package com.bksoftwarevn.repository.product;

import com.bksoftwarevn.entities.category.SmallCategory;
import com.bksoftwarevn.entities.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.status = true and p.name LIKE CONCAT('%',:name_product,'%')")
    Page<Product> findAllProductByNamePage(@Param("name_product") String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.status = true and p.name LIKE CONCAT('%',:name_product,'%')")
    List<Product> findByProductNameSize(@Param("name_product") String name);

    @Query(value = "SELECT p  From Product p where p.status=true order by p.id desc ")
    Page<Product> findNewProductPage(Pageable pageable);

    @Query(value = " SELECT p From Product p where p.status = true order by (p.originCost - p.saleCost) desc ")
    Page<Product> findSaleProducts(Pageable pageable);

    @Query(value = " SELECT p From Product p where p.smallCategory.id =:id and  p.status= true")
    Page<Product> findAllProductBySmallCategoryPage(@Param("id") int id, Pageable pageable);

    List<Product> findBySmallCategory(SmallCategory smallCategory);

    @Query(value = " SELECT p From Product p where p.status= true")
    List<Product> findAllProduct();

    @Query(value = " select * from product where MONTH(init_date) = MONTH(NOW()) and status = true order by (origin_cost - sale_cost) desc ", nativeQuery = true)
    Page<Product> listProductByMonth(Pageable pageable);

    @Query("select p from Product p where p.status=true and p.smallCategory.bigCategory.id= :id")
    Page<Product> findProductByBigCategoryPage(@Param("id") int id, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.smallCategory.bigCategory.id= :id")
    List<Product> findProductByBigCategory(@Param("id") int id);

    @Query("select p from Product p where p.status=true and p.smallCategory.bigCategory.menu.id= :id")
    Page<Product> findProductByMenuPage(@Param("id") int id, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.smallCategory.bigCategory.menu.id= :id")
    List<Product> findProductByMenu(@Param("id") int id);

    @Query("select p from Product p where p.status=true order by p.saleNumber asc ")
    Page<Product> findProductByHot(Pageable pageable);

    @Query("select p from Product p where p.status=true order by p.view desc ")
    Page<Product> findProductBySpecial(Pageable pageable);

    @Query("select p from Product p where p.status=true and p.productType.id= :id")
    Page<Product> findProductByTypePage(@Param("id") int id, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.productType.id= :id")
    List<Product> findProductByType(@Param("id") int id);

    @Query("select p from Product p where p.status=true and p.partner.id= :id")
    Page<Product> findProductByPartnerPage(@Param("id") int id, Pageable pageable);

    @Query("select p from Product p where p.status=true and p.partner.id= :id")
    List<Product> findProductByPartner(@Param("id") int id);

    Page<Product> findByStatus(boolean status, Pageable pageable);

    Product findById(int id);

}
