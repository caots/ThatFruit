package com.bksoftwarevn.repository.company;

import com.bksoftwarevn.entities.company.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer> {

    Page<Partner> findByStatus(boolean status, Pageable pageable);

    @Query("select p from Partner p where p.status=true")
    List<Partner> findAllPartner();

    Partner findById(int id);
}
