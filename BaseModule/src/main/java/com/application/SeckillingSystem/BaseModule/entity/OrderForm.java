package com.application.SeckillingSystem.BaseModule.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

/**
 * 购物车购买下单
 * @author PG
 */

@Entity
@Data
@Table(name = "order_form")
public class OrderForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //订单号
    @Column(unique = true,name = "order_form_id")
    private String orderId;


    @OneToMany(targetEntity = OrderDetail.class)
    Set<OrderDetail> orderDetails;

    //配送地址
    private String address;

    //联系电话
    private String telephone;

    //联系人
    private String userName;

    //下单时间
    private String time;

    //订单状态
    @Column(name="order_status",columnDefinition = "bool default false")
    private boolean orderStatus;
}