package com.bksoftwarevn.entities.category;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Table(name = "big_category")
@SecondaryTables({
        @SecondaryTable(name = "menu")
})
public class BigCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id", nullable = false)
    @NotNull
    private Menu menu;

    public BigCategory() {
    }

}