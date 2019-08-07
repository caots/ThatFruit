package com.bksoftwarevn.repository.news;

import com.bksoftwarevn.entities.news.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

    Page<Topic> findByStatus(boolean status, Pageable pageable);

    @Query("select t from Topic t where t.status=true")
    List<Topic> findAllTopic();

    Topic findById(int id);

}
