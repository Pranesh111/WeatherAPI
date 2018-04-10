package atrue.pranesh.creditmantri_weatherapi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Adminitrator on 4/10/2018.
 * Copyright IMDSTAR Technologies
 */

public class Forecast implements Serializable {

    @SerializedName("cod")
    public String cod;
    @SerializedName("message")
    public double message;
    @SerializedName("cnt")
    public int cnt;
    @SerializedName("list")
    public java.util.List<List> list;
    @SerializedName("city")
    public City city;

    public static class Main {
        @SerializedName("temp")
        public double temp;
        @SerializedName("temp_min")
        public double temp_min;
        @SerializedName("temp_max")
        public double temp_max;
        @SerializedName("pressure")
        public double pressure;
        @SerializedName("sea_level")
        public double sea_level;
        @SerializedName("grnd_level")
        public double grnd_level;
        @SerializedName("humidity")
        public int humidity;
        @SerializedName("temp_kf")
        public double temp_kf;
    }

    public static class Weather implements Serializable{
        @SerializedName("id")
        public int id;
        @SerializedName("main")
        public String main;
        @SerializedName("description")
        public String description;
        @SerializedName("icon")
        public String icon;
    }

    public static class Clouds {
        @SerializedName("all")
        public int all;
    }

    public static class Wind {
        @SerializedName("speed")
        public double speed;
        @SerializedName("deg")
        public int deg;
    }

    public static class Sys {
        @SerializedName("pod")
        public String pod;
    }

    public static class List implements Serializable {
        @SerializedName("dt")
        public int dt;
        @SerializedName("main")
        public Main main;
        @SerializedName("weather")
        public java.util.List<Weather> weather;
        @SerializedName("clouds")
        public Clouds clouds;
        @SerializedName("wind")
        public Wind wind;
        @SerializedName("sys")
        public Sys sys;
        @SerializedName("dt_txt")
        public String dt_txt;
    }

    public static class Coord {
        @SerializedName("lat")
        public double lat;
        @SerializedName("lon")
        public double lon;
    }

    public static class City {
        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
        @SerializedName("coord")
        public Coord coord;
        @SerializedName("country")
        public String country;
        @SerializedName("population")
        public int population;
    }
}
