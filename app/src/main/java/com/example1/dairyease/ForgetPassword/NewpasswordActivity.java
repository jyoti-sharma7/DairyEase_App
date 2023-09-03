package com.example1.dairyease.ForgetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example1.dairyease.LoginActivity;
import com.example1.dairyease.ModelResponse.NewPasswordResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewpasswordActivity extends AppCompatActivity {

    EditText RPconformpass,RPpassword,RPemail;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpassword);

        RPconformpass = findViewById(R.id.RPconformpass);
        RPpassword = findViewById(R.id.RPpassword);
        //RPemail = findViewById(R.id.RPemail);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // String email = RPemail.getText().toString().trim();
                String password = RPpassword.getText().toString().trim();
                String password_confirmation = RPconformpass.getText().toString().trim();

                if(password.equals(password_confirmation)){

                    Call<NewPasswordResponse> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .resetPassword(password,password_confirmation);

                    call.enqueue(new Callback<NewPasswordResponse>() {
                        @Override
                        public void onResponse(Call<NewPasswordResponse> call, Response<NewPasswordResponse> response) {
                            if (response.isSuccessful()) {

                                NewPasswordResponse newPasswordResponse = response.body();
                                if (response.isSuccessful()) {

                                    Toast.makeText(getApplicationContext(), newPasswordResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(NewpasswordActivity.this, LoginActivity.class);
                                    startActivity(i);
                                    finish();

                                }
                            }


                        }

                        @Override
                        public void onFailure(Call<NewPasswordResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        });

                }
            }
        });

    }
}