package com.example1.dairyease.ForgetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example1.dairyease.EmailVerifyActivity;
import com.example1.dairyease.ModelResponse.TokenOTPResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokenActivity extends AppCompatActivity {

    EditText Enterotp;
    Button enter;
    TextView tvEmail;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);

        Enterotp = findViewById(R.id.Enterotp);
        enter = findViewById(R.id.enter);
        tvEmail = findViewById(R.id.tvEmail);

        progressDialog = new ProgressDialog(this);

        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("email");
        tvEmail.setText(userEmail);


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String otp = Enterotp.getText().toString().trim();

                if (otp.isEmpty()) {
                    // Field is empty, show a message
                    Toast.makeText(TokenActivity.this, "The field is empty", Toast.LENGTH_SHORT).show();
                } else {
                    // Field is not empty, proceed with OTP verification
                   getOTP();
                }
            }
        });
    }

    private void getOTP() {

        String otp = Enterotp.getText().toString().trim();

        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.show();

        Call<TokenOTPResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .verifyOtp(otp);
        call.enqueue(new Callback<TokenOTPResponse>() {
            @Override
            public void onResponse(Call<TokenOTPResponse> call, Response<TokenOTPResponse> response) {
                TokenOTPResponse otpResponse = response.body();

                if (response.isSuccessful() && otpResponse != null) {
                    if (otpResponse.getStatus() == 200) {
                        // OTP verification was successful, navigate to NewpasswordActivity
                        Toast.makeText(getApplicationContext(), "OTP Verification Successful", Toast.LENGTH_SHORT).show();

                        SharedPreferences sharedPreferences = getSharedPreferences("otp_token", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("TOKEN", otpResponse.getToken());
                        editor.apply();

                        Intent i = new Intent(TokenActivity.this, NewpasswordActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        // OTP verification failed, show a message
                        Toast.makeText(getApplicationContext(), "OTP Invalid", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Handle other response statuses or errors
                    Toast.makeText(getApplicationContext(), "Error in OTP Verification", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TokenOTPResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}