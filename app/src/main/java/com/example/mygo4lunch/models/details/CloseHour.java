package com.example.mygo4lunch.models.details;

import com.google.gson.annotations.SerializedName;

public class CloseHour {
    @SerializedName("day")
    private Integer day;
    @SerializedName("time")
    private String time;

    public Integer getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }
}
