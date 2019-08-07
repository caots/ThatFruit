package com.bksoftwarevn.service.product;

import com.bksoftwarevn.entities.news.Tag;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    List<Tag> findAllTagPage(Pageable pageable);

    List<Tag> findAllTag();

    List<Tag> findByProduct(int productId);


    Tag findById(int id);

    boolean saveTag(Tag tag);

    boolean deleteTag(Tag tag);
}
