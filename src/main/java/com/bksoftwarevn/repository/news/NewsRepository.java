package com.bksoftwarevn.repository.news;

import com.bksoftwarevn.entities.news.News;
import com.bksoftwarevn.entities.news.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {

    Page<News> findByStatus(boolean status, Pageable pageable);

    @Query("select n from News n where n.status = true and  n.topic.id= :id")
    List<News> findAllNewsByTopicId(@Param("id") int id);

    Page<News> findByTopic(Topic topic, Pageable pageable);

    @Query("select n from News n where n.status = true order by n.view desc")
    Page<News> findByView(Pageable pageable);

    News findById(int id);

    @Query("SELECT n FROM News n WHERE n.title LIKE CONCAT('%',:title,'%') and n.status= true ")
    List<News> findByNameSize(@Param("title") String name);

    @Query("SELECT n FROM News n WHERE n.title LIKE CONCAT('%',:title,'%') and n.status= true ")
    Page<News> findByNamePage(@Param("title") String name,Pageable pageable);


}
