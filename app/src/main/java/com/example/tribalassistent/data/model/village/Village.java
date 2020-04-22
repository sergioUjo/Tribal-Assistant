package com.example.tribalassistent.data.model.village;

import com.example.tribalassistent.data.model.common.Resources;

import java.util.Map;

import lombok.Data;

@Data
public class Village {
    private Integer villageId;
    private Integer res_last_update;
    private Integer storage;
    private Integer base_storage;
    private Resources resources;
    private Integer building_queue_slots;
    private ProductionRates production_rates;
    private String name;
    private Integer x;
    private Integer y;
    private Integer province_id;
    private String province_name;
    private Integer continent_id;
    private Integer max_loyalty;
    private Integer loyalty;
    private Integer points;
    private Map<String, Building> buildings;
    private Object effects;
    private Object preceptory_order;
}
