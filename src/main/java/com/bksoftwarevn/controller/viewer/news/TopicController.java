package com.bksoftwarevn.controller.viewer.news;

import com.bksoftwarevn.commom.Token;
import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.news.Topic;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.news.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/public/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private RecordService recordService;

    @GetMapping("/page")
    public ResponseEntity<List<Topic>> findAllTopicPage(
            HttpServletResponse response,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            if (page < 1) page = 1;
            if (size < 0) size = 0;

            Pageable pageable = PageRequest.of(page - 1, size);
            List<Topic> topics = topicService.findAllTopicPage(pageable);
            return new ResponseEntity<>(topics, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/size")
    public ResponseEntity<Double> pageNumberTopic(
            HttpServletResponse response,
            @RequestHeader("adminbksoftwarevn") String header
    ) {

        if (header.equals(Token.tokenHeader)) {
            Record record = recordService.findByName("topic");
            double result = Math.ceil((double) record.getNumber() / 10);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Topic>> findAllTopic(@RequestHeader("adminbksoftwarevn") String header) {
        if (header.equals(Token.tokenHeader)) {
            Record record = recordService.findByName("menu");

            List<Topic> topics = topicService.findAllTopic();
            record.setNumber(topics.size());
            recordService.saveRecord(record);
            return new ResponseEntity<>(topics, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find-by-id")
    public ResponseEntity<Topic> findTopicById(
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {

            return new ResponseEntity<>(topicService.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
