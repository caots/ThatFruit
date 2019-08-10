package com.bksoftwarevn.repository.home_page;

import com.bksoftwarevn.entities.home_page.ImagePage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImagePageRepository extends JpaRepository<ImagePage, Integer> {

    List<ImagePage> findByStatus(boolean status);

    @Query(value = " select ip from ImagePage ip where ip.status = true  and  ip.id between :stId and :endId")
    List<ImagePage> findByIdBetween(@Param("stId") int stId, @Param("endId") int endId);

    ImagePage findById(int id);

}
