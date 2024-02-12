package com.rp.sec09batching.helper;

import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 12 Feb, 2024
 */

@ToString
public class RevenueReport {

    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final Map<String, Double> revenue;

    public RevenueReport(Map<String, Double> revenue) {
        this.revenue = revenue;
    }
}
