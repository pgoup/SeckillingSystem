package com.application.entity;

import javax.persistence.*;

@Entity
public class Good {

    //商品id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //商品编号
    @Column(unique = true,nullable = false)
    private String goodId;

    //商品名称
    private String name;

    //商品简介
    private String introdution;

    //商品是否打折
    private int discount;

    //商品类型
    private String kind;

    //商品库存
    private int count;

    //商品价格
    private double price;

    public Good() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntrodution() {
        return introdution;
    }

    public void setIntrodution(String introdution) {
        this.introdution = introdution;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int isDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}