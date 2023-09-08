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
import com.example1.dairyease.ModelResponse.MilkEveningResponse;
import com.example1.dairyease.ModelResponse.MilkMorningResponse;
import com.example1.dairyease.R;

import java.util.List;

public class MilkMorningAdapter extends RecyclerView.Adapter<MilkMorningAdapter.MilkMorningViewHolder> {

    Context context;
    List<MilkMorningResponse.MilkMorningList> milkMorningLists;

    public MilkMorningAdapter(Context context, List<MilkMorningResponse.MilkMorningList> milkMorningLists) {
        this.context = context;
        this.milkMorningLists = milkMorningLists;
    }

    @NonNull
    @Override
    public MilkMorningAdapter.MilkMorningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_morning_milk_design,parent,false);
        MilkMorningViewHolder holder = new MilkMorningViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MilkMorningAdapter.MilkMorningViewHolder holder, int position) {

        if (position < milkMorningLists.size()) {

            final MilkMorningResponse.MilkMorningList milkMorningList = milkMorningLists.get(position);

            holder.tvMilkDate.setText(milkMorningList.getDate());
            holder.tvMilkPrice.setText(String.valueOf(milkMorningList.getBalance()));
            holder.tvshift.setText(milkMorningList.getShift());


            holder.morningMilkRecyclerDesign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences sharedPreferences = context.getSharedPreferences("MilkData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Date", milkMorningList.getDate());
                    editor.putString("liter", milkMorningList.getLiter());
                    editor.putString("balance", milkMorningList.getBalance());
                    editor.putString("perLiterAmt", milkMorningList.getPer_liter_amt());
                    editor.putString("totalFat", milkMorningList.getFat_rate());
                    editor.putString("totalSnf", milkMorningList.getSnf_rate());
                    editor.putString("shift", milkMorningList.getShift());

                    editor.apply();

                    Intent i = new Intent(context, MilkStatementActivity.class);
                    context.startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return milkMorningLists.size();
    }

    public class MilkMorningViewHolder extends RecyclerView.ViewHolder {

        TextView tvMilkDate, tvMilkPrice, tvshift,tvRS;
        LinearLayout morningMilkRecyclerDesign;

        public MilkMorningViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMilkDate = itemView.findViewById(R.id.tvMilkDate);
            tvMilkPrice = itemView.findViewById(R.id.tvMilkPrice);
            tvshift = itemView.findViewById(R.id.tvshift);
            tvRS = itemView.findViewById(R.id.tvRS);
            morningMilkRecyclerDesign = itemView.findViewById(R.id.morningMilkRecyclerDesign);
        }
    }
}
