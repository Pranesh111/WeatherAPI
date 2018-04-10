package atrue.pranesh.creditmantri_weatherapi.ui.business;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import atrue.pranesh.creditmantri_weatherapi.R;

/**
 * Created by Adminitrator on 4/10/2018.
 * Copyright IMDSTAR Technologies
 */

public class TomorrowAdapter<T> extends RecyclerView.Adapter {
    TomorrowHolder tomorrowHolder;
    private T items;
    View.OnClickListener listener;
    public TomorrowAdapter(T tList,View.OnClickListener listener){
        this.items=  tList;
        this.listener=listener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter,parent,false);
        tomorrowHolder=new TomorrowHolder(view,listener);
        return tomorrowHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        tomorrowHolder.onBind(items);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
