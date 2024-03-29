package com.example.mygo4lunch.models.distance;

import com.google.gson.annotations.SerializedName;

public class Distance {
    private final String TAG = Distance.class.getSimpleName();

    @SerializedName("value")
    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
