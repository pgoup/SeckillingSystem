package com.application.SeckillingSystem.BaseModule.dao;

import com.application.SeckillingSystem.BaseModule.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetail,Long> {

}
