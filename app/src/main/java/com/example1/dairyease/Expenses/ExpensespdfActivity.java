package com.example1.dairyease.Expenses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example1.dairyease.R;

public class ExpensespdfActivity extends AppCompatActivity {

    TextView dateview,tvProductName,quantityview,unitview,tvPerUnit,totalpriceview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expensespdf);

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");

        dateview = findViewById(R.id.dateview);
        tvProductName = findViewById(R.id.tvProductName);
        quantityview = findViewById(R.id.quantityview);
        unitview = findViewById(R.id.unitview);
        tvPerUnit = findViewById(R.id.tvPerUnit);
        totalpriceview = findViewById(R.id.totalpriceview);



        SharedPreferences sharedPreferences1 = getSharedPreferences("ExpensesData", Context.MODE_PRIVATE);

        String date = sharedPreferences1.getString("Date","");
        String Productname = sharedPreferences1.getString("Product_name","");
        Integer Total_Price = sharedPreferences1.getInt("Total_Price",0);
        Integer Quantity = sharedPreferences1.getInt("Quantity",0);
        //String Shift = sharedPreferences1.getString("Shift","");
        String Units = sharedPreferences1.getString("Units","");
        String Per_Quantity = sharedPreferences1.getString("Per_Quantity","");

        dateview.setText("" +date);
        totalpriceview.setText(""+Total_Price);
        tvProductName.setText(""+ Productname);
        unitview.setText(""+Quantity);
        quantityview.setText(""+Units);
        tvPerUnit.setText(""+Per_Quantity);
        //tvShiftEnter.setText("Shift"+Shift);




    }
}