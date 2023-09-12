package com.example1.dairyease.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example1.dairyease.Fragments.MainActivity;
import com.example1.dairyease.R;

public class SplashScreenActivity extends AppCompatActivity {

    Animation splashanim;
    ImageView ssimg;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false);

        if (isLoggedIn) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish(); // Optional: To prevent returning to this activity from the dashboard
        } else

        {
            setContentView(R.layout.activity_splash_screen);

            //hide actionbar
            //getSupportActionBar().hide();
            //hide statusbar
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);


            ssimg = findViewById(R.id.ssimg);

            //animation call
            splashanim = AnimationUtils.loadAnimation(this,R.anim.splashanim);
            //setting animation to the elements
            ssimg.setAnimation(splashanim);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 2000);

        }
    }
}