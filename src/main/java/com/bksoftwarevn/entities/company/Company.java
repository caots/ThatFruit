package com.bksoftwarevn.entities.company;


import com.bksoftwarevn.entities.category.BigCategory;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "company")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int phone;

    private String description;

    private String email;

    private String address;

    private boolean status;

    public Company() {
    }

}
