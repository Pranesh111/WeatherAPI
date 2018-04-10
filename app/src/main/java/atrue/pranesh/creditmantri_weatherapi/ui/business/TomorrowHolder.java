package atrue.pranesh.creditmantri_weatherapi.ui.business;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import atrue.pranesh.creditmantri_weatherapi.R;
import atrue.pranesh.creditmantri_weatherapi.model.CityWeather;
import atrue.pranesh.creditmantri_weatherapi.model.Forecast;

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


    public <T> void onBind(T t) {
        cardView.setOnClickListener(listener);
        cardView.setTag(R.id.cardView, t);
        if (t != null) {
            if (t instanceof Forecast) {
                txtMain.setText(((Forecast) t).list.get(0).weather.get(0).main);
                txtMain.setText(((Forecast) t).list.get(0).weather.get(0).description);
                txtMin.setText(((Forecast) t).list.get(0).main.temp_min + "");
                txtMax.setText(((Forecast) t).list.get(0).main.temp_max + "");
                txtPres.setText(((Forecast) t).list.get(0).main.pressure + "");
                txtHumi.setText(((Forecast) t).list.get(0).main.humidity + "");
            } else {
                if (t instanceof CityWeather) {
                    if (((CityWeather) t).weather != null && ((CityWeather) t).weather.size() > 0) {
                        txtMain.setText(((CityWeather) t).weather.get(0).main);
                        txtDesc.setText(((CityWeather) t).weather.get(0).description);
                    }
                    if (((CityWeather) t).main != null) {
                        txtMax.setText(((CityWeather) t).main.temp_max + "");
                        txtMin.setText(((CityWeather) t).main.temp_min+ "");
                        txtPres.setText(((CityWeather) t).main.pressure + "");
                        txtHumi.setText(((CityWeather) t).main.humidity + "");
                    }
                }
            }
        }
    }
}
