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
import com.example1.dairyease.ModelResponse.ExpensesMorningResponse;
import com.example1.dairyease.R;

import java.util.List;

public class ExpensesMorningAdapter extends RecyclerView.Adapter<ExpensesMorningAdapter.ExpebsesMorningViewHolder> {

    Context context;
    List<ExpensesMorningResponse.ExpensesMorningList> expensesDataList;

    public ExpensesMorningAdapter(Context context, List<ExpensesMorningResponse.ExpensesMorningList> expensesDataList) {
        this.context = context;
        this.expensesDataList = expensesDataList;
    }

    @NonNull
    @Override
    public ExpensesMorningAdapter.ExpebsesMorningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_morning_recyclerview_design,parent,false);
        ExpebsesMorningViewHolder holder = new ExpebsesMorningViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesMorningAdapter.ExpebsesMorningViewHolder holder, int position) {


        if (position < expensesDataList.size()) {
            final ExpensesMorningResponse.ExpensesMorningList expensesMorningList = expensesDataList.get(position);
            holder.tvDATE.setText(expensesMorningList.getDate());
            holder.tvShift.setText(expensesMorningList.getShift());
            holder.tvProductName.setText(expensesMorningList.getProduct());
            holder.tvPerPrice.setText(String.valueOf(expensesMorningList.getTotal_price()));

            holder.expensesMorningRecyclerDesign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences sharedPreferences = context.getSharedPreferences("ExpensesData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Date", expensesMorningList.getDate());
                    editor.putString("Product_name", expensesMorningList.getProduct());
                    editor.putInt("Total_Price", expensesMorningList.getTotal_price());
                    editor.putInt("Quantity", expensesMorningList.getQuantity());
                    editor.putString("Shift", expensesMorningList.getShift());
                    editor.putString("Units", expensesMorningList.getUnit());
                    editor.putString("Per_Quantity", expensesMorningList.getPer_quantity());

                    editor.apply();


                    Intent i = new Intent(context, ExpensesStatementActivity.class);
                    context.startActivity(i);

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return expensesDataList.size();
    }

    public class ExpebsesMorningViewHolder extends RecyclerView.ViewHolder {

        TextView tvDATE,tvShift,tvProductName,tvPerPrice;

        LinearLayout expensesMorningRecyclerDesign;

        public ExpebsesMorningViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDATE =itemView.findViewById(R.id.tvDATE);
            tvShift = itemView.findViewById(R.id.tvShift);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvPerPrice = itemView.findViewById(R.id.tvPerPrice);

            expensesMorningRecyclerDesign = itemView.findViewById(R.id.expensesMorningRecyclerDesign);

        }
    }
}
