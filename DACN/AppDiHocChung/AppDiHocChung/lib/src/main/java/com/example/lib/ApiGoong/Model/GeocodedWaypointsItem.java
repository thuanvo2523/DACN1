package com.example.lib.ApiGoong.Model;

import com.google.gson.annotations.SerializedName;

public class GeocodedWaypointsItem {
    @SerializedName("geocoder_status")
    private String geocoderStatus;

    @SerializedName("place_id")
    private String placeId;

    public void setGeocoderStatus(String geocoderStatus){
        this.geocoderStatus = geocoderStatus;
    }

    public String getGeocoderStatus(){
        return geocoderStatus;
    }

    public void setPlaceId(String placeId){
        this.placeId = placeId;
    }

    public String getPlaceId(){
        return placeId;
    }
}
