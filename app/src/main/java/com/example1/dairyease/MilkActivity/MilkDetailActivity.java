package com.example1.dairyease.MilkActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example1.dairyease.Customer_DashboardActivity;
import com.example1.dairyease.Expenses.ExpensesActivity;
import com.example1.dairyease.LogoutPopoutDialogActivity;
import com.example1.dairyease.ModelResponse.MilkResponse;
import com.example1.dairyease.Profile.ProfileActivity;
import com.example1.dairyease.R;
import com.example1.dairyease.Recycler.ExpensesAdapter;
import com.example1.dairyease.Recycler.MilkAdapter;
import com.example1.dairyease.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MilkDetailActivity extends AppCompatActivity {

    RecyclerView rvMilkDetail;
    List<MilkResponse.Data> milkResponseList;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk_detail);

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");


        //RecyclerView
        rvMilkDetail = findViewById(R.id.rvMilkDetail);
        rvMilkDetail.setLayoutManager(new LinearLayoutManager(context));

        Call<MilkResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getMilkList("Bearer " + accessToken);

        call.enqueue(new Callback<MilkResponse>() {
            @Override
            public void onResponse(Call<MilkResponse> call, Response<MilkResponse> response) {

                if(response.isSuccessful()){
                    milkResponseList = response.body().getData();
                    rvMilkDetail.setAdapter(new MilkAdapter(context,milkResponseList));
                    MilkAdapter adapter = new MilkAdapter(context,milkResponseList);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<MilkResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        BottomNavigationView bottom_navigationView = findViewById(R.id.bottom_navigationView);
        bottom_navigationView.setSelectedItemId(R.id.milk);

        bottom_navigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.Expenses) {
                    startActivity(new Intent(getApplicationContext(), ExpensesActivity.class));
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
                } else if(item.getItemId() == R.id.milk){
                    return true;
                }

                return false;
            }
        });

    }



}