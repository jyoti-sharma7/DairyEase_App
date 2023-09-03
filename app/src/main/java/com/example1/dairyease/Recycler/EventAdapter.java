package com.example1.dairyease.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example1.dairyease.ModelResponse.EventData;
import com.example1.dairyease.ModelResponse.ProductList;
import com.example1.dairyease.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    Context context;
    List<EventData> eventDataList;

    public EventAdapter(Context context, List<EventData> eventDataList) {
        this.context = context;
        this.eventDataList = eventDataList;
    }

    @NonNull
    @Override
    public EventAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_event_design,parent,false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.EventViewHolder holder, int position) {

        if (eventDataList != null && position < eventDataList.size()) {
            holder.bind(eventDataList.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return eventDataList != null ? eventDataList.size() : 0;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        TextView tvDetail;
        ImageView ivEvent;
        LinearLayout llEventDesign;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDetail = itemView.findViewById(R.id.tvDetail);
            ivEvent = itemView.findViewById(R.id.ivEvent);
            llEventDesign = itemView.findViewById(R.id.llEventDesign);

        }

        public  void bind( EventData eventData ){

            tvDetail.setText(eventData.getEvent_description());

            Glide.with(context).load(eventData.getEvent_image_url()).into(ivEvent);



        }

    }
}
