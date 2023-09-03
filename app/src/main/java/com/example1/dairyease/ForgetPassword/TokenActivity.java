package com.example1.dairyease.ForgetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example1.dairyease.ModelResponse.TokenOTPResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokenActivity extends AppCompatActivity {

    EditText Enterotp;
    Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);

        Enterotp = findViewById(R.id.Enterotp);

        enter = findViewById(R.id.enter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String otp = Enterotp.getText().toString().trim();

                Call<TokenOTPResponse> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .verifyOtp(otp);
                call.enqueue(new Callback<TokenOTPResponse>() {
                    @Override
                    public void onResponse(Call<TokenOTPResponse> call, Response<TokenOTPResponse> response) {
                        if(response.isSuccessful()){
                            TokenOTPResponse otpResponse = response.body();
                            Toast.makeText(getApplicationContext(), otpResponse.getMessage(),Toast.LENGTH_SHORT).show();

                            // OTP verified successfully, navigate to ResetPasswordActivity
                            Intent intent = new Intent(TokenActivity.this, NewpasswordActivity.class);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onFailure(Call<TokenOTPResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });


    }
}