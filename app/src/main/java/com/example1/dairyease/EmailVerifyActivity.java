package com.example1.dairyease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example1.dairyease.ModelResponse.LoginResponse;
import com.example1.dairyease.ModelResponse.RegisterResponse;
import com.example1.dairyease.ModelResponse.VerifyOtpResponse;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailVerifyActivity extends AppCompatActivity {

    EditText Enterotp;
    Button enter;
    TextView tvResend;
    String tvEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verify);

        tvEmail = getIntent().getStringExtra("userEmail");
        Enterotp = findViewById(R.id.Enterotp);
        tvResend = findViewById(R.id.tvResend);
        enter = findViewById(R.id.enter);





        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String otp = Enterotp.getText().toString().trim();

                if(otp.equals("")){
                    Toast.makeText(EmailVerifyActivity.this,"Field is empty",Toast.LENGTH_SHORT).show();
                }else{
                    if (Enterotp.getError()==null){

                        Call<VerifyOtpResponse> call = RetrofitClient
                                .getInstance()
                                .getApi()
                                .verifyemail(otp);
                        call.enqueue(new Callback<VerifyOtpResponse>() {
                            @Override
                            public void onResponse(Call<VerifyOtpResponse> call, Response<VerifyOtpResponse> response) {
                                VerifyOtpResponse verifyOtpResponse = response.body();
                                if(response.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),verifyOtpResponse.getMessage(),Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                                    startActivity(i);
                                    finish();

                                } else {
                                    Toast.makeText(getApplicationContext(),verifyOtpResponse.getMessage(),Toast.LENGTH_SHORT).show();

                                }
                            }

                            @Override
                            public void onFailure(Call<VerifyOtpResponse> call, Throwable t) {
                                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                }

            }
        });


    }



}