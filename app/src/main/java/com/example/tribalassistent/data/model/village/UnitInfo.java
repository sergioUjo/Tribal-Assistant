package com.example.tribalassistent.data.model.village;

import java.util.Map;

import lombok.Data;

@Data
class UnitInfo {
    private Integer village_id;
    private Map<String, AvailableUnit> available_units;
    private Object queues;
}
