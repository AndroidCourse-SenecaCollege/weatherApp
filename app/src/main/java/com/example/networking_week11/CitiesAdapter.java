package com.example.networking_week11;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.TasksViewHolder> {

    interface cityClickListner {
        public void cityClicked(City selectedCity);
    }
        private Context mCtx;
        private List<City> cityList;
        cityClickListner listner;

        public CitiesAdapter(Context mCtx, List<City> cityList) {
            this.mCtx = mCtx;
            this.cityList = cityList;
            listner = (cityClickListner)mCtx;
        }

        @Override
        public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_cities, parent, false);
            return new TasksViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TasksViewHolder holder, int position) {
            City t = cityList.get(position);
            holder.cityTextView.setText(t.getCityName() +": "+t.getCountry());


        }

        @Override
        public int getItemCount() {
            return cityList.size();
        }

        class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView cityTextView, countryTextView;

            public TasksViewHolder(View itemView) {
                super(itemView);

                cityTextView = itemView.findViewById(R.id.cityy);

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                City city = cityList.get(getAdapterPosition());
                listner.cityClicked(city);

            }
        }


}
