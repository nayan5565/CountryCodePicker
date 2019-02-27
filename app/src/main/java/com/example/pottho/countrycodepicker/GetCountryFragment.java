package com.example.pottho.countrycodepicker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

public class GetCountryFragment extends Fragment {
    private View view;
    TextView textViewCountryName, textViewCountryCode, textViewCountryNameCode;
    Button buttonReadCountry;
    CountryCodePicker ccp;

    public GetCountryFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_get_country,container,false);
        assignViews();
        setClickListener();
        return view;
    }

    private void setClickListener() {
        buttonReadCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewCountryName.setText(ccp.getSelectedCountryName());
                textViewCountryCode.setText(ccp.getSelectedCountryCodeWithPlus());
                textViewCountryNameCode.setText(ccp.getSelectedCountryNameCode());
            }
        });


    }

    private void assignViews() {
        ccp = view.findViewById(R.id.ccp);
        ccp.setPhoneNumberValidityChangeListener(new CountryCodePicker.PhoneNumberValidityChangeListener() {
            @Override
            public void onValidityChanged(boolean isValidNumber) {
                Toast.makeText(getContext(),"Validated: "+isValidNumber,Toast.LENGTH_SHORT).show();
            }
        });
        textViewCountryCode =view.findViewById(R.id.textView_countryCode);
        textViewCountryName = view.findViewById(R.id.textView_countryName);
        textViewCountryNameCode = view.findViewById(R.id.textView_countryNameCode);
        buttonReadCountry = view.findViewById(R.id.buttonReadCountry);
    }
}
