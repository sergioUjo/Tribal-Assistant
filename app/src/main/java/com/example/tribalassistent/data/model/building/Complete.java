package com.example.tribalassistent.data.model.building;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Complete {
    private Integer job_id;
    private String location;
    private Integer price;
    private Integer village_id;
}
