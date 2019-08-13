package com.bksoftwarevn.entities.product;

import com.bksoftwarevn.entities.user.AppUser;
import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "buy_form")
public class BuyForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code_buy_form")
    private String codeBuyForm;

    private String name;

    private long phone;

    private String email;

    private String address;

    private LocalDate date;

    private boolean checked;

    private String note;

    private boolean status;

    @Column(name = "total_price")
    private long totalPrice;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "buy_form_has_product",
            joinColumns = @JoinColumn(name = "buy_form_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();
}
