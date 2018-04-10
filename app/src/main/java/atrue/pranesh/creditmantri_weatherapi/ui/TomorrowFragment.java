package atrue.pranesh.creditmantri_weatherapi.ui;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import java.util.List;

import atrue.pranesh.creditmantri_weatherapi.R;
import atrue.pranesh.creditmantri_weatherapi.model.CityWeather;
import atrue.pranesh.creditmantri_weatherapi.model.Forecast;
import atrue.pranesh.creditmantri_weatherapi.network.ApiClient;
import atrue.pranesh.creditmantri_weatherapi.network.ApiInterface;
import atrue.pranesh.creditmantri_weatherapi.ui.business.TomorrowAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TomorrowFragment extends Fragment implements View.OnClickListener{

    RecyclerView recyclerView;
    ProgressBar progress;
    Communicator communicator;
    String apiKey;
    String city;

    TomorrowAdapter tomorrowAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.today_fragment, container, false);
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
        progress.setVisibility(View.VISIBLE);
        callForeCast();
    }

    private void callForeCast() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Forecast>> weatherCall = apiInterface.getFutureWeather(city, apiKey);
        weatherCall.enqueue(new Callback<List<Forecast>>() {
            @Override
            public void onResponse(Call<List<Forecast>> call, Response<List<Forecast>> response) {
                List<Forecast> forecasts=response.body();
                
                setTomorrowAdapter(forecasts.get(0));
                setDayAfterTomorrowAdapter(forecasts.get(1));
                setFutureAdater(forecasts.get(2));
                
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Forecast>> call, Throwable t) {

            }
        });
    }

    private void setFutureAdater(Forecast forecast) {
        Bundle bundle=new Bundle();
        bundle.putString("details_from","FutureWeatherFragment");
        bundle.putSerializable("futureweather",forecast);
        communicator.sendData(bundle);
    }

    private void setDayAfterTomorrowAdapter(Forecast forecast) {
        Bundle bundle=new Bundle();
        bundle.putString("details_from","DayAfterTommorowFragment");
        bundle.putSerializable("dayaftertmrwweather",forecast);
        communicator.sendData(bundle);
    }

    private void setTomorrowAdapter(Forecast forecast) {
        Bundle bundle=new Bundle();
        bundle.putString("details_from","TomorrowAdapter");
        bundle.putSerializable("tomorrowweather",forecast);
        communicator.sendData(bundle);
    }

    public void setAdapter(Bundle bundle){
        Forecast forecast= (Forecast) bundle.getSerializable("tomorrowweather");
        tomorrowAdapter=new TomorrowAdapter(forecast,this);
        recyclerView.setAdapter(tomorrowAdapter);
    }

    @Override
    public void onClick(View view) {

    }
}
