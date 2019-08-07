package com.bksoftwarevn.service.news;

import com.bksoftwarevn.entities.news.Topic;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface TopicService {

    List<Topic> findAllTopicPage(Pageable pageable);

    List<Topic> findAllTopic();

    Topic findById(int id);

    boolean saveTopic(Topic topic);

    boolean deleteTopic(Topic topic);

}
