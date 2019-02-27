package com.example.pottho.countrycodepicker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button btnGetCountry, btnFullNumber;

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();

        return view;
    }

    private void init() {
        btnGetCountry = view.findViewById(R.id.btnGetCountry);
        btnFullNumber = view.findViewById(R.id.btnFullNumber);
        btnGetCountry.setOnClickListener(this);
        btnFullNumber.setOnClickListener(this);
    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFullNumber:
                MainActivity.exit = 1;
                setFragment(new FullNumberFragment());
                break;
            case R.id.btnGetCountry:
                MainActivity.exit = 1;
                setFragment(new GetCountryFragment());
                break;
        }
    }
}