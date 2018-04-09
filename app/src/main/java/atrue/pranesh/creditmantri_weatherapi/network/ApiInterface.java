package atrue.pranesh.creditmantri_weatherapi.network;

import java.util.List;

import atrue.pranesh.creditmantri_weatherapi.model.CityWeather;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("weather?&lang=en&mode=json")
    Call<ResponseBody> getWeather(@Query("q") String city, @Query("appid") String appid);
}
