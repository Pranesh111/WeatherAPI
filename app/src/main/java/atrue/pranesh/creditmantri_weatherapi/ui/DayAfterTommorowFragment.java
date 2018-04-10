package atrue.pranesh.creditmantri_weatherapi.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import atrue.pranesh.creditmantri_weatherapi.MainActivity;
import atrue.pranesh.creditmantri_weatherapi.R;
import atrue.pranesh.creditmantri_weatherapi.model.Forecast;
import atrue.pranesh.creditmantri_weatherapi.ui.business.TomorrowAdapter;

/**
 * Created by Adminitrator on 4/10/2018.
 * Copyright IMDSTAR Technologies
 */

public class DayAfterTommorowFragment extends Fragment implements View.OnClickListener{
    RecyclerView recyclerView;
    ProgressBar progress;
    Communicator communicator;
    private TomorrowAdapter tomorrowAdapter ;
    private List<Forecast.List> dayAfterTommorwLists    ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tmrw_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progress = view.findViewById(R.id.progress);
        recyclerView = view.findViewById(R.id.todayRecy);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        communicator = (Communicator) getActivity();

    }

    public void setAdapter(Bundle bundle){
        dayAfterTommorwLists= (List<Forecast.List>) bundle.getSerializable("dayaftertmrwweather");
        if(recyclerView!=null){
            progress.setVisibility(View.GONE);
            tomorrowAdapter = new TomorrowAdapter(dayAfterTommorwLists, this, (MainActivity) getActivity());
            recyclerView.setAdapter(tomorrowAdapter);
        }
    }

    @Override
    public void onClick(View view) {

    }
}
