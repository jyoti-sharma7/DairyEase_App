package com.example1.dairyease.Expenses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example1.dairyease.Customer_DashboardActivity;
import com.example1.dairyease.LogoutPopoutDialogActivity;
import com.example1.dairyease.MilkActivity.MilkDetailActivity;
import com.example1.dairyease.ModelResponse.ExpensesResponse;
import com.example1.dairyease.Profile.ProfileActivity;
import com.example1.dairyease.R;
import com.example1.dairyease.Recycler.ExpensesAdapter;
import com.example1.dairyease.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpensesActivity extends AppCompatActivity {

    RecyclerView rvExpensesDetail;
    List<ExpensesResponse.ExpensesData> expensesDataList;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);



        //recyclerView

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");

        rvExpensesDetail = findViewById(R.id.rvExpensesDetail);
        rvExpensesDetail.setLayoutManager(new LinearLayoutManager(context));

        Call<ExpensesResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getExpensesList("Bearer " + accessToken);

        call.enqueue(new Callback<ExpensesResponse>() {
            @Override
            public void onResponse(Call<ExpensesResponse> call, Response<ExpensesResponse> response) {

                if(response.isSuccessful()){

                    expensesDataList = response.body().getData();
                    rvExpensesDetail.setAdapter(new ExpensesAdapter(context,expensesDataList));

                }else {
                    Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ExpensesResponse> call, Throwable t) {

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
        //step2



        BottomNavigationView bottom_navigationView = findViewById(R.id.bottom_navigationView);
        bottom_navigationView.setSelectedItemId(R.id.Expenses);

        bottom_navigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.milk) {
                    startActivity(new Intent(getApplicationContext(), MilkDetailActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if(item.getItemId() == R.id.home){
                    startActivity(new Intent(getApplicationContext(), Customer_DashboardActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }else if(item.getItemId() == R.id.profile){
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }else if(item.getItemId() == R.id.logout){
                    startActivity(new Intent(getApplicationContext(), LogoutPopoutDialogActivity.class));
                    overridePendingTransition(0, 0);
                } else if(item.getItemId() == R.id.Expenses){
                    return true;
                }

                return false;
            }
        });






    }
}