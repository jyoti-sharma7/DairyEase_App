package com.example1.dairyease.MilkActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example1.dairyease.R;

public class MilkPdfActivity extends AppCompatActivity {

    TextView dateview,literview,fatview,snfview,perliterview,totalpriceview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk_pdf);

        dateview = findViewById(R.id.dateview);
        literview = findViewById(R.id.literview);
        fatview = findViewById(R.id.fatview);
        snfview = findViewById(R.id.snfview);
        perliterview = findViewById(R.id.perliterview);
        totalpriceview = findViewById(R.id.totalpriceview);

        SharedPreferences sharedPreferences2 = getSharedPreferences("MilkData", Context.MODE_PRIVATE);

        int Balance = sharedPreferences2.getInt("balance",0);
        String date = sharedPreferences2.getString("Date","");
        String liter = sharedPreferences2.getString("liter","");
        float perliter = sharedPreferences2.getFloat("perLiterAmt",0.0f);
        String totalFat = sharedPreferences2.getString("totalFat","");
        String totalSnf = sharedPreferences2.getString("totalSnf","");

        totalpriceview.setText(""+Balance);
        dateview.setText("" + date);
        literview.setText("" + liter);
        perliterview.setText("" + perliter);
        fatview.setText("" + totalFat);
        snfview.setText("" + totalSnf);



    }
}