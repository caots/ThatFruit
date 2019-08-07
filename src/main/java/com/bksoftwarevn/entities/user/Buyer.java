package com.bksoftwarevn.entities.user;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


// khi mua hàng mà ng dùng chưa có tài khoản
@Data
@Entity
@Table(name = "buyer")
public class Buyer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int phone;

    private String email;

    private String address;

    private boolean status;

    public Buyer() {
    }

}
