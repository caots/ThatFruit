package com.bksoftwarevn.entities.company;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "contact_form")
public class ContactForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Column(name = "full_name")
    private String fullName;

    private int phone;

    private String content;

    private boolean checked;

    private boolean status;

    public ContactForm() {
    }
}
