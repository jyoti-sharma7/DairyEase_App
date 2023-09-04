package com.example1.dairyease.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example1.dairyease.ModelResponse.ProfileData;
import com.example1.dairyease.ModelResponse.ProfileResponse;
import com.example1.dairyease.R;
import com.example1.dairyease.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    int REQUEST_CODE_SELECT_IMAGE = 0;
    TextView Profile_id;
    TextView Profile_name, Profile_Contact, Profile_Address, Profile_Mail;
    TextView UploadImg;
    Button btn_update;
    String path;
    ImageView imgPROFILE;

    public ProfileFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

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
                    editor.putString("imageUrl", profileData.getProfile_image_url());
                    editor.apply();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(requireActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
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

        return view;
    }

}
