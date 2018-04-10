package atrue.pranesh.creditmantri_weatherapi.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import atrue.pranesh.creditmantri_weatherapi.R;
import atrue.pranesh.creditmantri_weatherapi.model.CityWeather;
import atrue.pranesh.creditmantri_weatherapi.network.ApiClient;
import atrue.pranesh.creditmantri_weatherapi.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    ViewPager viewPager;
    TabLayout tabLayout;
    CoordinatorLayout coordinatorLayout;
    private WeatherAdapter adapter;
    TextView txtMain, txtDesc, txtMax, txtMin, txtPres, txtHumi;
    String apiKey;
    String city;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        coordinatorLayout = view.findViewById(R.id.coordinatorLayout);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);

        txtMain = view.findViewById(R.id.txtMain);
        txtDesc = view.findViewById(R.id.txtDesc);
        txtMax = view.findViewById(R.id.txtMax);
        txtMin = view.findViewById(R.id.txtMin);
        txtPres = view.findViewById(R.id.txtPres);
        txtHumi = view.findViewById(R.id.txtHumi);


        tabLayout.addTab(tabLayout.newTab().setText("Today1"));
        tabLayout.addTab(tabLayout.newTab().setText("Today2"));
        tabLayout.addTab(tabLayout.newTab().setText("Today3"));

        tabLayout.addOnTabSelectedListener(this);
        adapter = new WeatherAdapter(getFragmentManager(), tabLayout.getTabCount());
        adapter.addFragments(new TomorrowFragment());
        adapter.addFragments(new TomorrowFragment());
        adapter.addFragments(new FutureWeatherFragment());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

        apiKey = sp.getString("apiKey", getActivity().getResources().getString(R.string.apiKey));
        city = sp.getString("city", "Mumbai");

        callTodayReport();
    }

    private void callTodayReport() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<CityWeather> weatherCall = apiInterface.getWeather(city, apiKey);
        weatherCall.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                CityWeather cityWeather = response.body();
                showData(cityWeather);
            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        if (!isNetworkAvailable()) {
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, getString(R.string.internet_not_avail), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private class WeatherAdapter extends FragmentStatePagerAdapter {
        private ArrayList<Fragment> fragments = new ArrayList<>();

        public WeatherAdapter(FragmentManager fm, int tabCount) {
            super(fm);
        }

        private void addFragments(Fragment fragment) {
            fragments.add(fragment);
        }
        private Fragment findFragmentByPosition(int pos) {return fragments.get(pos);}

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return fragments.get(0);
                case 1:
                    return fragments.get(1);
                case 2:
                    return fragments.get(2);
            }
            return null;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    public void showData(CityWeather cityWeather) {
        if (cityWeather != null) {
            if (cityWeather.weather != null && cityWeather.weather.size() > 0) {
                txtMain.setText(cityWeather.weather.get(0).main);
                txtDesc.setText(cityWeather.weather.get(0).description);
            }
            if (cityWeather.main != null) {
                txtMax.setText(cityWeather.main.temp_max + "");
                txtMin.setText(cityWeather.main.temp_min + "");
                txtPres.setText(cityWeather.main.pressure + "");
                txtHumi.setText(cityWeather.main.humidity + "");
            }
        }
    }

    public void reDirectPage(Bundle bundle) {
        if (bundle != null && bundle.containsKey("details_from")) {
            String details_from = bundle.getString("details_from");
            if (details_from.equalsIgnoreCase(TomorrowFragment.class.getName())) {
                ((TomorrowFragment) adapter.findFragmentByPosition(0)).setAdapter(bundle);
            }else if (details_from.equalsIgnoreCase(DayAfterTommorowFragment.class.getName())){
                ((DayAfterTommorowFragment) adapter.findFragmentByPosition(1)).showData(bundle);
            }else if (details_from.equalsIgnoreCase(FutureWeatherFragment.class.getName())){
                ((FutureWeatherFragment) adapter.findFragmentByPosition(1)).showData(bundle);
            }
        }
    }

}
