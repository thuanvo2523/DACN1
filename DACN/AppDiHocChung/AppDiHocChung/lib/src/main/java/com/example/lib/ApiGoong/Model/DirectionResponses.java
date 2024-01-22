package com.example.lib.ApiGoong.Model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DirectionResponses {
    @SerializedName("routes")
    private List<RoutesItem> routes;

    @SerializedName("geocoded_waypoints")
    private List<GeocodedWaypointsItem> geocodedWaypoints;


    public void setRoutes(List<RoutesItem> routes){
        this.routes = routes;
    }

    public List<RoutesItem> getRoutes(){
        return routes;
    }

    public void setGeocodedWaypoints(List<GeocodedWaypointsItem> geocodedWaypoints){
        this.geocodedWaypoints = geocodedWaypoints;
    }

    public List<GeocodedWaypointsItem> getGeocodedWaypoints(){
        return geocodedWaypoints;
    }

}
