package atrue.pranesh.creditmantri_weatherapi.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.DateFormat;

import atrue.pranesh.creditmantri_weatherapi.R;
import atrue.pranesh.creditmantri_weatherapi.model.CityWeather;

/**
 * Created by Adminitrator on 4/11/2018.
 * Copyright IMDSTAR Technologies
 */

public class HomeDetailActivity extends AppCompatActivity {
    CityWeather cityWeather;
    TextView txtHumidity, txtPressure,txtWind,txtSunrise,txtSunset;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        Bundle bundle = getIntent().getExtras();

        txtHumidity = findViewById(R.id.txtHumi);
        txtPressure = findViewById(R.id.txtPres);
        txtWind = findViewById(R.id.txtWind);
        txtSunrise = findViewById(R.id.txtSunRise);
        txtSunset = findViewById(R.id.txtSunSet);

        if (bundle != null) {
            cityWeather = (CityWeather) bundle.get("details");
            inflateDetails();
        }
    }
    private void inflateDetails() {
        txtPressure.setText(cityWeather.main.pressure+ getString(R.string.setting_unit_hpa));
        txtHumidity.setText(cityWeather.main.humidity+ " %");
        txtWind.setText(cityWeather.wind.speed +getString(R.string.speed_unit_mps));
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(this);
        txtSunrise.setText(timeFormat.format(cityWeather.sys.sunrise));
        txtSunset.setText(timeFormat.format(cityWeather.sys.sunset));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
