package atrue.pranesh.creditmantri_weatherapi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


/**
 * Created by Adminitrator on 4/10/2018.
 * Copyright IMDSTAR Technologies
 */

public class Forecast implements Serializable{

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

    public static class Weather {
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
        public double deg;
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
} /*implements Parcelable{

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

    protected Forecast(Parcel in) {
        cod = in.readString();
        message = in.readDouble();
        cnt = in.readInt();
        list = in.createTypedArrayList(List.CREATOR);
        city = in.readParcelable(City.class.getClassLoader());
    }

    public static final Creator<Forecast> CREATOR = new Creator<Forecast>() {
        @Override
        public Forecast createFromParcel(Parcel in) {
            return new Forecast(in);
        }

        @Override
        public Forecast[] newArray(int size) {
            return new Forecast[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cod);
        parcel.writeDouble(message);
        parcel.writeInt(cnt);
        parcel.writeTypedList(list);
        parcel.writeParcelable(city, i);
    }

    public static class Main implements Parcelable{
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

        protected Main(Parcel in) {
            temp = in.readDouble();
            temp_min = in.readDouble();
            temp_max = in.readDouble();
            pressure = in.readDouble();
            sea_level = in.readDouble();
            grnd_level = in.readDouble();
            humidity = in.readInt();
            temp_kf = in.readDouble();
        }

        public static final Creator<Main> CREATOR = new Creator<Main>() {
            @Override
            public Main createFromParcel(Parcel in) {
                return new Main(in);
            }

            @Override
            public Main[] newArray(int size) {
                return new Main[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeDouble(temp);
            parcel.writeDouble(temp_min);
            parcel.writeDouble(temp_max);
            parcel.writeDouble(pressure);
            parcel.writeDouble(sea_level);
            parcel.writeDouble(grnd_level);
            parcel.writeInt(humidity);
            parcel.writeDouble(temp_kf);
        }
    }

    public static class Weather implements Parcelable{
        @SerializedName("id")
        public int id;
        @SerializedName("main")
        public String main;
        @SerializedName("description")
        public String description;
        @SerializedName("icon")
        public String icon;

        protected Weather(Parcel in) {
            id = in.readInt();
            main = in.readString();
            description = in.readString();
            icon = in.readString();
        }

        public static final Creator<Weather> CREATOR = new Creator<Weather>() {
            @Override
            public Weather createFromParcel(Parcel in) {
                return new Weather(in);
            }

            @Override
            public Weather[] newArray(int size) {
                return new Weather[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeString(main);
            parcel.writeString(description);
            parcel.writeString(icon);
        }
    }

    public static class Clouds implements Parcelable{
        @SerializedName("all")
        public int all;

        protected Clouds(Parcel in) {
            all = in.readInt();
        }

        public static final Creator<Clouds> CREATOR = new Creator<Clouds>() {
            @Override
            public Clouds createFromParcel(Parcel in) {
                return new Clouds(in);
            }

            @Override
            public Clouds[] newArray(int size) {
                return new Clouds[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(all);
        }
    }

    public static class Wind implements Parcelable{
        @SerializedName("speed")
        public double speed;
        @SerializedName("deg")
        public double deg;

        protected Wind(Parcel in) {
            speed = in.readDouble();
            deg = in.readInt();
        }

        public static final Creator<Wind> CREATOR = new Creator<Wind>() {
            @Override
            public Wind createFromParcel(Parcel in) {
                return new Wind(in);
            }

            @Override
            public Wind[] newArray(int size) {
                return new Wind[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeDouble(speed);
            parcel.writeDouble(deg);
        }
    }

    public static class Sys implements Parcelable{
        @SerializedName("pod")
        public String pod;

        protected Sys(Parcel in) {
            pod = in.readString();
        }

        public static final Creator<Sys> CREATOR = new Creator<Sys>() {
            @Override
            public Sys createFromParcel(Parcel in) {
                return new Sys(in);
            }

            @Override
            public Sys[] newArray(int size) {
                return new Sys[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(pod);
        }
    }

    public static class List implements Parcelable {
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

        protected List(Parcel in) {
            dt = in.readInt();
            main = in.readParcelable(Main.class.getClassLoader());
            weather = in.createTypedArrayList(Weather.CREATOR);
            clouds = in.readParcelable(Clouds.class.getClassLoader());
            wind = in.readParcelable(Wind.class.getClassLoader());
            sys = in.readParcelable(Sys.class.getClassLoader());
            dt_txt = in.readString();
        }

        public static final Creator<List> CREATOR = new Creator<List>() {
            @Override
            public List createFromParcel(Parcel in) {
                return new List(in);
            }

            @Override
            public List[] newArray(int size) {
                return new List[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(dt);
            parcel.writeParcelable(main, i);
            parcel.writeTypedList(weather);
            parcel.writeParcelable(clouds, i);
            parcel.writeParcelable(wind, i);
            parcel.writeParcelable(sys, i);
            parcel.writeString(dt_txt);
        }
    }

    public static class Coord implements Parcelable{
        @SerializedName("lat")
        public double lat;
        @SerializedName("lon")
        public double lon;

        protected Coord(Parcel in) {
            lat = in.readDouble();
            lon = in.readDouble();
        }

        public static final Creator<Coord> CREATOR = new Creator<Coord>() {
            @Override
            public Coord createFromParcel(Parcel in) {
                return new Coord(in);
            }

            @Override
            public Coord[] newArray(int size) {
                return new Coord[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeDouble(lat);
            parcel.writeDouble(lon);
        }
    }

    public static class City implements Parcelable{
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

        protected City(Parcel in) {
            id = in.readInt();
            name = in.readString();
            coord = in.readParcelable(Coord.class.getClassLoader());
            country = in.readString();
            population = in.readInt();
        }

        public static final Creator<City> CREATOR = new Creator<City>() {
            @Override
            public City createFromParcel(Parcel in) {
                return new City(in);
            }

            @Override
            public City[] newArray(int size) {
                return new City[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeString(name);
            parcel.writeParcelable(coord, i);
            parcel.writeString(country);
            parcel.writeInt(population);
        }
    }
}
*/