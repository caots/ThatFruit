package com.bksoftwarevn.repository.news;

import com.bksoftwarevn.entities.news.Tag;
import com.bksoftwarevn.entities.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Page<Tag> findByStatus(boolean status, Pageable pageable);

    @Query(value = " SELECT t From Tag t where  t.status = true")
    List<Tag> findAllTagSize();

    Tag findByName(String name);

    Tag findById(int id);

}
