package com.application.SeckillingSystem.BaseModule.dao;

import com.application.SeckillingSystem.BaseModule.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author PG
 */
public interface ShoppingCartDao extends JpaRepository<ShoppingCart, Long> {

    @Query(value = "select * from shopping_cart where user_id=?1 limit 1", nativeQuery = true)
    ShoppingCart queryByUserId(String userId);

}
