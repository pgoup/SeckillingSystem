package com.application.SeckillingSystem.BaseModule.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Good {

    //商品id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //商品编号
    @Column(unique = true, nullable = false)
    private String goodId;

    //商品名称
    private String name;

    //商品简介
    private String introduction;

    //商品是否打折
    private int discount;

    //商品类型
    private String kind;

    //商品数量
    private int count;

    //商品价格
    private double price;

    //商品总价格
    private double totalValue;

    public Good() {
    }
}