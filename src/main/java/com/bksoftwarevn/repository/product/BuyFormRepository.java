package com.bksoftwarevn.repository.product;

import com.bksoftwarevn.entities.product.BuyForm;
import com.bksoftwarevn.entities.user.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyFormRepository extends JpaRepository<BuyForm, Integer> {

    @Query("select bf from BuyForm bf where bf.status=true and bf.checked=:status")
    Page<BuyForm> findAllByCheckedPage(@Param("status") boolean status, Pageable pageable);

    List<BuyForm> findAllByCheckedAndStatus(boolean checked, boolean status);

    @Query("select bf from BuyForm bf where bf.status=true and bf.appUser.id=:id")
    Page<BuyForm> findByAppUserPage(@Param("id") int id, Pageable pageable);

    List<BuyForm> findByAppUser(AppUser appUser);

    BuyForm findById(int id);

}


