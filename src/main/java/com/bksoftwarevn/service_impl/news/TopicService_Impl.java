package com.bksoftwarevn.service_impl.news;

import com.bksoftwarevn.entities.news.Topic;
import com.bksoftwarevn.repository.news.TopicRepository;
import com.bksoftwarevn.service.news.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TopicService_Impl implements TopicService {

    private final static Logger LOGGER = Logger.getLogger(TopicService_Impl.class.getName());

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public List<Topic> findAllTopicPage(Pageable pageable) {
        try {
            return topicRepository.findByStatus(true, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-topic-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Topic> findAllTopic() {
        try {
            return topicRepository.findAllTopic();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-topic-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public Topic findById(int id) {
        try {
            Topic topic = topicRepository.findById(id);
            if (topic.isStatus()) return topic;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-topic-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveTopic(Topic topic) {
        try {
            topicRepository.save(topic);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-topic-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteTopic(Topic topic) {
        try {
            if (topic.isStatus()) {
                topic.setStatus(false);
                topicRepository.save(topic);
                return true;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-topic-error : {0}", ex.getMessage());
        }
        return false;
    }
}
