package com.example.networking_week11;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CitiesAdapter extends
        RecyclerView.Adapter<CitiesAdapter.TasksViewHolder> {

    interface citiesClickListener{
        public void cityClicked(String cityName);
    }
    citiesClickListener listener;
        private Context mCtx;
        public List<City> cityList;

        public CitiesAdapter(Context mCtx, List<City> cityList) {
            this.mCtx = mCtx;
            this.cityList = cityList;
            this.listener = (MainActivity)mCtx;

        }

        @Override
        public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_cities, parent, false);
            return new TasksViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TasksViewHolder holder, int position) {
            City t = cityList.get(position);
            holder.cityTextView.setText(t.getCityName());
        }

        @Override
        public int getItemCount() {
            return cityList.size();
        }

        class TasksViewHolder extends RecyclerView.ViewHolder
                implements
                View.OnClickListener
                {
            TextView cityTextView;

            public TasksViewHolder(View itemView) {
                super(itemView);
                cityTextView = itemView.findViewById(R.id.cityy);
                itemView.setOnClickListener(this);
                    }
                    @Override
                    public void onClick(View view) {
                        listener.cityClicked(cityList.get(getAdapterPosition()).getCityName());
                    }
                }


}
