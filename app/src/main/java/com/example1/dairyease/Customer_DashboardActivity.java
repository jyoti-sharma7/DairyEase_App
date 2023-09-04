package com.example1.dairyease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example1.dairyease.Expenses.ExpensesActivity;
import com.example1.dairyease.MilkActivity.MilkDetailActivity;
import com.example1.dairyease.ModelResponse.CategoryData;
import com.example1.dairyease.ModelResponse.CategoryResponse;
import com.example1.dairyease.ModelResponse.DashBoardResponse;
import com.example1.dairyease.ModelResponse.EventData;
import com.example1.dairyease.ModelResponse.EventResponse;
import com.example1.dairyease.ModelResponse.ExpensesResponse;
import com.example1.dairyease.ModelResponse.LoginResponse;
import com.example1.dairyease.ModelResponse.ProductList;
import com.example1.dairyease.ModelResponse.ProductResponse;
import com.example1.dairyease.ModelResponse.ProfileData;
import com.example1.dairyease.ModelResponse.ProfileResponse;
import com.example1.dairyease.Profile.ProfileActivity;
import com.example1.dairyease.Recycler.CategoryAdapter;
import com.example1.dairyease.Recycler.EventAdapter;
import com.example1.dairyease.Recycler.ProductListAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Customer_DashboardActivity extends AppCompatActivity {

    RecyclerView RecyclerProductDetail,RecyclerEvents,rvCategory;
    LinearLayoutManager layoutManager;
    ProductListAdapter productListAdapter;
    CategoryAdapter categoryAdapter;
    EventAdapter eventAdapter;
    Context context;
    TextView greetingTextView,greet,perliterAMOUNT,totalmilkAMOUNT,balanceAMOUNT;
    Timer timer;
    ImageView imgCUSTOMER;

    List<ProductList> productLists;
    List<EventData> eventDataList;
    List<CategoryData> categoryDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);

        greetingTextView = findViewById(R.id.tvGoodMorning);
        productRecyclerView();
        eventRecycler();
        categoryRecycle();
        balanceData();

        // Schedule a task to update the greeting text periodically
        timer = new Timer();
        long delay = 0; // Start immediately
        long period = 1000 * 60 * 15; // Update every 15 minutes
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateGreetingText();
            }
        }, delay, period);



        //sharedPreferance load image and name from profile

       greet = findViewById(R.id.greet);
        imgCUSTOMER = findViewById(R.id.imgCUSTOMER);

        SharedPreferences sharedPreferences = getSharedPreferences("ProfileData", MODE_PRIVATE);
        String name =  sharedPreferences.getString("Name", "");
        String imageUrl = sharedPreferences.getString("imageUrl","");

        greet.setText(name);

        if (!imageUrl.isEmpty()) {
            Glide.with(this)
                    .load(imageUrl)
                    .into(imgCUSTOMER);
        } else {
            // Handle the case where imageUrl is empty or null
            Log.e("DashboardActivity", "Image URL is empty or null");
        }

        // end of sharedPreferance load image and name from profile

        BottomNavigationView bottom_navigationView = findViewById(R.id.bottom_navigationView);
        bottom_navigationView.setSelectedItemId(R.id.home);


        bottom_navigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.milk) {
                    startActivity(new Intent(getApplicationContext(), MilkDetailActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if(item.getItemId() == R.id.Expenses){
                    startActivity(new Intent(getApplicationContext(), ExpensesActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }else if(item.getItemId() == R.id.profile){
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }else if(item.getItemId() == R.id.logout){
                    startActivity(new Intent(getApplicationContext(), LogoutPopoutDialogActivity.class));
                    overridePendingTransition(0, 0);
                }else if(item.getItemId() == R.id.home){
                    return true;
                }

                return false;
            }
        });
    }

    private void balanceData() {

        perliterAMOUNT = findViewById(R.id.perliterAMOUNT);
        totalmilkAMOUNT = findViewById(R.id.totalmilkAMOUNT);
        balanceAMOUNT = findViewById(R.id.balanceAMOUNT);

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");


        Call<DashBoardResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getDashboardData("Bearer " + accessToken);

        call.enqueue(new Callback<DashBoardResponse>() {
            @Override
            public void onResponse(Call<DashBoardResponse> call, Response<DashBoardResponse> response) {
                if(response.isSuccessful()){

                   // balanceAMOUNT.setText(String.valueOf(DashBoardResponse.));

                    DashBoardResponse dashBoardResponse = response.body();
                    balanceAMOUNT.setText(String.valueOf(dashBoardResponse.getTotal_balance()));
                    perliterAMOUNT.setText(String.valueOf(dashBoardResponse.getPer_liter_amt()));
                    totalmilkAMOUNT.setText(dashBoardResponse.getTotal_milk());


                }
            }

            @Override
            public void onFailure(Call<DashBoardResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    private void eventRecycler() {

        RecyclerEvents = findViewById(R.id.RecyclerEvents);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        RecyclerEvents.setLayoutManager(layoutManager);
        // Initialize productLists as an empty ArrayList
        eventDataList = new ArrayList<>();

        eventAdapter = new EventAdapter (Customer_DashboardActivity.this,eventDataList);
        RecyclerEvents.setAdapter(eventAdapter);
        eventAdapter.notifyDataSetChanged();
        populateEvent();
    }

    private void populateEvent() {

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");

        Call<EventResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getEvents("Bearer " + accessToken);

        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if(response.isSuccessful()){
                    // Clear existing data and add the new data from the response
                    eventDataList.clear();
                    eventDataList.addAll(response.body().getData());
                    eventAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }

    private void categoryRecycle(){

        rvCategory = findViewById(R.id.rvCategory);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rvCategory.setLayoutManager(layoutManager);
        categoryDataList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(Customer_DashboardActivity.this,categoryDataList);
        rvCategory.setAdapter(categoryAdapter);
        populateCategory();
    }

    private void populateCategory(){
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");

        Call<CategoryResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getCategoryData("Bearer " + accessToken);

        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.isSuccessful()){
                    // Clear existing data and add the new data from the response
                    categoryDataList.clear();
                    categoryDataList.addAll(response.body().getData());
                    categoryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }



    private void updateGreetingText() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                String greeting;

                if (hour >= 0 && hour < 12) {
                    greeting = "GOOD MORNING";
                } else if (hour >= 12 && hour < 18) {
                    greeting = "GOOD AFTERNOON";
                } else {
                    greeting = "GOOD EVENING";
                }

                greetingTextView.setText(greeting);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop the timer when the activity is destroyed to prevent leaks
        if (timer != null) {
            timer.cancel();
        }
    }

    private void productRecyclerView() {

        RecyclerProductDetail = findViewById(R.id.RecyclerProductDetail);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        RecyclerProductDetail.setLayoutManager(layoutManager);

        // Initialize productLists as an empty ArrayList
        productLists = new ArrayList<>();

        productListAdapter = new ProductListAdapter (Customer_DashboardActivity.this,productLists);
        RecyclerProductDetail.setAdapter(productListAdapter);
        productListAdapter.notifyDataSetChanged();
        populateProducts();

    }

    public void populateProducts(){

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");

        Call<ProductResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getProducts("Bearer " + accessToken);

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    // Clear existing data and add the new data from the response
                    productLists.clear();
                    productLists.addAll(response.body().getData());
                    productListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }



}