package com.bksoftwarevn.entities.company;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "contact")
@SecondaryTables({
        @SecondaryTable(name = "company")
})
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String facebook;

    private String zalo;

    private String instagram;

    private String youtube;

    private String map;

    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", nullable = false)
    @NotNull
    private Company company;

    public Contact(){}


}
