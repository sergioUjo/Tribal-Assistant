package com.example.tribalassistent.client.service.connection;

import java.util.Date;

public class TravelTime {
    private static Date date = new Date();
    private Object[][] traveltimes;

    public TravelTime() {
        this.traveltimes = new Object[1][2];
        traveltimes[0][0] = "browser_send";
        traveltimes[0][1] = date.getTime();
    }

    public Object[][] getTraveltimes() {
        return traveltimes;
    }

}
