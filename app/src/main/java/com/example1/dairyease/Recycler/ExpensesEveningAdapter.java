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
import com.example1.dairyease.ModelResponse.ExpensesEveningResponse;
import com.example1.dairyease.ModelResponse.ExpensesMorningResponse;
import com.example1.dairyease.R;

import java.util.List;

public class ExpensesEveningAdapter extends RecyclerView.Adapter<ExpensesEveningAdapter.ExpensesEveningViewHolder> {

    Context context;
    List<ExpensesEveningResponse.ExpensesEveningList> expensesEveningListList;

    public ExpensesEveningAdapter(Context context, List<ExpensesEveningResponse.ExpensesEveningList> expensesEveningListList) {
        this.context = context;
        this.expensesEveningListList = expensesEveningListList;
    }

    @NonNull
    @Override
    public ExpensesEveningAdapter.ExpensesEveningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evening_expenses_design,parent,false);
       ExpensesEveningViewHolder holder = new ExpensesEveningViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesEveningAdapter.ExpensesEveningViewHolder holder, int position) {

        if (position < expensesEveningListList.size()) {
            final ExpensesEveningResponse.ExpensesEveningList expensesEveningList = expensesEveningListList.get(position);
            holder.tvDATE.setText(expensesEveningList.getDate());
            holder.tvShift.setText(expensesEveningList.getShift());
            holder.tvProductName.setText(expensesEveningList.getProduct());
            holder.tvPerPrice.setText(String.valueOf(expensesEveningList.getTotal_price()));

            holder.expensesEveningRecyclerDesign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences sharedPreferences = context.getSharedPreferences("ExpensesData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Date", expensesEveningList.getDate());
                    editor.putString("Product_name", expensesEveningList.getProduct());
                    editor.putInt("Total_Price", expensesEveningList.getTotal_price());
                    editor.putInt("Quantity", expensesEveningList.getQuantity());
                    editor.putString("Shift", expensesEveningList.getShift());
                    editor.putString("Units", expensesEveningList.getUnit());
                    editor.putString("Per_Quantity", expensesEveningList.getPer_quantity());

                    editor.apply();


                    Intent i = new Intent(context, ExpensesStatementActivity.class);
                    context.startActivity(i);

                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return expensesEveningListList.size();
    }

    public class ExpensesEveningViewHolder extends RecyclerView.ViewHolder {

        TextView tvDATE,tvShift,tvProductName,tvPerPrice;

        LinearLayout expensesEveningRecyclerDesign;

        public ExpensesEveningViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDATE =itemView.findViewById(R.id.tvDATE);
            tvShift = itemView.findViewById(R.id.tvShift);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvPerPrice = itemView.findViewById(R.id.tvPerPrice);

            expensesEveningRecyclerDesign = itemView.findViewById(R.id.expensesEveningRecyclerDesign);

        }
    }
}
