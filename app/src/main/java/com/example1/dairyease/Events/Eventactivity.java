package com.example1.dairyease.Events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example1.dairyease.ModelResponse.EventResponse;
import com.example1.dairyease.Product.ProductDetailActivity;
import com.example1.dairyease.R;
import com.example1.dairyease.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Eventactivity extends AppCompatActivity {

    TextView tvDescription,tvTitle,tvVenue,tvdate;
    ImageView ivEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventactivity);

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");


        Call<EventResponse> call = RetrofitClient.getInstance().getApi().getEvents("Bearer " + accessToken);
        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                EventResponse eventResponse = response.body();
                if(response.isSuccessful()){

                    tvDescription = findViewById(R.id.tvDescription);
                    tvTitle = findViewById(R.id.tvTitle);
                    tvVenue = findViewById(R.id.tvVenue);
                    tvdate = findViewById(R.id.tvdate);
                    ivEvent = findViewById(R.id.ivEvent);

                    SharedPreferences sharedPreferences2 = getSharedPreferences("EventData", Context.MODE_PRIVATE);
                    String date = sharedPreferences2.getString("Date","");
                    String title = sharedPreferences2.getString("Title","");
                    String description = sharedPreferences2.getString("Description","");
                    String venue = sharedPreferences2.getString("Venue","");
                    String imageUrl = sharedPreferences2.getString("Image","");

                    tvDescription.setText("" + description);
                    tvdate.setText("" + date);
                    tvTitle.setText("" + title);
                    tvVenue.setText("" + venue);

                    if (!imageUrl.isEmpty()) {
                        Glide.with(Eventactivity.this).load(imageUrl).into(ivEvent);
                    } else {
                        // Handle the case where imageUrl is empty or null
                        Log.e("ProductDetailActivity", "Image URL is empty or null");
                    }


                }
                else {
                    Toast.makeText(getApplicationContext(),eventResponse.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                t.printStackTrace();
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