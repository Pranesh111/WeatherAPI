package atrue.pranesh.creditmantri_weatherapi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Adminitrator on 4/9/2018.
 * Copyright IMDSTAR Technologies
 */

public class CityWeather implements Serializable {

    @SerializedName("coord")
    public Coord coord;
    @SerializedName("weather")
    public List<Weather> weather;
    @SerializedName("base")
    public String base;
    @SerializedName("main")
    public Main main;
    @SerializedName("visibility")
    public int visibility;
    @SerializedName("wind")
    public Wind wind;
    @SerializedName("clouds")
    public Clouds clouds;
    @SerializedName("dt")
    public int dt;
    @SerializedName("sys")
    public Sys sys;
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("cod")
    public int cod;

    public static class Coord implements Serializable {
        @SerializedName("lon")
        public double lon;
        @SerializedName("lat")
        public double lat;
    }

    public static class Weather  implements Serializable {
        @SerializedName("id")
        public int id;
        @SerializedName("main")
        public String main;
        @SerializedName("description")
        public String description;
        @SerializedName("icon")
        public String icon;
    }

    public static class Main implements Serializable {
        @SerializedName("temp")
        public double temp;
        @SerializedName("pressure")
        public int pressure;
        @SerializedName("humidity")
        public int humidity;
        @SerializedName("temp_min")
        public double temp_min;
        @SerializedName("temp_max")
        public double temp_max;
    }

    public static class Wind  implements Serializable {
        @SerializedName("speed")
        public double speed;
        @SerializedName("deg")
        public int deg;
    }

    public static class Clouds implements Serializable {
        @SerializedName("all")
        public int all;
    }

    public static class Sys  implements Serializable {
        @SerializedName("type")
        public int type;
        @SerializedName("id")
        public int id;
        @SerializedName("message")
        public double message;
        @SerializedName("country")
        public String country;
        @SerializedName("sunrise")
        public int sunrise;
        @SerializedName("sunset")
        public int sunset;
    }
}
