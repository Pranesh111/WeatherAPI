package atrue.pranesh.creditmantri_weatherapi.ui.business;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;

import atrue.pranesh.creditmantri_weatherapi.MainActivity;
import atrue.pranesh.creditmantri_weatherapi.R;
import atrue.pranesh.creditmantri_weatherapi.helper.PreferenceKeys;
import atrue.pranesh.creditmantri_weatherapi.helper.PreferencesCredit;
import atrue.pranesh.creditmantri_weatherapi.model.CityWeather;
import atrue.pranesh.creditmantri_weatherapi.model.Forecast;

import static atrue.pranesh.creditmantri_weatherapi.ui.HomeFragment.getDeimalFormat;

/**
 * Created by Adminitrator on 4/10/2018.
 * Copyright IMDSTAR Technologies
 */

public class TomorrowHolder extends RecyclerView.ViewHolder {
    TextView txtMain, txtDesc, txtMax, txtMin, txtPres, txtHumi,txtDate;
    CardView cardView;
    View.OnClickListener listener;

    public TomorrowHolder(View itemView, View.OnClickListener listener) {
        super(itemView);
        this.listener = listener;
        cardView = itemView.findViewById(R.id.cardView);
        txtMain = itemView.findViewById(R.id.txtMain);
        txtDesc = itemView.findViewById(R.id.txtDesc);
        txtMax = itemView.findViewById(R.id.txtMax);
        txtMin = itemView.findViewById(R.id.txtMin);
        txtPres = itemView.findViewById(R.id.txtPres);
        txtHumi = itemView.findViewById(R.id.txtHumi);
        txtDate = itemView.findViewById(R.id.txtDate);
    }


    @SuppressLint("SetTextI18n")
    public <T> void onBind(T t, MainActivity mainActivity) {
        cardView.setOnClickListener(listener);
        cardView.setTag(R.id.cardView, t);
        String tempUnit = PreferencesCredit.getStringValue(mainActivity, PreferenceKeys.key_temp);
        if (t != null) {
            if (t instanceof Forecast.List) {
                txtMain.setText(((Forecast.List) t).weather.get(0).main);
                txtMain.setText(((Forecast.List) t).weather.get(0).description);
                if (!tempUnit.equals("K")) {
                    txtMin.setText(getDeimalFormat(((Forecast.List) t).main.temp_min - 273.15F)+ mainActivity.getString(R.string.celcius));
                    txtMax.setText(getDeimalFormat(((Forecast.List) t).main.temp_max - 273.15F)+ mainActivity.getString(R.string.celcius));
                    txtPres.setText(getDeimalFormat(((Forecast.List) t).main.pressure)+ mainActivity.getString(R.string.pressure_unit_hpa));
                    txtHumi.setText(getDeimalFormat(((Forecast.List) t).main.humidity)+" %");
                    txtDate.setText(((Forecast.List) t).dt_txt.split(" ")[0]);
                } else {
                    txtMin.setText(getDeimalFormat(((Forecast.List) t).main.temp_min)+ mainActivity.getString(R.string.celcius));
                    txtMax.setText(getDeimalFormat(((Forecast.List) t).main.temp_max) + mainActivity.getString(R.string.kelvin));
                    txtPres.setText(getDeimalFormat(((Forecast.List) t).main.pressure)+ mainActivity.getString(R.string.pressure_unit_hpa));
                    txtHumi.setText(getDeimalFormat(((Forecast.List) t).main.humidity)+" %");
                    txtDate.setText(((Forecast.List) t).dt_txt.split(" ")[0]);
                }
            } else {
                if (t instanceof CityWeather) {
                    DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(mainActivity);
                    if (((CityWeather) t).weather != null && ((CityWeather) t).weather.size() > 0) {
                        txtMain.setText(((CityWeather) t).weather.get(0).main);
                        txtDesc.setText(((CityWeather) t).weather.get(0).description);
                    }
                    if (((CityWeather) t).main != null) {
                        if (!tempUnit.equals("K")) {

                            txtMax.setText(getDeimalFormat(((CityWeather) t).main.temp_max - 273.15F)+ mainActivity.getString(R.string.celcius));
                            txtMin.setText(getDeimalFormat(((CityWeather) t).main.temp_min - 273.15F)+ mainActivity.getString(R.string.celcius));
                            txtPres.setText(getDeimalFormat(((CityWeather) t).main.pressure)+ mainActivity.getString(R.string.pressure_unit_hpa));
                            txtHumi.setText(getDeimalFormat(((CityWeather) t).main.humidity)+" %");
                            txtDate.setText(((CityWeather) t).dt);
                        } else {

                            txtMax.setText(getDeimalFormat(((CityWeather) t).main.temp_max)+ mainActivity.getString(R.string.celcius));
                            txtMin.setText(getDeimalFormat(((CityWeather) t).main.temp_min)+ mainActivity.getString(R.string.celcius));
                            txtPres.setText(((CityWeather) t).main.pressure+ mainActivity.getString(R.string.pressure_unit_hpa));
                            txtHumi.setText(((CityWeather) t).main.humidity+" %");
                            txtDate.setText(((CityWeather) t).dt);
                        }
                    }
                }
            }
        }
    }
}
