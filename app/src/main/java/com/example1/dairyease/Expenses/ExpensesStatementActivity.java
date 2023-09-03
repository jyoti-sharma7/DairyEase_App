package com.example1.dairyease.Expenses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example1.dairyease.R;

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

        SharedPreferences sharedPreferences1 = getSharedPreferences("ExpensesData", Context.MODE_PRIVATE);

        String date = sharedPreferences1.getString("Date","");
        String Productname = sharedPreferences1.getString("Product_name","");
        Integer Total_Price = sharedPreferences1.getInt("Total_Price",0);
        Integer Quantity = sharedPreferences1.getInt("Quantity",0);
        String Shift = sharedPreferences1.getString("Shift","");
        String Units = sharedPreferences1.getString("Units","");
        String Per_Quantity = sharedPreferences1.getString("Per_Quantity","");

        tvDate.setText("Date" +date);
        balanceAMOUNT.setText("Total_Price"+Total_Price);
        ProductEnter.setText("Product_name"+ Productname);
        QuantityEnter.setText("Quantity"+Quantity);
        unitsEnter.setText("Units"+Units);
        AmountEnter.setText("AmountEnter"+Per_Quantity);
        tvShiftEnter.setText("Shift"+Shift);



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
                Intent i = new Intent(getApplicationContext(), ExpensespdfActivity.class);
                startActivity(i);
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