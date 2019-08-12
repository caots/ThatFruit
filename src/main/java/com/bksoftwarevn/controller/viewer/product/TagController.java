package com.bksoftwarevn.controller.viewer.product;

import com.bksoftwarevn.entities.news.Tag;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.product.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private RecordService recordService;

    @GetMapping("/all")
    public ResponseEntity<List<Tag>> findAllTag(
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<Tag> tags = tagService.findAllTag();
        if (tags != null) {
            return new ResponseEntity<>(tags, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/page")
    public ResponseEntity<List<Tag>> findAllTagPage(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (page < 1) page = 1;
        if (size < 0) size = 10;
        Pageable pageable = PageRequest.of(page - 1, size);
        List<Tag> tags = tagService.findAllTagPage(pageable);
        if (tags != null) return new ResponseEntity<>(tags, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<Tag> findNewsById(@RequestParam("id") int id,
                                            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Tag tag = tagService.findById(id);
        if (tag != null) {
            return new ResponseEntity<>(tag, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/size")
    public ResponseEntity<Double> findAllNewsPageSize(
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        double pageNumber = recordService.findByName("tag").getNumber();
        double result = Math.ceil(pageNumber / 10);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/find-by-product")
    public ResponseEntity<List<Tag>> findByNewsId(@RequestParam("product-id") int productId,
                                                  HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<Tag> tags = tagService.findByProduct(productId);
        if (tags != null) return new ResponseEntity<>(tags, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
