package atrue.pranesh.creditmantri_weatherapi.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import atrue.pranesh.creditmantri_weatherapi.MainActivity;
import atrue.pranesh.creditmantri_weatherapi.R;
import atrue.pranesh.creditmantri_weatherapi.model.Forecast;
import atrue.pranesh.creditmantri_weatherapi.network.ApiClient;
import atrue.pranesh.creditmantri_weatherapi.network.ApiInterface;
import atrue.pranesh.creditmantri_weatherapi.ui.business.TomorrowAdapter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TomorrowFragment extends Fragment implements View.OnClickListener {

    RecyclerView recyclerView;
    ProgressBar progress;
    Communicator communicator;
    String apiKey;
    String city;
    List<Forecast.List> foreCastLst = new ArrayList<>();
    TomorrowAdapter tomorrowAdapter;
    Gson gson;
    List<Forecast.List> tmrwList;
    List<Forecast.List> dayAfterTmrwList;
    List<Forecast.List> tfutureList;
    private List<Forecast.List> tommorwLists;
    private boolean isViewShown = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tmrw_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progress = view.findViewById(R.id.progress);
        recyclerView = view.findViewById(R.id.todayRecy);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        communicator = (Communicator) getActivity();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        apiKey = sp.getString("apiKey", getActivity().getResources().getString(R.string.apiKey));
        city = sp.getString("city", "Mumbai");
        callForeCast();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void callForeCast() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> weatherCall = apiInterface.getFutureWeather(city, apiKey);
        weatherCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                if(response.body()!=null){
                    InputStream inputStream = response.body().byteStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    try {
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line);
                            stringBuilder.append("\r\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String jsonResponse;
                    try {
                        JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                        if (jsonObject.has("cod")) {
                            String code = jsonObject.getString("cod");
                            if (code.equals("200")) {
                                if (jsonObject.has("list")) {
                                    jsonResponse = jsonObject.getJSONArray("list").toString();
                                    Gson gson = new Gson();
                                    List<Forecast.List> foreCastLst = Arrays.asList(gson.fromJson(jsonResponse, Forecast.List[].class));
                                    getTmrwList(foreCastLst);
                                    setTomorrowAdapter(tmrwList);
                                    setDayAfterTomorrowAdapter(dayAfterTmrwList);
                                    setFutureAdater(tfutureList);
                                }
                            }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(getActivity(),"City not found",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getTmrwList(List<Forecast.List> foreCastLst) {
        tmrwList = new ArrayList<>();
        dayAfterTmrwList = new ArrayList<>();
        tfutureList = new ArrayList<>();
        for (Forecast.List list : foreCastLst) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            Date today = new Date();
            Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
            Date dayAfterTmrw = new Date(today.getTime() + 2 * (1000 * 60 * 60 * 24));
            Date futureDate = new Date(today.getTime() + 3 * (1000 * 60 * 60 * 24));

            String tmrwDate = sdf.format(tomorrow);
            String dayAftertmrwDate = sdf.format(dayAfterTmrw);
            String nearFutureDate = sdf.format(futureDate);
            if (getPostDate(list.dt_txt).equalsIgnoreCase(tmrwDate)) {
                tmrwList.add(list);
            } else if (getPostDate(list.dt_txt).equalsIgnoreCase(dayAftertmrwDate)) {
                dayAfterTmrwList.add(list);
            } else if (getPostDate(list.dt_txt).equalsIgnoreCase(nearFutureDate)) {
                tfutureList.add(list);
            }
        }
    }

    public static String getPostDate(String post_date) {
        String postDate = "";
        try {
            if (post_date.equalsIgnoreCase("")) return "";
            SimpleDateFormat neededFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);

            Date date = formatter.parse(post_date);

            postDate = neededFormatter.format(date);
            return postDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setFutureAdater(List<Forecast.List> forecast) {
        progress.setVisibility(View.GONE);
        Bundle bundle = new Bundle();
        bundle.putString("details_from", FutureWeatherFragment.class.getName());
        bundle.putSerializable("futureweather", (Serializable) forecast);
        communicator.sendData(bundle);
    }

    private void setDayAfterTomorrowAdapter(List<Forecast.List> forecast) {
        progress.setVisibility(View.GONE);
        Bundle bundle = new Bundle();
        bundle.putString("details_from", DayAfterTommorowFragment.class.getName());
        bundle.putSerializable("dayaftertmrwweather", (Serializable) forecast);
        communicator.sendData(bundle);
    }

    private void setTomorrowAdapter(List<Forecast.List> forecast) {
        progress.setVisibility(View.GONE);
        Bundle bundle = new Bundle();
        bundle.putString("details_from", TomorrowFragment.class.getName());
        bundle.putSerializable("tomorrowweather", (Serializable) forecast);
        communicator.sendData(bundle);
    }

    public void setAdapter(Bundle bundle) {
        tommorwLists = (List<Forecast.List>) bundle.getSerializable("tomorrowweather");
        if (recyclerView != null) {
            progress.setVisibility(View.GONE);
            tomorrowAdapter = new TomorrowAdapter(tommorwLists, this, (MainActivity) getActivity());
            recyclerView.setAdapter(tomorrowAdapter);
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getView() != null) {
            isViewShown = true;
            callForeCast();
        } else {
            isViewShown = false;
        }
    }

}
