package com.bksoftwarevn.entities.news;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "news")
@SecondaryTables({
        @SecondaryTable(name = "topic"),
        @SecondaryTable(name = "tag")
})
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String content;

    private String description;

    private LocalDateTime time;

    private int view;

    private boolean status;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id", nullable = false)
    @NotNull
    private Topic topic;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "news_has_tag",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();

    public News() {
    }

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
    }
}
