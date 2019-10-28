package com.bksoftwarevn.service_impl.product;

import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.category.BigCategory;
import com.bksoftwarevn.entities.category.SmallCategory;
import com.bksoftwarevn.entities.news.Tag;
import com.bksoftwarevn.entities.product.BuyFormHasProduct;
import com.bksoftwarevn.entities.product.Product;
import com.bksoftwarevn.repository.RecordRepository;
import com.bksoftwarevn.repository.category.SmallCategoryRepository;
import com.bksoftwarevn.repository.news.TagRepository;
import com.bksoftwarevn.repository.product.BuyFormHasProductRepository;
import com.bksoftwarevn.repository.product.ProductRepository;
import com.bksoftwarevn.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ProductService_Impl implements ProductService {

    private final static Logger LOGGER = Logger.getLogger(ProductService_Impl.class.getName());

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SmallCategoryRepository smallCategoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public List<Product> findAllHotProductBySmallCategory(int id, Pageable pageable) {
        try {
            return productRepository.findAllHotProductBySmallCategory(id, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-hot-product-by-small-category-error: {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> findAllProductPage(Pageable pageable) {
        try {
            return productRepository.findByStatus(true, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-product-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> findAllProduct() {
        try {
            return productRepository.findAllProduct();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-product-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> findProductByMenuPage(int idMenu, Pageable pageable) {

        try {
            return productRepository.findProductByMenuPage(idMenu, pageable).getContent();

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-product-by-menu-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public int sizeOfProductByMenu(int idMenu) {
        try {
            return productRepository.findProductByMenu(idMenu).size();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "size-of-product-by-menu-error : {0}", ex.getMessage());
        }
        return 0;
    }

    @Override
    public List<Product> findProductByBigCategoryPage(int idBigCategory, Pageable pageable) {

        try {
            return productRepository.findProductByBigCategoryPage(idBigCategory, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-product-by-big-category-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public int sizeOfProductByBigCategory(int idBigCategory) {
        try {
            return productRepository.findProductByBigCategory(idBigCategory).size();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "size-of-product-by-big-category-error : {0}", ex.getMessage());
        }
        return 0;
    }

    @Override
    public List<Product> findProductBySmallCategoryPage(int idSmallCategory, Pageable pageable) {
        try {
            return productRepository.findAllProductBySmallCategoryPage(idSmallCategory, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-productBySmall-category-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public int sizeOfProductBySmallCategory(int idSmallCategory) {
        try {
            SmallCategory smallCategory = smallCategoryRepository.findById(idSmallCategory);
            if (smallCategory.isStatus())
                return productRepository.findBySmallCategory(smallCategory)
                        .stream()
                        .filter(Product::isStatus)
                        .collect(Collectors.toList())
                        .size();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "size-of-product-by-small-category-error : {0}", ex.getMessage());
        }
        return 0;
    }

    @Override
    public List<Product> findAllProductByPartnerPage(int idPartner, Pageable pageable) {
        try {
            return productRepository.findProductByPartnerPage(idPartner, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-product-by-partner-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public int sizeOfProductByPartner(int idPartner) {
        try {
            return productRepository.findProductByPartner(idPartner).size();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "size-of-product-by-partner-error : {0}", ex.getMessage());
        }
        return 0;
    }

    @Override
    public List<Product> findHotProducts(Pageable pageable) {
        try {
            return productRepository.findProductByHot(pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-hot-products-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> findSpecialProducts(Pageable pageable) {
        try {
            return productRepository.findProductBySpecial(pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-special-products-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Set<String> findAllSmallCategoryByHotProducts(int max, Pageable pageable) {

        try {
            Set<String> nameSmalls = new HashSet<>();
            List<Product> productsHot = productRepository.findProductByHot(pageable).getContent();
            for (Product product : productsHot) {
                nameSmalls.add(product.getSmallCategory().getName());
                if (nameSmalls.size() >= max) break;
            }
            return nameSmalls;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-small-category-by-hot-product-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> findProductSale(Pageable pageable) {
        try {
            return productRepository.findSaleProducts(pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-product-sale-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> findProductByNamePage(String name, Pageable pageable) {
        try {
            return productRepository.findAllProductByNamePage(name, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-product-by-name-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> findProductByNameAll(String name) {
        try {
            return productRepository.findAllProductByName(name);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-product-by-name-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public int sizeOfProductByName(String name) {
        try {
            return productRepository.findByProductNameSize(name).size();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "size-of-product-by-name-error : {0}", ex.getMessage());
        }
        return 0;
    }


    @Override
    public List<Product> findNewProducts(Pageable pageable) {

        try {
            return productRepository.findNewProductPage(pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-new-products-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> findAllProductByTag(int idTag) {
        try {
            Tag tag = tagRepository.findById(idTag);
            return tag.getProducts()
                    .stream()
                    .filter(Product::isStatus)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-product-by-tag-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> findAllHotProductByMonthPage(Pageable pageable) {
        try {
            return productRepository.listProductByMonth(pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-hot-product-by-month-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Product findById(int id) {
        try {
            Product product = productRepository.findById(id);
            if (product.isStatus()) return product;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-product-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveProduct(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-product-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteProduct(Product product) {
        try {
            if (product.isStatus()) {
                product.setStatus(false);
                productRepository.save(product);
                return true;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-product-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public Sort sortData(String sort, String field) {
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by(field).ascending();
        }
        if (sort.equals("DESC")) {
            sortable = Sort.by(field).descending();
        }
        return sortable;

    }

    @Override
    public Set<Integer> listTagAdd(String content) {
        Set<Integer> tagIds = new HashSet<>();
        System.out.println(content);
        Record record = recordRepository.findByName("tag");
        record.setNumber(record.getNumber() + 1);

        String[] nameTag = content.split("@");

        for (int i = 1; i < nameTag.length; i++) {
            nameTag[i].replaceAll("\\s++", "");
            nameTag[i].trim();
            System.out.println(nameTag[i]);
        }
        //done

        List<Tag> tags = tagRepository.findAllTagSize();

        for (int i = 1; i < nameTag.length; i++) {
            int index = 1;

            for (Tag tag : tags) {
                System.out.println(tag.getName().trim() + " - " + nameTag[i]);
                if (tag.getName().equals(nameTag[i])) {
                    index = -1;
                    break;
                }
            }

            System.out.println(index);
            if (index == 1) {
                Tag t = new Tag();
                t.setName(nameTag[i]);
                t.setStatus(true);
                tagRepository.save(t);
                Tag tag = findByNameUnique(t.getName());
                tagIds.add(tag.getId());
            } else {
                Tag tag = findByNameUnique(nameTag[i]);
                System.out.println(tag.getId());
                tagIds.add(tag.getId());
            }
        }
        return tagIds;
    }

    private Tag findByNameUnique(String name) {
        try {
            return tagRepository.findByName(name);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-tag-by-name-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean setEndDateSale() {
        try {
            List<Product> products = productRepository.findAllProduct();
            products.forEach(product -> {
                if (LocalDate.now().toString().equals(product.getEnDateSale().toString())) {
                    product.setSaleCostRetail(product.getOriginCostRetail());
                    product.setSaleCostWholesale(product.getOriginCostWholesale());
                    productRepository.save(product);
                }
            });
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "set-end-date-sale-error : {0}", ex.getMessage());
        }
        return false;
    }


}
