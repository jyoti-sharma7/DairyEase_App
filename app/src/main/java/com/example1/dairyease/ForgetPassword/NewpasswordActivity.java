package com.example1.dairyease.ForgetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example1.dairyease.LoginActivity;
import com.example1.dairyease.ModelResponse.NewPasswordResponse;
import com.example1.dairyease.ModelResponse.TokenOTPResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewpasswordActivity extends AppCompatActivity {

    EditText RPconformpass, RPpassword;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpassword);

        RPconformpass = findViewById(R.id.RPconformpass);
        RPpassword = findViewById(R.id.RPpassword);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = RPpassword.getText().toString().trim();
                String password_confirmation = RPconformpass.getText().toString().trim();

                if (password.isEmpty() || password_confirmation.isEmpty()) {
                    Toast.makeText(NewpasswordActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 8) {
                    Toast.makeText(NewpasswordActivity.this, "Password must be at least 8 characters", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(password_confirmation)) {
                    Toast.makeText(NewpasswordActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else if(password.equals(password_confirmation)) {
                    SharedPreferences sharedPreferences = getSharedPreferences("otp_token", MODE_PRIVATE);
                    String accessToken = sharedPreferences.getString("TOKEN", "");

                    Call<NewPasswordResponse> callNew = RetrofitClient
                            .getInstance()
                            .getApi()
                            .resetPassword(password, password_confirmation,"Bearer " + accessToken);

                    callNew.enqueue(new Callback<NewPasswordResponse>() {
                        @Override
                        public void onResponse(Call<NewPasswordResponse> call, Response<NewPasswordResponse> response) {
                            if (response.isSuccessful()) {
                                NewPasswordResponse newPasswordResponse = response.body();
                                String message = newPasswordResponse.getMessage();
                                if (newPasswordResponse != null && "Successfully Set New Password".equals(message) && newPasswordResponse.getStatus()==200) {
                                    Toast.makeText(NewpasswordActivity.this, newPasswordResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(NewpasswordActivity.this, LoginActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(NewpasswordActivity.this, "Response body is null", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                // Handle non-successful response (e.g., login failed)
                                if (response.errorBody() != null) {
                                    try {
                                        String errorBody = response.errorBody().string();
                                        // You can parse the error message from the errorBody if available
                                        Toast.makeText(NewpasswordActivity.this, errorBody, Toast.LENGTH_LONG).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Toast.makeText(NewpasswordActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<NewPasswordResponse> call, Throwable t) {
                            Toast.makeText(NewpasswordActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}