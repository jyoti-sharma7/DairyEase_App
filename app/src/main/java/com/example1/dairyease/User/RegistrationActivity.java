package com.example1.dairyease.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example1.dairyease.ModelResponse.RegisterResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    EditText Reg_FName, Reg_Contact, Reg_Address, Reg_Mail, Reg_Pass, Reg_Con_Pass;
    Button btn_Rrgister;
    TextView GoTo_login;

    private ProgressDialog progressDialog;



   // @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_registration);

        progressDialog = new ProgressDialog(this);

            Reg_FName = findViewById(R.id.Reg_FName);
            Reg_Contact = findViewById(R.id.Reg_Contact);
            Reg_Address = findViewById(R.id.Reg_Address);
            Reg_Mail = findViewById(R.id.Reg_Mail);
            Reg_Pass = findViewById(R.id.Reg_Pass);
            Reg_Con_Pass = findViewById(R.id.Reg_Con_Pass);
            btn_Rrgister = findViewById(R.id.btn_Rrgister);
            GoTo_login = findViewById(R.id.GoTo_login);

            GoTo_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            });

            btn_Rrgister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = Reg_FName.getText().toString();
                    String contact = Reg_Contact.getText().toString().trim();
                    String address = Reg_Address.getText().toString();
                    String email = Reg_Mail.getText().toString().trim();
                    String password = Reg_Pass.getText().toString().trim();
                    String conformPass = Reg_Con_Pass.getText().toString().trim();

                    //fullname
                    if (name.isEmpty()) {
                        Reg_FName.setError("Field is Empty");
                        return;
                    }

                    //contact
                    if (contact.isEmpty()) {
                        Reg_Contact.setError("Field is Empty");
                        return;
                    } else if (contact.length() > 10) {
                        Reg_Contact.setError("Invalid Contact");
                        return;
                    }

                    //Address
                    if (address.isEmpty()) {
                        Reg_Address.setError("Field is Empty");
                        return;
                    }

                    //mail
                    if (email.isEmpty()) {
                        Reg_Mail.setError("Field is Empty");
                        return;
                    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Reg_Mail.setError("enter valid email address");
                        return;
                    }

                    //password
                    if (password.isEmpty()) {
                        Reg_Pass.setError("Field is Empty");
                        return;
                    } else if (password.length() < 8) {
                        Reg_Pass.setError("Password must be at least 8 char");
                        return;
                    }

                    //conf_password
                    if (conformPass.isEmpty()) {
                        Reg_Con_Pass.setError("Field is Empty");
                        return;
                    } else if (!password.equals(conformPass)) {
                        Toast.makeText(RegistrationActivity.this, "Password Does not match", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    getRegister();

                }
            });
        }

    private void getRegister() {

        String name = Reg_FName.getText().toString();
        String contact = Reg_Contact.getText().toString().trim();
        String address = Reg_Address.getText().toString();
        String email = Reg_Mail.getText().toString().trim();
        String password = Reg_Pass.getText().toString().trim();
        String conformPass = Reg_Con_Pass.getText().toString().trim();

        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.show();

        Call<RegisterResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .register(name, contact, address, email, password, conformPass);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    Toast.makeText(RegistrationActivity.this,registerResponse.getMessage() , Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RegistrationActivity.this, EmailVerifyActivity.class);
                    i.putExtra("userEmail",registerResponse.getUser());
                    startActivity(i);

                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                //t.printStackTrace();

                Toast.makeText(RegistrationActivity.this,"Email already register.", Toast.LENGTH_LONG).show();

            }
        });

    }

}
