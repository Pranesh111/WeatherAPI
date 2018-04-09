package atrue.pranesh.creditmantri_weatherapi.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Adminitrator on 4/9/2018.
 * Copyright IMDSTAR Technologies
 */

public class ApiClient {

    public static String Base_Url= "https://api.openweathermap.org/data/2.5/";
    public static Retrofit retrofit=null;

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
