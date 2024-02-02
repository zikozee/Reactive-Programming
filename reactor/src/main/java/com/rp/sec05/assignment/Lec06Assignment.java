package com.rp.sec05.assignment;

import com.rp.courseutil.Util;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 02 Feb, 2024
 */

public class Lec06Assignment {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        RevenueService revenueService = new RevenueService();
        InventoryService inventoryService = new InventoryService();

        // revenue and inv -> observe the order stream
        orderService.orderStream().subscribe(revenueService.subscribeOrderStream());
        orderService.orderStream().subscribe(inventoryService.subscribeOrderStream());

        inventoryService.inventoryStream()
                .subscribe(Util.subscriber("inventory"));

        inventoryService.inventoryStream()
                .subscribe(Util.subscriber("revenue"));

        Util.sleepSecond(60);
    }
}
