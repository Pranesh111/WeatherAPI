package atrue.pranesh.creditmantri_weatherapi.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;

import atrue.pranesh.creditmantri_weatherapi.R;
import atrue.pranesh.creditmantri_weatherapi.model.CityWeather;

/**
 * Created by Adminitrator on 4/11/2018.
 * Copyright IMDSTAR Technologies
 */

public class HomeDetailFragment extends Fragment {
    CityWeather cityWeather;
    TextView txtHumidity, txtPressure,txtWind,txtSunrise,txtSunset;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();

        txtHumidity = view.findViewById(R.id.txtHumi);
        txtPressure = view.findViewById(R.id.txtPres);
        txtWind = view.findViewById(R.id.txtWind);
        txtSunrise = view.findViewById(R.id.txtSunRise);
        txtSunset = view.findViewById(R.id.txtSunSet);

        if (bundle != null) {
            cityWeather = (CityWeather) bundle.get("details");
            inflateDetails();
        }
    }

    private void inflateDetails() {
        txtPressure.setText(cityWeather.main.pressure+ getString(R.string.setting_unit_hpa));
        txtHumidity.setText(cityWeather.main.humidity+ " %");
        txtWind.setText(cityWeather.wind.speed +getString(R.string.speed_unit_mps));
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(getActivity());
        txtSunrise.setText(timeFormat.format(cityWeather.sys.sunrise));
        txtSunset.setText(timeFormat.format(cityWeather.sys.sunset));
    }


}
