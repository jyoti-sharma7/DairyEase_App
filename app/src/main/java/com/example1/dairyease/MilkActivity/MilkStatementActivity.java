package com.example1.dairyease.MilkActivity;

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

import com.example1.dairyease.ModelResponse.MilkResponse;
import com.example1.dairyease.ModelResponse.ProfileResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MilkStatementActivity extends AppCompatActivity {

    TextView tvBalanceAMOUNT,dateEnter,LiterEnter,FatEnter,snfEnter,AmountEnter;
    Button btn_fINISH;
    ImageView ivPdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk_statement);

        ivPdf = findViewById(R.id.ivPdf);

        Toolbar toolbar = findViewById(R.id.toolbar);
        btn_fINISH = findViewById(R.id.btn_fINISH);

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");

        Call<MilkResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getMilkList("Bearer " + accessToken);

        call.enqueue(new Callback<MilkResponse>() {
            @Override
            public void onResponse(Call<MilkResponse> call, Response<MilkResponse> response) {

                tvBalanceAMOUNT = findViewById(R.id.tvBalanceAMOUNT);
                dateEnter = findViewById(R.id.dateEnter);
                LiterEnter = findViewById(R.id.LiterEnter);
                FatEnter = findViewById(R.id.FatEnter);
                snfEnter = findViewById(R.id.snfEnter);
                AmountEnter = findViewById(R.id.AmountEnter);

                MilkResponse milkResponse = response.body();

                if(response.isSuccessful()){

                    SharedPreferences sharedPreferences2 = getSharedPreferences("MilkData", Context.MODE_PRIVATE);

                    int Balance = sharedPreferences2.getInt("balance",0);
                    String date = sharedPreferences2.getString("Date","");
                    String liter = sharedPreferences2.getString("liter","");
                    float perliter = sharedPreferences2.getFloat("perLiterAmt",0.0f);
                    float totalFat = sharedPreferences2.getFloat("totalFat",0.0f);
                    float totalSnf = sharedPreferences2.getFloat("totalSnf",0.0f);

                    tvBalanceAMOUNT.setText(""+Balance);
                    dateEnter.setText("" + date);
                    LiterEnter.setText("" + liter);
                    AmountEnter.setText("" + perliter);
                    FatEnter.setText("" + totalFat);
                    snfEnter.setText("" + totalSnf);

                } else {
                    Toast.makeText(getApplicationContext(),milkResponse.getMessage(),Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<MilkResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });




        ivPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDownloadConfirmationDialog();
            }
        });
        btn_fINISH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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
                Intent intent = new Intent(MilkStatementActivity.this,MilkPdfActivity.class);
                startActivity(intent);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

}