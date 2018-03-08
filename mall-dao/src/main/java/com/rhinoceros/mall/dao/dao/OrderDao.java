package com.rhinoceros.mall.dao.dao;
/* created at 4:40 PM 3/6/2018  */

import com.rhinoceros.mall.core.pojo.Order;
import com.rhinoceros.mall.core.query.PageQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {

    /**
     * 通过userId查找订单
     * @param userId
     * @return
     */
    List<Order> findByUserId(Long userId);

    List<Order> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") String status, @Param("page")PageQuery pageQuery);

    Integer findOrderNumByUserIdAndStatus(@Param("userId") Long userId, @Param("status") String status);
}