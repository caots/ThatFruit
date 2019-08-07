package com.bksoftwarevn.service_impl.product;

import com.bksoftwarevn.entities.news.Tag;
import com.bksoftwarevn.entities.product.Product;
import com.bksoftwarevn.repository.news.TagRepository;
import com.bksoftwarevn.service.product.ProductService;
import com.bksoftwarevn.service.product.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TagService_Impl implements TagService {

    private final static Logger LOGGER = Logger.getLogger(ProductService_Impl.class.getName());

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ProductService productService;

    @Override
    public List<Tag> findAllTagPage(Pageable pageable) {
        try {
            return tagRepository.findByStatus(true, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-tag-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Tag> findAllTag() {
        try {
            return tagRepository.findAllTagSize();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-tag-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Tag> findByProduct(int productId) {
        try {
            Product product = productService.findById(productId);
            return product.getTags();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-tag-by-product-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Tag findById(int id) {
        try {
            Tag tag = tagRepository.findById(id);
            if (tag.isStatus()) return tag;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-tag-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveTag(Tag tag) {
        try {
            tagRepository.save(tag);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-tag-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteTag(Tag tag) {
        try {
            if (tag.isStatus()) {
                tag.setStatus(false);
                tagRepository.save(tag);
                return true;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-tag-error : {0}", ex.getMessage());
        }
        return false;
    }
}
