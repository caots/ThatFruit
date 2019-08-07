package com.bksoftwarevn.entities.home_page;

import com.bksoftwarevn.entities.category.BigCategory;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Table(name = "footer_menu_details")
@SecondaryTables({
        @SecondaryTable(name = "footer_menu")
})
public class FooterMenuDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "footer_menu_id",nullable = false)
    @NotNull
    private FooterMenu footerMenu;

    public FooterMenuDetails() {
    }

}
