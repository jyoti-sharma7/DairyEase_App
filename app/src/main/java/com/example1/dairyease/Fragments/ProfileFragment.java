package com.example1.dairyease.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example1.dairyease.ModelResponse.ProfileData;
import com.example1.dairyease.ModelResponse.ProfileResponse;
import com.example1.dairyease.ModelResponse.UpdateProfileResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    // private final int GALLERY_REQ_CODE = 10;
    private Uri uri;
    Bitmap bitmap;

    TextView Profile_id;
    TextView Profile_name, Profile_Contact, Profile_Address, Profile_Mail;
    TextView UploadImg;
    Button btn_update;
    ImageView imgPROFILE;



    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Profile_id = view.findViewById(R.id.Profile_id);
        Profile_name = view.findViewById(R.id.Profile_name);
        Profile_Contact = view.findViewById(R.id.Profile_Contact);
        Profile_Address = view.findViewById(R.id.Profile_Address);
        Profile_Mail = view.findViewById(R.id.Profile_Mail);

        btn_update = view.findViewById(R.id.btn_update);
        UploadImg = view.findViewById(R.id.UploadImg);
        imgPROFILE = view.findViewById(R.id.imgPROFILE);


        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("TOKEN", "");

        // Load user profile data
        loadUserProfile(accessToken);

        // Initialize the ActivityResultLauncher for image selection
        ActivityResultLauncher<Intent> activityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == getActivity().RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), uri);
                                imgPROFILE.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        // Handle image selection
        UploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(iGallery);
            }
        });

        // Handle profile update
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfileImage(accessToken);
            }
        });

        return view;
    }

    private void loadUserProfile(String accessToken) {
        Call<ProfileResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getUserProfile("Bearer " + accessToken);

        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()) {
                    ProfileResponse profileResponse = response.body();
                    ProfileData profileData = profileResponse.getProfiledata();
                    // Set data to UI components
                    Profile_id.setText(String.valueOf(profileData.getId()));
                    Profile_name.setText(profileData.getName());
                    Profile_Contact.setText(profileData.getContact());
                    Profile_Address.setText(profileData.getAddress());
                    Profile_Mail.setText(profileData.getEmail());

                    Glide.with(requireActivity()).load(profileData.getProfile_image_url())
                            .into(imgPROFILE);


                    SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("ProfileData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Name", profileData.getName());
                    editor.putString("imageUrl",profileData.getProfile_image_url());
                    editor.apply();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateProfileImage(String accessToken) {
        ByteArrayOutputStream byteArrayOutputStream;
        byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            final String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);

            // Create a RequestBody from the byte array
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), bytes);

            // Create a MultipartBody.Part
            MultipartBody.Part imagePart = MultipartBody.Part.createFormData("profile_photo", "profile.jpg", requestBody);

            Call<UpdateProfileResponse> call1 = RetrofitClient
                    .getInstance()
                    .getApi().updateProfilePicture(imagePart, "Bearer " + accessToken);
            call1.enqueue(new Callback<UpdateProfileResponse>() {
                @Override
                public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                    if (response.isSuccessful()) {
                        UpdateProfileResponse updateProfileResponse = response.body();
                        if (updateProfileResponse != null) {
                            String message = updateProfileResponse.getMessage();
                            Log.d("ProfileFragment","Response Message: " + message);
                            if ("profile updated successfully".equals(message)) {
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                                // Reload user profile data after successful update
                                loadUserProfile(accessToken);
                            } else {
                                Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(requireActivity(), "Response body is null", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(requireActivity(), "Update failed. Please try again later.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                    Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(requireActivity(), "Image not selected!", Toast.LENGTH_LONG).show();
        }
    }
}