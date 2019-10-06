package com.application.SeckillingSystem.BaseModule.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * 购物车实体类
 *
 * @author PG
 */
@Entity(name = "shopping_cart")
@Data
public class ShoppingCart {
    //商品id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "shopping_cart_id", unique = true)
    private String shoppingCartId;

    @OneToMany(targetEntity = OrderDetail.class)
    private Set<OrderDetail> orderDetails;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    
}
