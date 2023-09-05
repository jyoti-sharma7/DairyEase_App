package com.example1.dairyease.Expenses;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example1.dairyease.ModelResponse.ExpensesResponse;
import com.example1.dairyease.ModelResponse.MilkResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpensesStatementActivity extends AppCompatActivity {


    TextView balanceAMOUNT,tvShiftEnter,tvDate,ProductEnter,QuantityEnter,
            unitsEnter,AmountEnter;

    ImageView ivPDFexpenses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_statement);

        ivPDFexpenses = findViewById(R.id.ivPDFexpenses);
        balanceAMOUNT = findViewById(R.id.balanceAMOUNT);
        tvDate = findViewById(R.id.tvDate);
        ProductEnter = findViewById(R.id.ProductEnter);
        QuantityEnter = findViewById(R.id.QuantityEnter);
        unitsEnter = findViewById(R.id.unitsEnter);
        AmountEnter = findViewById(R.id.AmountEnter);
        tvShiftEnter = findViewById(R.id.tvShiftEnter);

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");


        Call<ExpensesResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getExpensesList("Bearer " + accessToken);

        call.enqueue(new Callback<ExpensesResponse>() {
            @Override
            public void onResponse(Call<ExpensesResponse> call, Response<ExpensesResponse> response) {
                ExpensesResponse expensesResponse = response.body();

                if(response.isSuccessful()){

                    SharedPreferences sharedPreferences1 = getSharedPreferences("ExpensesData", Context.MODE_PRIVATE);

                    String date = sharedPreferences1.getString("Date","");
                    String Productname = sharedPreferences1.getString("Product_name","");
                    Integer Total_Price = sharedPreferences1.getInt("Total_Price",0);
                    Integer Quantity = sharedPreferences1.getInt("Quantity",0);
                    String Shift = sharedPreferences1.getString("Shift","");
                    String Units = sharedPreferences1.getString("Units","");
                    String Per_Quantity = sharedPreferences1.getString("Per_Quantity","");

                    tvDate.setText("" +date);
                    balanceAMOUNT.setText(""+Total_Price);
                    ProductEnter.setText(""+ Productname);
                    QuantityEnter.setText(""+Quantity);
                    unitsEnter.setText(""+Units);
                    AmountEnter.setText(""+Per_Quantity);
                    tvShiftEnter.setText(""+Shift);

                }
                else{
                    Toast.makeText(getApplicationContext(),expensesResponse.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ExpensesResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });



        Button btn_fINISH = findViewById(R.id.btn_fINISH);
        btn_fINISH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        ivPDFexpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDownloadConfirmationDialog();
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

    private void showDownloadConfirmationDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Download Confirmation");
        builder.setMessage("DO you want to download this file?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ExpensesStatementActivity.this, ExpensespdfActivity.class);
                startActivity(intent);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}