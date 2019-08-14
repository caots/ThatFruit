package com.bksoftwarevn.service.news;

import com.bksoftwarevn.entities.news.News;
import com.bksoftwarevn.entities.news.Tag;
import com.bksoftwarevn.entities.news.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {

    List<News> findAllNewsByNameTitlePage(String name, Pageable pageable);

    List<News> sizeOfNewsByNameTitle(String name);

    List<News> findAllNewsPage(Pageable pageable);

    List<News> findAllNewsByTopicPage(Topic topic, Pageable pageable);

    int sizeOfNewsByTopic(int topicId);

    List<News> findAllNewsByTitle(String title, Pageable pageable);

    int sizeOfNewsBTitle(String title);

    List<News> findByTime();

    List<News> findByView(Pageable pageable);

    List<News> findByTag(Tag tag);

    int sizeOfNewsByTag(int tagId);

    News findById(int id);

    boolean saveNews(News news);

    boolean deleteNews(News news);

}
