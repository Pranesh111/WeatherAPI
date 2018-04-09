package atrue.pranesh.creditmantri_weatherapi.ui;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
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
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.List;

import atrue.pranesh.creditmantri_weatherapi.R;
import atrue.pranesh.creditmantri_weatherapi.model.CityWeather;

public class HomeFragment extends Fragment implements TabLayout.OnTabSelectedListener{
    ViewPager viewPager;
    TabLayout tabLayout;
    ProgressBar progress;
    CoordinatorLayout coordinatorLayout;
    private List<CityWeather> longTermWeather = new ArrayList<>();
    private List<CityWeather> longTermTodayWeather = new ArrayList<>();
    private List<CityWeather> longTermTomorrowWeather = new ArrayList<>();
    private WeatherAdapter adapter;

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
        progress = view.findViewById(R.id.progress);

        tabLayout.addTab(tabLayout.newTab().setText("Today1"));
        tabLayout.addTab(tabLayout.newTab().setText("Today2"));
        tabLayout.addTab(tabLayout.newTab().setText("Today3"));

        tabLayout.addOnTabSelectedListener(this);
        adapter = new WeatherAdapter(getFragmentManager(), tabLayout.getTabCount());
        adapter.addFragments(new TodayFragment());
        adapter.addFragments(new TomorrowFragment());
        adapter.addFragments(new FutureWeatherFragment());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
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
}
