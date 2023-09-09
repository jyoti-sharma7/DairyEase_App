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

import com.example1.dairyease.Expenses.ExpensesStatementActivity;
import com.example1.dairyease.ModelResponse.ExpensesResponse;
import com.example1.dairyease.R;

import java.util.List;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ExpensesViewHolder> {

    Context context;
    List<ExpensesResponse.ExpensesData> expensesDataList;

    public ExpensesAdapter(Context context, List<ExpensesResponse.ExpensesData> expensesDataList) {
        this.context = context;
        this.expensesDataList = expensesDataList;
    }

    @NonNull
    @Override
    public ExpensesAdapter.ExpensesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expenses_recycler,parent,false);
        ExpensesViewHolder holder = new ExpensesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesAdapter.ExpensesViewHolder holder, int position) {

        if (position < expensesDataList.size()) {
            final ExpensesResponse.ExpensesData expensesData = expensesDataList.get(position);
            holder.tvDATE.setText(expensesData.getDate());
            holder.tvShift.setText(expensesData.getShift());
            holder.tvProductName.setText(expensesData.getProduct());
            holder.tvPerPrice.setText(String.valueOf(expensesData.getTotal_price()));


            holder.expensesRecyclerDesign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences sharedPreferences = context.getSharedPreferences("ExpensesData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Date", expensesData.getDate());
                    editor.putString("Product_name", expensesData.getProduct());
                    editor.putInt("Total_Price", expensesData.getTotal_price());
                    editor.putInt("Quantity", expensesData.getQuantity());
                    editor.putString("Shift", expensesData.getShift());
                    editor.putString("Units", expensesData.getUnit());
                    editor.putString("Per_Quantity", expensesData.getPer_quantity());

                    editor.apply();


                    Intent i = new Intent(context, ExpensesStatementActivity.class);
                    context.startActivity(i);

                }
            });
        }    }

    @Override
    public int getItemCount() {
        return expensesDataList.size();
    }

    public class ExpensesViewHolder extends RecyclerView.ViewHolder {


        TextView tvDATE,tvShift,tvProductName,tvPerPrice;

        LinearLayout expensesRecyclerDesign;
        public ExpensesViewHolder(@NonNull View itemView) {

            super(itemView);

            tvDATE =itemView.findViewById(R.id.tvDATE);
            tvShift = itemView.findViewById(R.id.tvShift);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvPerPrice = itemView.findViewById(R.id.tvPerPrice);

            expensesRecyclerDesign = itemView.findViewById(R.id.expensesRecyclerDesign);


        }
    }
}
