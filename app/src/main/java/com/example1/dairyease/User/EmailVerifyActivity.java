package com.example1.dairyease.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example1.dairyease.ModelResponse.VerifyOtpResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailVerifyActivity extends AppCompatActivity {

    EditText Enterotp;
    Button enter;
    TextView tvResend;
    TextView tvEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verify);

        tvEmail = findViewById(R.id.tvEmail);
        Enterotp = findViewById(R.id.Enterotp);
        tvResend = findViewById(R.id.tvResend);
        enter = findViewById(R.id.enter);

        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("userEmail");
        tvEmail.setText(userEmail);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = Enterotp.getText().toString().trim();

                if (TextUtils.isEmpty(otp)) {
                    Toast.makeText(EmailVerifyActivity.this, "Field is empty", Toast.LENGTH_SHORT).show();
                } else {
                    Call<VerifyOtpResponse> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .verifyemail(otp);
                    call.enqueue(new Callback<VerifyOtpResponse>() {
                        @Override
                        public void onResponse(Call<VerifyOtpResponse> call, Response<VerifyOtpResponse> response) {
                            VerifyOtpResponse verifyOtpResponse = response.body();
                            if (response.isSuccessful()) {
                                String message = verifyOtpResponse.getMessage();
                                if (message != null && message.equals("Email verified successfully.")) {
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid OTP", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<VerifyOtpResponse> call, Throwable t) {
                            t.printStackTrace();
                           // Toast.makeText(getApplicationContext(), "Invalid OTP", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}