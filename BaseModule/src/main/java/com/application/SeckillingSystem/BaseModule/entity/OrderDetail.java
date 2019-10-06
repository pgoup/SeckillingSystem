package com.application.SeckillingSystem.BaseModule.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 一个物品所导致的订单
 *
 * @author PG
 */
@Entity(name = "order_detail")
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //订单号
    @Column(name = "order_detail_id", unique = true)
    private String orderDetailId;

    @Column(name = "good_id", nullable = false)
    private String goodId;

    @Column(name = "good_name")
    //购买的名称
    private String goodName;

    //购买的数量
    private int count;

    //购买的价格
    private double price;

    //购买的总价
    private double totalValue;

    //订单状态
    @Column(name = "order_status", columnDefinition = "bool default false")
    private boolean orderStatus;

    @ManyToOne(targetEntity = OrderForm.class)
    @JoinColumn(name = "order_form_id", referencedColumnName = "order_form_id")
    private OrderForm orderForm;

    @ManyToOne(targetEntity = ShoppingCart.class)
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "shopping_cart_id")
    private ShoppingCart shoppingCart;


}
