package com.bksoftwarevn.entities.product;

import com.bksoftwarevn.entities.category.SmallCategory;
import com.bksoftwarevn.entities.company.Partner;
import com.bksoftwarevn.entities.news.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @Column(name = "product_code")
    private String productCode;

    private String image;

    @Column(name = "sale_cost")
    private double saleCost;

    @Column(name = "origin_cost")
    private double originCost;

    private int view;

    @Column(name = "init_date")
    private LocalDate initDate;

    @Column(name = "product_info")
    private String productInfo;

    @Column(name = "sale_number")
    private int saleNumber;

    private String origin;

    private String unit;

    @Column(name = "product_status")
    private boolean productStatus;

    @NotNull
    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "small_category_id", nullable = false)
    @NotNull
    private SmallCategory smallCategory;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_type_id", nullable = false)
    @NotNull
    private ProductType productType;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "partner_id")
    private Partner partner;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_has_tag",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private List<BuyForm> buyForms = new ArrayList<>();

    public Product() {
    }
}
