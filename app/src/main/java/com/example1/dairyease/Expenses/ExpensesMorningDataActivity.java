package com.example1.dairyease.Expenses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example1.dairyease.ModelResponse.ExpensesMorningResponse;
import com.example1.dairyease.ModelResponse.ExpensesResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.Recycler.ExpensesMorningAdapter;
import com.example1.dairyease.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpensesMorningDataActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ExpensesMorningResponse.ExpensesMorningList> expensesDataList;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_morning_data);

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");
        context = this;
        recyclerView = findViewById(R.id.rvExpensesMorningDetail);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        Call<ExpensesMorningResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .fetchMorningExpenses("Bearer " + accessToken);
        call.enqueue(new Callback<ExpensesMorningResponse>() {
            @Override
            public void onResponse(Call<ExpensesMorningResponse> call, Response<ExpensesMorningResponse> response) {

                if(response.isSuccessful()){
                    expensesDataList = response.body().getData();
                    recyclerView.setAdapter(new ExpensesMorningAdapter(context,expensesDataList));
                } else {
                    Toast.makeText(getApplicationContext(),response.body().getMessag(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ExpensesMorningResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

                // t.printStackTrace();
            }
        });



        Toolbar toolbar = findViewById(R.id.toolbar);
        //step1(set up tool bar & link in java)
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}