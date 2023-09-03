package com.example1.dairyease.Profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example1.dairyease.Customer_DashboardActivity;
import com.example1.dairyease.Expenses.ExpensesActivity;
import com.example1.dairyease.LogoutPopoutDialogActivity;
import com.example1.dairyease.MilkActivity.MilkDetailActivity;
import com.example1.dairyease.ModelResponse.ProfileData;
import com.example1.dairyease.ModelResponse.ProfileResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    int REQUEST_CODE_SELECT_IMAGE = 0;
    TextView Profile_id;
    TextView Profile_name,Profile_Contact,Profile_Address,Profile_Mail;
    TextView UploadImg;
    Button btn_update;
    String path;
    ImageView imgPROFILE;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Profile_id = findViewById(R.id.Profile_id);
        Profile_name = findViewById(R.id.Profile_name);
        Profile_Contact = findViewById(R.id.Profile_Contact);
        Profile_Address = findViewById(R.id.Profile_Address);
        Profile_Mail = findViewById(R.id.Profile_Mail);
        btn_update = findViewById(R.id.btn_update);
        UploadImg = findViewById(R.id.UploadImg);
        imgPROFILE = findViewById(R.id.imgPROFILE);

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN","");


        Call<ProfileResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getUserProfile("Bearer " + accessToken);

        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {

                if(response.isSuccessful()){


                    ProfileResponse profileResponse = response.body();
                    ProfileData profileData = profileResponse.getProfiledata();
                    //set data to ui component
                    Profile_id.setText(String.valueOf(profileData.getId()));
                    Profile_name.setText(profileData.getName());
                    Profile_Contact.setText(profileData.getContact());
                    Profile_Address.setText(profileData.getAddress());
                    Profile_Mail.setText(profileData.getEmail());

                    Glide.with(ProfileActivity.this).load(profileData.getProfile_image_url())
                            .into(imgPROFILE);




                    SharedPreferences sharedPreferences = getSharedPreferences("ProfileData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Name", profileData.getName());
                    editor.putString("imageUrl",profileData.getProfile_image_url());
                    editor.apply();

                }


            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

                Toast.makeText(ProfileActivity.this, t.getMessage(),Toast.LENGTH_LONG).show();


            }
        });



        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Select image from mobile album
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
            }
        });







        Toolbar toolbar = findViewById(R.id.toolbar);
        //step1(set up tool bar & link in java)
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        BottomNavigationView bottom_navigationView = findViewById(R.id.bottom_navigationView);
        bottom_navigationView.setSelectedItemId(R.id.profile);

        bottom_navigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.milk) {
                    startActivity(new Intent(getApplicationContext(), MilkDetailActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if(item.getItemId() == R.id.home){
                    startActivity(new Intent(getApplicationContext(), Customer_DashboardActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }else if(item.getItemId() == R.id.Expenses){
                    startActivity(new Intent(getApplicationContext(), ExpensesActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }else if(item.getItemId() == R.id.logout){
                    startActivity(new Intent(getApplicationContext(), LogoutPopoutDialogActivity.class));
                    overridePendingTransition(0, 0);
                } else
                    if(item.getItemId() == R.id.profile){
                    return true;
                }

                return false;
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();

            // Convert URI to file path and create a RequestBody
            String imagePath = getPathFromUri(selectedImageUri);
            File imageFile = new File(imagePath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);

            // Create MultipartBody.Part and update the profile picture
            MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", imageFile.getName(), requestBody);


            Call<ResponseBody> updateCall = RetrofitClient
                    .getInstance()
                    .getApi()
                    .updateProfilePicture(imagePart);

            updateCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        // Profile picture updated successfully
                        Toast.makeText(ProfileActivity.this, "Profile picture updated successfully", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    // Handle network failure
                    Toast.makeText(ProfileActivity.this,  "Network error. Please try again later.", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    private String getPathFromUri(Uri selectedImageUri) {

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(selectedImageUri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(column_index);
        cursor.close();
        return filePath;


    }


}