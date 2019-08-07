package com.bksoftwarevn.service_impl.news;

import com.bksoftwarevn.entities.news.News;
import com.bksoftwarevn.entities.news.Tag;
import com.bksoftwarevn.entities.news.Topic;
import com.bksoftwarevn.repository.news.NewsRepository;
import com.bksoftwarevn.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class NewsService_Impl implements NewsService {

    private final static Logger LOGGER = Logger.getLogger(NewsService_Impl.class.getName());

    @Autowired
    private NewsRepository newsRepository;


    @Override
    public List<News> findAllNewsPage(Pageable pageable) {
        try {
            return newsRepository.findByStatus(true, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-news-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<News> findAllNewsByTopicPage(Topic topic, Pageable pageable) {
        try {
            return newsRepository.findByTopic(topic, pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-news-by-topic-page-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public int sizeOfNewsByTopic(int topicId) {
        try {
            return newsRepository.findAllNewsByTopicId(topicId).size();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "size-of-news-by-topic-error : {0}", ex.getMessage());
        }
        return 0;
    }

    @Override
    public List<News> findAllNewsByTitle(String title, Pageable pageable) {
        try {
            return newsRepository.findByNamePage(title,pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-news-by-title-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public int sizeOfNewsBTitle(String title) {
        return newsRepository.findByNameSize(title).size();
    }

    @Override
    public List<News> findByTime() {
        try {
            List<News> newsList = newsRepository.findAll();
            LocalDateTime localTime_n1 = LocalDateTime.now();
            LocalDateTime localTime_n2 = LocalDateTime.now();
            newsList.sort((n1, n2) -> -(int) ChronoUnit.MINUTES.between(n1.getTime(), localTime_n1)
                    + (int) ChronoUnit.HOURS.between(n2.getTime(), localTime_n2));
            newsList.stream()
                    .filter(News::isStatus)
                    .collect(Collectors.toList());
            return newsList;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-news-by-time-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<News> findByView(Pageable pageable) {
        try {
            return newsRepository.findByView(pageable).getContent();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-by-view-error : {0}", ex.getMessage());
        }
        return null;
    }

    //================== news đíu cần tag đâu================================
    @Override
    public List<News> findByTag(Tag tag) {
        try {

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-by-tag-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public int sizeOfNewsByTag(int tagId) {
        try {

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "size-of-news-by-tag-error : {0}", ex.getMessage());
        }
        return 0;
    }
    //================== news đíu cần tag đâu================================

    @Override
    public News findById(int id) {
        try {
            News news = newsRepository.findById(id);
            if (news.isStatus()) return news;

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean saveNews(News news) {
        try {
            newsRepository.save(news);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-news-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteNews(News news) {
        try {
            if (news.isStatus()) {
                news.setStatus(false);
                newsRepository.save(news);
                return true;
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-news-error : {0}", ex.getMessage());
        }
        return false;
    }
}
