package com.example.mygo4lunch.models.distance;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Row {
    private final String TAG = Row.class.getSimpleName();

    @SerializedName("elements")
    private List<Elements> elements = null;

    public List<Elements> getElements() {
        return elements;
    }

    public void setElements(List<Elements> elements) {
        this.elements = elements;
    }
}
