package com.example1.dairyease.Product;

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
import com.example1.dairyease.ModelResponse.MilkResponse;
import com.example1.dairyease.ModelResponse.ProductList;
import com.example1.dairyease.ModelResponse.ProductResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView productImg;
    TextView productName,quantityAmt,perPrice,brandName,tvProductDiscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");


        Call<ProductResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getProducts("Bearer " + accessToken);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {


                ProductResponse productResponse = response.body();
                if (response.isSuccessful()){

                    productName = findViewById(R.id.productName);
                    quantityAmt = findViewById(R.id.quantityAmt);
                    perPrice = findViewById(R.id.perPrice);
                    brandName = findViewById(R.id.brandName);
                    tvProductDiscription = findViewById(R.id.tvProductDiscription);
                    productImg = findViewById(R.id.productImg);

                    SharedPreferences sharedPreferences2 = getSharedPreferences("ProductData", Context.MODE_PRIVATE);

                    String  ProductDiscription = sharedPreferences2.getString("ProductDiscription","");
                    String ProductName = sharedPreferences2.getString("ProductName","");
                    int QuantityAmt = sharedPreferences2.getInt("QuantityAmt",0);
                    String PerPrice = sharedPreferences2.getString("perPrice","");
                    String BrandName = sharedPreferences2.getString("BrandName","");
                    String imageUrl = sharedPreferences2.getString("imageUrl","");

                    productName.setText("" + ProductName);
                    quantityAmt.setText("" + QuantityAmt);
                    perPrice.setText("" + PerPrice);
                    brandName.setText("" + BrandName);
                    tvProductDiscription.setText("" + ProductDiscription);

                    if (!imageUrl.isEmpty()) {
                        Glide.with(ProductDetailActivity.this).load(imageUrl).into(productImg);
                    } else {
                        // Handle the case where imageUrl is empty or null
                        Log.e("ProductDetailActivity", "Image URL is empty or null");
                    }


                } else{
                    Toast.makeText(getApplicationContext(),productResponse.getMessage(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

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