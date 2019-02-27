package com.example.pottho.countrycodepicker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static int exit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment(new HomeFragment());
    }

    @Override
    public void onBackPressed() {
        if (exit < 1) {
            super.onBackPressed();
        } else {
            exit=0;
            setFragment(new HomeFragment());
        }

    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = Objects.requireNonNull(this).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
