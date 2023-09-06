package com.example1.dairyease.ForgetPassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example1.dairyease.LoginActivity;
import com.example1.dairyease.ModelResponse.ForgetPasswordResponse;
import com.example1.dairyease.ModelResponse.LoginResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPassActivity extends AppCompatActivity {

    EditText FPemail;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);


        Toolbar toolbar = findViewById(R.id.toolbar);
        //step1(set up tool bar & link in java)
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        FPemail = findViewById(R.id.FPemail);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = FPemail.getText().toString().trim();

                if(email.isEmpty()){
                    Toast.makeText(ForgetPassActivity.this,"Field is Empty",Toast.LENGTH_SHORT).show();
                }else{
                    Call<ForgetPasswordResponse> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .sendOtp(email);

                    call.enqueue(new Callback<ForgetPasswordResponse>() {
                        @Override
                        public void onResponse(Call<ForgetPasswordResponse> call, Response<ForgetPasswordResponse> response) {

                            ForgetPasswordResponse forgetPasswordResponse = response.body();

                            if(response.isSuccessful() && forgetPasswordResponse != null){

                                String message = forgetPasswordResponse.getMessage();

                                if( forgetPasswordResponse.getStatus()==200 && "OTP sent successfully".equals(message)){
                                    Toast.makeText(ForgetPassActivity.this, forgetPasswordResponse.getMessage(),Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ForgetPassActivity.this,TokenActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else{
                                    Toast.makeText(ForgetPassActivity.this, message,Toast.LENGTH_SHORT).show();
                                }

                            }
                            else {
                                Toast.makeText(ForgetPassActivity.this, "Email Not Found", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ForgetPasswordResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        });




    }
}