package atrue.pranesh.creditmantri_weatherapi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;

import atrue.pranesh.creditmantri_weatherapi.R;
import atrue.pranesh.creditmantri_weatherapi.model.CityWeather;

/**
 * Created by Adminitrator on 4/11/2018.
 * Copyright IMDSTAR Technologies
 */

public class HomeDetailActivity extends AppCompatActivity implements View.OnClickListener {
    CityWeather cityWeather;
    TextView txtHumidity, txtPressure, txtWind, txtSunrise, txtSunset;
    Button btnShare;
    DateFormat timeFormat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        Bundle bundle = getIntent().getExtras();
        timeFormat = android.text.format.DateFormat.getTimeFormat(this);
        txtHumidity = findViewById(R.id.txtHumi);
        txtPressure = findViewById(R.id.txtPres);
        txtWind = findViewById(R.id.txtWind);
        txtSunrise = findViewById(R.id.txtSunRise);
        txtSunset = findViewById(R.id.txtSunSet);
        btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(this);
        if (bundle != null) {
            cityWeather = (CityWeather) bundle.get("details");
            inflateDetails();
        }
    }

    private void inflateDetails() {
        txtPressure.setText(cityWeather.main.pressure + getString(R.string.setting_unit_hpa));
        txtHumidity.setText(cityWeather.main.humidity + " %");
        txtWind.setText(cityWeather.wind.speed + getString(R.string.speed_unit_mps));

        txtSunrise.setText(timeFormat.format(cityWeather.sys.sunrise));
        txtSunset.setText(timeFormat.format(cityWeather.sys.sunset));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnShare) {
            String shareContent = getString(R.string.pressure).concat(cityWeather.main.pressure + getString(R.string.setting_unit_hpa)) + "\n\n" +
                    getString(R.string.wind).concat(cityWeather.wind.speed + getString(R.string.speed_unit_mps));

            ShareCompat.IntentBuilder intentBuilder = ShareCompat.IntentBuilder.from(this);
            Intent intent = intentBuilder
                    .setType("text/plain")
                    .setText(shareContent)
                    .createChooserIntent();
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "No Client support this", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
