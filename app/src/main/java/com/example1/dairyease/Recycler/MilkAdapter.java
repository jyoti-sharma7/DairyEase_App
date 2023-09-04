package com.example1.dairyease.Recycler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example1.dairyease.MilkActivity.MilkStatementActivity;
import com.example1.dairyease.ModelResponse.MilkResponse;
import com.example1.dairyease.R;

import java.util.List;

public class MilkAdapter extends RecyclerView.Adapter<MilkAdapter.MilkListViewHolder> {

    Context context;
    List<MilkResponse.Data> milkResponseList;

    public MilkAdapter(Context context, List<MilkResponse.Data> milkResponseList) {
        this.context = context;
        this.milkResponseList = milkResponseList;
    }

    @NonNull
    @Override
    public MilkAdapter.MilkListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_milk_details_design,parent,false);
        MilkListViewHolder holder = new MilkListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MilkAdapter.MilkListViewHolder holder, int position) {

        MilkResponse.Data milkData = milkResponseList.get(position);

        holder.tvMilkDate.setText(milkData.getDate());
        holder.tvMilkPrice.setText(String.valueOf(milkData.getBalance()));
        holder.tvshift.setText(milkData.getShift());

        holder.milkRecyclerDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = context.getSharedPreferences("MilkData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Date", milkData.getDate());
                editor.putString("liter", milkData.getLiter());
                editor.putInt("balance", milkData.getBalance());
                editor.putInt("perLiterAmt", milkData.getPer_liter_amt());
                editor.putInt("totalFat", milkData.getTotal_fat());
                editor.putInt("totalSnf", milkData.getTotal_snf());
                editor.putString("shift", milkData.getShift());

                editor.apply();

                Intent i = new Intent(context, MilkStatementActivity.class);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return milkResponseList.size();
    }

    public class MilkListViewHolder extends RecyclerView.ViewHolder {

        TextView tvMilkDate, tvMilkPrice, tvshift,tvRS;
        LinearLayout milkRecyclerDesign;


        public MilkListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMilkDate = itemView.findViewById(R.id.tvMilkDate);
            tvMilkPrice = itemView.findViewById(R.id.tvMilkPrice);
            tvshift = itemView.findViewById(R.id.tvshift);
            tvRS = itemView.findViewById(R.id.tvRS);
            milkRecyclerDesign = itemView.findViewById(R.id.milkRecyclerDesign);

        }
    }
}
