package atrue.pranesh.creditmantri_weatherapi.ui;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;

import atrue.pranesh.creditmantri_weatherapi.R;
import atrue.pranesh.creditmantri_weatherapi.model.CityWeather;
import atrue.pranesh.creditmantri_weatherapi.network.ApiClient;
import atrue.pranesh.creditmantri_weatherapi.network.ApiInterface;
import atrue.pranesh.creditmantri_weatherapi.tasks.ParseResult;
import atrue.pranesh.creditmantri_weatherapi.tasks.TaskOutput;
import atrue.pranesh.creditmantri_weatherapi.tasks.TaskResult;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TodayFragment extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progress;

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
        //new TodayWeatherTask().execute();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String apiKey = sp.getString("apiKey", getActivity().getResources().getString(R.string.apiKey));
        String city = sp.getString("city", "Mumbai");

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> weatherCall = apiInterface.getWeather(city, apiKey);
        weatherCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String strResponse = fromStream(response.body().byteStream());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public String fromStream(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                out.append(line);
                out.append("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    private void setRecyAdapter(List<CityWeather> cityWeathers) {

    }

    class TodayWeatherTask extends AsyncTask<String, String, TaskOutput> {

        @Override
        protected TaskOutput doInBackground(String... params) {
            TaskOutput output = new TaskOutput();

            String response = "";
            String[] coords = new String[]{};

            if (params != null && params.length > 0) {
                final String zeroParam = params[0];
                if ("cachedResponse".equals(zeroParam)) {
                    response = params[1];
                    output.taskResult = TaskResult.SUCCESS;
                } else if ("coords".equals(zeroParam)) {
                    String lat = params[1];
                    String lon = params[2];
                    coords = new String[]{lat, lon};
                }
            }

            if (response.isEmpty()) {
                try {
                    URL url = provideURL(coords);
                    Log.i("URL", url.toString());
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    if (urlConnection.getResponseCode() == 200) {
                        InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
                        BufferedReader r = new BufferedReader(inputStreamReader);

                        int responseCode = urlConnection.getResponseCode();
                        String line = null;
                        while ((line = r.readLine()) != null) {
                            response += line + "\n";
                        }
                        r.close();
                        urlConnection.disconnect();
                        output.taskResult = TaskResult.SUCCESS;
                    } else if (urlConnection.getResponseCode() == 429) {
                        output.taskResult = TaskResult.TOO_MANY_REQUESTS;
                    } else {
                        output.taskResult = TaskResult.BAD_RESPONSE;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    output.taskResult = TaskResult.IO_EXCEPTION;
                }
            }

            if (TaskResult.SUCCESS.equals(output.taskResult)) {
                // Parse JSON data
                ParseResult parseResult = parseTodayJson(response);
                output.parseResult = parseResult;
            }

            return output;
        }
    }

    private ParseResult parseTodayJson(String result) {
        try {
            JSONObject reader = new JSONObject(result);

            final String code = reader.optString("cod");
            if ("404".equals(code)) {
                return ParseResult.CITY_NOT_FOUND;
            }


        } catch (JSONException e) {
            Log.e("JSONException Data", result);
            e.printStackTrace();
            return ParseResult.JSON_EXCEPTION;
        }

        return ParseResult.OK;
    }

    private URL provideURL(String[] coords) throws UnsupportedEncodingException, MalformedURLException {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String apiKey = sp.getString("apiKey", getActivity().getResources().getString(R.string.apiKey));

        StringBuilder urlBuilder = new StringBuilder("https://api.openweathermap.org/data/2.5/weather?");
        if (coords.length == 2) {
            urlBuilder.append("lat=").append(coords[0]).append("&lon=").append(coords[1]);
        } else {
            final String city = sp.getString("city", "Mumbai");
            urlBuilder.append("q=").append(URLEncoder.encode(city, "UTF-8"));
        }
        urlBuilder.append("&lang=").append(getLanguage());
        urlBuilder.append("&mode=json");
        urlBuilder.append("&appid=").append(apiKey);

        return new URL(urlBuilder.toString());
    }

    private String getLanguage() {
        String language = Locale.getDefault().getLanguage();
        if (language.equals("en")) {
            language = "en";
        }
        return language;
    }
}
