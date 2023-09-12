package com.example1.dairyease.Fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example1.dairyease.Fragments.DashboardFragment;
import com.example1.dairyease.Fragments.ExpensesFragment;
import com.example1.dairyease.Fragments.LogOutFragment;
import com.example1.dairyease.Fragments.MilkFragment;
import com.example1.dairyease.Fragments.ProfileFragment;
import com.example1.dairyease.R;
import com.example1.dairyease.User.LogoutPopoutDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    DashboardFragment dashboardFragment = new DashboardFragment();
    MilkFragment milkFragment = new MilkFragment();
    ExpensesFragment expensesFragment = new ExpensesFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    LogOutFragment logOutFragment = new LogOutFragment();


    private void showLogoutPopoutDialog(){
        LogoutPopoutDialog dialog = new LogoutPopoutDialog(this);
        dialog.show();
    }

    @Override
    public void onBackPressed() {

        if(bottomNavigationView.getSelectedItemId()== R.id.home){
            super.onBackPressed();
            finish();
        }else {
            bottomNavigationView.setSelectedItemId(R.id.home);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,dashboardFragment).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, dashboardFragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.milk) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, milkFragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.Expenses) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, expensesFragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.profile) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.logout) {
                    // getSupportFragmentManager().beginTransaction().replace(R.id.container, logOutFragment).commit();

                    showLogoutPopoutDialog(); // Show the logout popup dialog
                    return true;
                }

                return onNavigationItemSelected(item);
            }
        });






    }

    private  void loadFragment (Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container ,fragment);
        fragmentTransaction.commit();

    }


}