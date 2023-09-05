package com.example1.dairyease.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example1.dairyease.LoginActivity;
import com.example1.dairyease.R;


public class LogOutFragment extends Fragment {

    Button logout_yes_button,logout_no_button;


    public LogOutFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_log_out, container, false);

        logout_yes_button = view.findViewById(R.id.logout_yes_button);
        logout_yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("is_logged_in", false);
                editor.remove("TOKEN");
                editor.apply();

                // Redirect to LoginActivity
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                requireActivity().finish();
            }
        });


        logout_no_button = view.findViewById(R.id.logout_no_button);
        logout_no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

        return view;


    }
}