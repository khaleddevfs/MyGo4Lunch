package com.example.mygo4lunch.service;

import com.example.mygo4lunch.models.DetailsRestaurantResponseApi;
import com.example.mygo4lunch.models.RestaurantResponseApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlacesService {

    String BASE_URL_GOOGLE = "https://maps.googleapis.com/maps/api/place/";
    String PHOTO_REF_GOOGLE = "photo?photoreference=";
    String MAX_WIDTH_GOOGLE = "&maxwidth=";
    String KEY_GOOGLE = "&key=";


    @GET("nearbysearch/json")
    Call<RestaurantResponseApi> getNeatByPlaces (
            @Query("key") String key,
            @Query("type") String type,
            @Query("location") String location,
            @Query("radius") int radius);


    @GET("details/json")
    Call<DetailsRestaurantResponseApi> getRestaurantDetail(
            @Query("key") String pKey,
            @Query("place_id") String pPlaceId,
            @Query("fields") String pFields);
}
