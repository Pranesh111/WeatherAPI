package atrue.pranesh.creditmantri_weatherapi.ui.business;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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
    TextView txtMain, txtDesc, txtMax, txtMin, txtPres, txtHumi;
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
    }


    public <T> void onBind(T t, MainActivity mainActivity) {
        cardView.setOnClickListener(listener);
        cardView.setTag(R.id.cardView, t);
        String tempUnit = PreferencesCredit.getStringValue(mainActivity, PreferenceKeys.key_temp);
        if (t != null) {
            if (t instanceof Forecast.List) {
                txtMain.setText(((Forecast.List) t).weather.get(0).main);
                txtMain.setText(((Forecast.List) t).weather.get(0).description);
                if (!tempUnit.equals("K")) {
                    txtMin.setText(getDeimalFormat(((Forecast.List) t).main.temp_min - 273.15F));
                    txtMax.setText(getDeimalFormat(((Forecast.List) t).main.temp_max - 273.15F));
                    txtPres.setText(getDeimalFormat(((Forecast.List) t).main.pressure - 273.15F));
                    txtHumi.setText(getDeimalFormat(((Forecast.List) t).main.humidity - 273.15F));
                } else {
                    txtMin.setText(getDeimalFormat(((Forecast.List) t).main.temp_min));
                    txtMax.setText(getDeimalFormat(((Forecast.List) t).main.temp_max));
                    txtPres.setText(getDeimalFormat(((Forecast.List) t).main.pressure));
                    txtHumi.setText(getDeimalFormat(((Forecast.List) t).main.humidity));
                }
            } else {
                if (t instanceof CityWeather) {
                    if (((CityWeather) t).weather != null && ((CityWeather) t).weather.size() > 0) {
                        txtMain.setText(((CityWeather) t).weather.get(0).main);
                        txtDesc.setText(((CityWeather) t).weather.get(0).description);
                    }
                    if (((CityWeather) t).main != null) {
                        if (!tempUnit.equals("K")) {

                            txtMax.setText(getDeimalFormat(((CityWeather) t).main.temp_max - 273.15F));
                            txtMin.setText(getDeimalFormat(((CityWeather) t).main.temp_min - 273.15F));
                            txtPres.setText(getDeimalFormat(((CityWeather) t).main.pressure - 273.15F));
                            txtHumi.setText(getDeimalFormat(((CityWeather) t).main.humidity - 273.15F));
                        } else {

                            txtMax.setText(getDeimalFormat(((CityWeather) t).main.temp_max));
                            txtMin.setText(getDeimalFormat(((CityWeather) t).main.temp_min));
                            txtPres.setText(((CityWeather) t).main.pressure);
                            txtHumi.setText(((CityWeather) t).main.humidity);
                        }
                    }
                }
            }
        }
    }
}
