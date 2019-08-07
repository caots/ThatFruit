package com.bksoftwarevn.entities;


import com.bksoftwarevn.entities.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cart {

    private Product product;

    private int quantity;

    public Cart() {
    }

}
