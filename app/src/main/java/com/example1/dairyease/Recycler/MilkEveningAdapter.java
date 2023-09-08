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
import com.example1.dairyease.ModelResponse.MilkResponse;
import com.example1.dairyease.R;

import java.util.List;

public class MilkEveningAdapter extends RecyclerView.Adapter<MilkEveningAdapter.MilkEveningViewHolder> {

   Context context;
   List<MilkEveningResponse.MilkEveningList> milkEveningLists;

    public MilkEveningAdapter(Context context, List<MilkEveningResponse.MilkEveningList> milkEveningLists) {
        this.context = context;
        this.milkEveningLists = milkEveningLists;
    }

    @NonNull
    @Override
    public MilkEveningAdapter.MilkEveningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evening_milk_design,parent,false);
        MilkEveningViewHolder holder = new MilkEveningViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MilkEveningAdapter.MilkEveningViewHolder holder, int position) {

        if (position < milkEveningLists.size()) {

            final MilkEveningResponse.MilkEveningList milkEveningList = milkEveningLists.get(position);

            holder.tvMilkDate.setText(milkEveningList.getDate());
            holder.tvMilkPrice.setText(String.valueOf(milkEveningList.getBalance()));
            holder.tvshift.setText(milkEveningList.getShift());


            holder.eveningMilkRecyclerDesign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences sharedPreferences = context.getSharedPreferences("MilkData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Date", milkEveningList.getDate());
                    editor.putString("liter", milkEveningList.getLiter());
                    editor.putString("balance", milkEveningList.getBalance());
                    editor.putString("perLiterAmt", milkEveningList.getPer_liter_amt());
                    editor.putString("totalFat", milkEveningList.getFat_rate());
                    editor.putString("totalSnf", milkEveningList.getSnf_rate());
                    editor.putString("shift", milkEveningList.getShift());

                    editor.apply();

                    Intent i = new Intent(context, MilkStatementActivity.class);
                    context.startActivity(i);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return milkEveningLists.size();
    }

    public class MilkEveningViewHolder extends RecyclerView.ViewHolder {

        TextView tvMilkDate, tvMilkPrice, tvshift,tvRS;
        LinearLayout eveningMilkRecyclerDesign;

        public MilkEveningViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMilkDate = itemView.findViewById(R.id.tvMilkDate);
            tvMilkPrice = itemView.findViewById(R.id.tvMilkPrice);
            tvshift = itemView.findViewById(R.id.tvshift);
            tvRS = itemView.findViewById(R.id.tvRS);
            eveningMilkRecyclerDesign = itemView.findViewById(R.id.eveningMilkRecyclerDesign);
        }
    }
}
