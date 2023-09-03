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

import com.example1.dairyease.ForgetPassword.ForgetPassActivity;
import com.example1.dairyease.ModelResponse.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    EditText login_email,login_password;
    TextView gotoRegister,ForgetPass;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        btn_login = findViewById(R.id.btn_login);
        ForgetPass = findViewById(R.id.ForgetPass);



        //forget pass
        ForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ForgetPassActivity.class);
                startActivity(i);
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_email.getText().toString();
                String password = login_password.getText().toString();

                if(email.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this,"Field are empty",Toast.LENGTH_SHORT).show();
                }else{
                    if (login_email.getError()==null && login_password.getError()==null){
                        Call<LoginResponse> call = RetrofitClient
                                .getInstance()
                                .getApi()
                                .login(email,password);

                        call.enqueue(new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response)
                            {
                              LoginResponse loginResponse = response.body();
                                if(response.isSuccessful()){


                                    SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean("is_logged_in", true);
                                    editor.putString("TOKEN",loginResponse.getAccessToken());
                                    editor.apply();

                                    Toast.makeText(LoginActivity.this, loginResponse.getMessage(),Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(LoginActivity.this, Customer_DashboardActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Error!! Try Again.",Toast.LENGTH_SHORT).show();
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
        });

        //to go to registration activity
        gotoRegister = findViewById(R.id.gotoRegister);
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(in);
            }
        });
        }
}