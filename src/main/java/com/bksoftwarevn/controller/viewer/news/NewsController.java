package com.bksoftwarevn.controller.viewer.news;

import com.bksoftwarevn.commom.Token;
import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.news.News;
import com.bksoftwarevn.entities.news.Topic;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.news.NewsService;
import com.bksoftwarevn.service.news.TopicService;
import com.bksoftwarevn.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ProductService productService;


    @GetMapping("/page")
    public ResponseEntity<List<News>> findAllNews(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "20") int size,
            @RequestParam(name = "type", required = false, defaultValue = "DESC") String type,
            @RequestParam(name = "field", required = false, defaultValue = "id") String field,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;
        if (header.equals(Token.tokenHeader)) {
            Sort sortable = productService.sortData(type, field);

            Pageable pageable = PageRequest.of(page - 1, size, sortable);
            List<News> newsList = newsService.findAllNewsPage(pageable);

            if (newsList != null) {
                return new ResponseEntity<>(newsList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/size")
    public ResponseEntity<Double> pageNumberMenu(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            Record record = recordService.findByName("news");
            double result = Math.ceil((double) record.getNumber() / 10);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find-by-id")
    public ResponseEntity<News> findMenuById(
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {

            return new ResponseEntity<>(newsService.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/views")
    public ResponseEntity<List<News>> findAllNewsByViews(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "20") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;
        if (header.equals(Token.tokenHeader)) {
            Pageable pageable = PageRequest.of(page - 1, size);
            List<News> newsList = newsService.findByView(pageable);
            if (newsList != null) {
                return new ResponseEntity<>(newsList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "title/size")
    public ResponseEntity<Double> pageNumberNewsByName(
            @RequestParam("title") String title,
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            double result = Math.ceil((double) newsService.sizeOfNewsBTitle(title) / 10);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("title/page")
    public ResponseEntity<List<News>> findAllNewsByTitle(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "20") int size,
            @RequestParam("title") String title,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;
        if (header.equals(Token.tokenHeader)) {
            Pageable pageable = PageRequest.of(page - 1, size);
            List<News> newsList = newsService.findAllNewsByTitle(title, pageable);
            if (newsList != null) {
                newsList = newsService.findByTime();
                return new ResponseEntity<>(newsList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/topic/page")
    public ResponseEntity<List<News>> findAllNewsByTopic(
            @RequestParam("topic-id") int topicId,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (page < 1) page = 1;
        if (size < 0) size = 0;
        if (header.equals(Token.tokenHeader)) {
            Pageable pageable = PageRequest.of(page - 1, size);
            Topic topic = topicService.findById(topicId);
            if (topic.isStatus()) {
                List<News> newsList = newsService.findAllNewsByTopicPage(topic, pageable);
                if (newsList != null) {
                    newsList = newsService.findByTime();
                    return new ResponseEntity<>(newsList, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/topic/size")
    public ResponseEntity<Double> pageNumberNews(
            HttpServletResponse response,
            @RequestParam("topic-id") int topicId,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            double result = Math.ceil((double) newsService.sizeOfNewsByTopic(topicId) / 10);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
