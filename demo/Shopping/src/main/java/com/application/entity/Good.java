package com.application.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Good {

    //商品id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String good_id;

    //商品名称
    private String name;

    //商品简介
    private String introdution;

    //商品库存
    private int count;

    //商品价格
    private double price;

    public Good()
    {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGood_id() {
        return good_id;
    }

    public void setGood_id(String good_id) {
        this.good_id = good_id;
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
}
