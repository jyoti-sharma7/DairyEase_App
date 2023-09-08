package com.example1.dairyease.MilkActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example1.dairyease.ModelResponse.MilkEveningResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.Recycler.MilkEveningAdapter;
import com.example1.dairyease.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MilkEveningActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<MilkEveningResponse.MilkEveningList> milkEveningLists;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk_evening);

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");
        context = this;

        recyclerView = findViewById(R.id.rvMilkEveningDetail);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        Call<MilkEveningResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .fetchEveningData("Bearer " + accessToken);
        call.enqueue(new Callback<MilkEveningResponse>() {
            @Override
            public void onResponse(Call<MilkEveningResponse> call, Response<MilkEveningResponse> response) {
                if(response.isSuccessful()){
                    milkEveningLists = response.body().getData();
                    recyclerView.setAdapter(new MilkEveningAdapter(context,milkEveningLists));
                }else{
                    Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<MilkEveningResponse> call, Throwable t) {
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