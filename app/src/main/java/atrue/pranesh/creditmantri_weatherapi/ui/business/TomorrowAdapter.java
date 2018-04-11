package atrue.pranesh.creditmantri_weatherapi.ui.business;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import atrue.pranesh.creditmantri_weatherapi.MainActivity;
import atrue.pranesh.creditmantri_weatherapi.R;

/**
 * Created by Adminitrator on 4/10/2018.
 * Copyright IMDSTAR Technologies
 */

public class TomorrowAdapter<T> extends RecyclerView.Adapter {
    TomorrowHolder tomorrowHolder;
    private ArrayList<T> items;
    View.OnClickListener listener;
    MainActivity mainActivity;
    public TomorrowAdapter(List<T> tList, View.OnClickListener listener, MainActivity mainActivity){
        this.items= (ArrayList<T>) tList;
        this.listener=listener;
        this.mainActivity=mainActivity;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter,parent,false);
        tomorrowHolder=new TomorrowHolder(view,listener);
        return tomorrowHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        tomorrowHolder.onBind(items.get(position),mainActivity);
    }

    @Override
    public int getItemCount() {
        if(items!=null)
        return items.size();
        return 0;
    }
}
