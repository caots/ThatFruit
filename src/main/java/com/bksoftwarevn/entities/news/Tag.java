package com.bksoftwarevn.entities.news;


import com.bksoftwarevn.entities.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private boolean status;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "tags")
    private List<News> newsList = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "tags")
    private List<Product> products = new ArrayList<>();

}
