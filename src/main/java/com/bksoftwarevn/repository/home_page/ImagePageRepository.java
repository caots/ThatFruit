package com.bksoftwarevn.repository.home_page;

import com.bksoftwarevn.entities.home_page.ImagePage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagePageRepository extends JpaRepository<ImagePage, Integer> {

    List<ImagePage> findByStatus(boolean status);

    ImagePage findById(int id);

}
