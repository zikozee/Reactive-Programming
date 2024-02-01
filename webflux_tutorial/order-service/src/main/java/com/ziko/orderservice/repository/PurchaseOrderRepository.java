package com.ziko.orderservice.repository;

import com.ziko.orderservice.entity.PurchaseOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 01 Feb, 2024
 */

@Repository
public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Integer> {

    List<PurchaseOrder> findPurchaseOrderByUserId(int userId);
}
