package com.bksoftwarevn.controller.admin.news;

import com.bksoftwarevn.entities.Record;
import com.bksoftwarevn.entities.news.News;
import com.bksoftwarevn.entities.news.Topic;
import com.bksoftwarevn.service.RecordService;
import com.bksoftwarevn.service.news.NewsService;
import com.bksoftwarevn.service.news.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/admin")
@RolesAllowed("ROLE_ADMIN")

public class AdminNewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private RecordService recordService;

    //=============================== Topic =================================

    @PostMapping("/topic")
    public ResponseEntity<Object> createTopic(@RequestBody Topic topic) {
        Record record = recordService.findByName("topic");
        topic.setStatus(true);
        if (topicService.saveTopic(topic)) {
            record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(topic, HttpStatus.OK);
        } else
            return new ResponseEntity<>("createtopic fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/topic")
    public ResponseEntity<Object> updateTopic(@RequestBody Topic topic) {
        if (topicService.saveTopic(topic))
            return new ResponseEntity<>(topic, HttpStatus.OK);
        else
            return new ResponseEntity<>("update topic fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/topic/delete")
    public ResponseEntity<Object> deleteTopic(@RequestBody Topic topic) {
        Record record = recordService.findByName("topic");
        if (topicService.deleteTopic(topic)) {
            record.setNumber(record.getNumber() - 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete topic success", HttpStatus.OK);
        } else
            return new ResponseEntity<>("delete topic fail", HttpStatus.BAD_REQUEST);
    }

    //============================ News =========================================
    @PostMapping("/news")
    public ResponseEntity<Object> createNews(
            @RequestBody News news,
            @RequestParam(name = "topic-id") int topicId
    ) {
        Record record = recordService.findByName("news");
        news.setStatus(true);
        news.setTopic(topicService.findById(topicId));
        news.setView(0);
        news.setTime(LocalDateTime.now());
        if (newsService.saveNews(news)) {
            record.setNumber(record.getNumber() + 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>(news, HttpStatus.OK);
        } else
            return new ResponseEntity<>("create news fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/news")
    public ResponseEntity<Object> updateNews(@RequestBody News news) {
        if (newsService.saveNews(news))
            return new ResponseEntity<>(news, HttpStatus.OK);
        else
            return new ResponseEntity<>("update news fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/news/delete")
    public ResponseEntity<Object> deleteNews(@RequestBody News news) {
        Record record = recordService.findByName("news");
        if (newsService.deleteNews(news)) {
            record.setNumber(record.getNumber() - 1);
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete news success", HttpStatus.OK);
        } else
            return new ResponseEntity<>("delete news fail", HttpStatus.BAD_REQUEST);
    }


}
