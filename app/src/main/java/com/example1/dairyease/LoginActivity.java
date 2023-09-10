package com.example1.dairyease;

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

import com.example1.dairyease.ForgetPassword.ForgetPassActivity;
import com.example1.dairyease.ModelResponse.LoginResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    EditText login_email,login_password;
    TextView gotoRegister,ForgetPass;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        btn_login = findViewById(R.id.btn_login);
        ForgetPass = findViewById(R.id.ForgetPass);

        //forget pass
        ForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();

                Intent i = new Intent(LoginActivity.this, ForgetPassActivity.class);
                startActivity(i);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_email.getText().toString().trim();
                String password = login_password.getText().toString().trim();


                if(email.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this,"Field are empty",Toast.LENGTH_SHORT).show();
                }else{
                  getLogin();
                }
            }
        });

        //to go to registration activity
        gotoRegister = findViewById(R.id.gotoRegister);
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();

                Intent in = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(in);
            }
        });
    }

    private void getLogin() {

        String email = login_email.getText().toString().trim();
        String password = login_password.getText().toString().trim();

        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.show();


        if (login_email.getError()==null && login_password.getError()==null){
            Call<LoginResponse> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .login(email,password);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        LoginResponse loginResponse = response.body();
                        if (loginResponse != null) {
                            String message = loginResponse.getMessage();

                            if (loginResponse.getStatus() == 1 && "User logged in successfully".equals(message)) {
                                SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("is_logged_in", true);
                                editor.putString("TOKEN", loginResponse.getAccessToken());
                                editor.apply();

                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            } else if (loginResponse.getStatus() == 0 && "Password didn't match".equals(message)) {
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this, "Login failed: " + message, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Response body is null", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Handle non-successful response (e.g., login failed)
                        Toast.makeText(LoginActivity.this, "Password didn't match.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}