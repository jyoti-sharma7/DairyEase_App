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

import com.example1.dairyease.ModelResponse.ExpensesEveningResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.Recycler.ExpensesEveningAdapter;
import com.example1.dairyease.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpensesEveningDataActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ExpensesEveningResponse.ExpensesEveningList> expensesEveningListList;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_evening_data);

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");
        context = this;

        recyclerView = findViewById(R.id.rvExpensesEveningDetail);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        Call<ExpensesEveningResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .fetchEveningExpenses("Bearer " + accessToken);
        call.enqueue(new Callback<ExpensesEveningResponse>() {
            @Override
            public void onResponse(Call<ExpensesEveningResponse> call, Response<ExpensesEveningResponse> response) {
                if(response.isSuccessful()){
                    expensesEveningListList = response.body().getData();
                    recyclerView.setAdapter(new ExpensesEveningAdapter(context,expensesEveningListList));
                } else {
                    Toast.makeText(getApplicationContext(),response.body().getMessag(),Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<ExpensesEveningResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

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