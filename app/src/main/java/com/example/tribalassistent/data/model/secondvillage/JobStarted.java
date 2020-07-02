package com.example.tribalassistent.data.model.secondvillage;

import lombok.Data;

@Data
public class JobStarted {
    private String job_id;
    private Long time_completed;
    private Long time_started;
}
