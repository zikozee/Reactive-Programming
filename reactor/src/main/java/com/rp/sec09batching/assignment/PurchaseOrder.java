package com.rp.sec09batching.assignment;

import com.rp.courseutil.Util;
import lombok.Data;
import lombok.ToString;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 12 Feb, 2024
 */

@Data
@ToString
public class PurchaseOrder {
    private String item;
    private double price;
    private String category;

    public PurchaseOrder(){
        this.item = Util.faker().commerce().productName();
        this.price = Double.parseDouble(Util.faker().commerce().price());
        this.category = Util.faker().commerce().department();
    }
}
