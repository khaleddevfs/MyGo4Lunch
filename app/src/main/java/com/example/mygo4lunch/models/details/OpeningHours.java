package com.example.mygo4lunch.models.details;

import com.google.gson.annotations.SerializedName;

public class OpeningHours {
    @SerializedName("open_now")
    private boolean openNow;

    public boolean getOpenNow() {
        return openNow;
    }

}
