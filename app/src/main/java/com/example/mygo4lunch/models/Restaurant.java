package com.example.mygo4lunch.models;

import java.util.List;

public class Restaurant {

    private String restaurantID;
    private String name;
    private Double latitude;
    private Double longitude;
    private String address;
    private boolean openNow;
    private int distance;
    private String photoReference;
    private float rating;
    private String phoneNumber;
    private String webSite;
    private List<WorkMate> workMatesEatingHere;

    public Restaurant(){ }


    public Restaurant(String restaurantID, String name, Double latitude, Double longitude, String address, boolean openNow, int distance, String photoReference, float rating, String phoneNumber, String webSite) {
        this.restaurantID = restaurantID;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.openNow = openNow;
        this.distance = distance;
        this.photoReference = photoReference;
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.webSite = webSite;
        this.workMatesEatingHere = workMatesEatingHere;
    }

    public Restaurant(String uid, String name, double latitude, double longitude, String photo, String address, int distance, boolean openNow, String webSite, String phoneNumber, float rating) {
    }


    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isOpenNow() {
        return openNow;
    }

    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public List<WorkMate> getWorkMatesEatingHere() {
        return workMatesEatingHere;
    }

    public void setWorkMatesGoingEating(List<WorkMate> users)
    { workMatesEatingHere = users; }



}
