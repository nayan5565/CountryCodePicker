package com.example.pottho.countrycodepicker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;

public class FullNumberFragment extends Fragment {

    EditText editTextLoadFullNumber, editTextLoadCarrierNumber, editTextGetCarrierNumber;
    TextView editTextGetFullNumber;
    CountryCodePicker ccpLoadNumber, ccpGetNumber;
    TextView tvValidity;
    ImageView imgValidity;
    Button buttonLoadNumber, buttonGetNumber, buttonGetNumberWithPlus, buttonFormattedFullNumber;
    Button buttonNext;
    private View view;

    public FullNumberFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_full_number, container, false);
        assignView();
        registerCarrierEditText();
        setClickListener();
        addTextWatcher();
        return view;
    }

    private void addTextWatcher() {
        editTextLoadFullNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buttonLoadNumber.setText("Load " + s + " to CCP.");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setClickListener() {
        buttonLoadNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ccpLoadNumber.setFullNumber(editTextLoadFullNumber.getText().toString());
            }
        });

        buttonFormattedFullNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextGetFullNumber.setText(ccpGetNumber.getFormattedFullNumber());
            }
        });

        buttonGetNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextGetFullNumber.setText(ccpGetNumber.getFullNumber());
            }
        });

        buttonGetNumberWithPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextGetFullNumber.setText(ccpGetNumber.getFullNumberWithPlus());
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void registerCarrierEditText() {
        ccpLoadNumber.registerCarrierNumberEditText(editTextLoadCarrierNumber);
        ccpGetNumber.registerCarrierNumberEditText(editTextGetCarrierNumber);
        ccpGetNumber.setPhoneNumberValidityChangeListener(new CountryCodePicker.PhoneNumberValidityChangeListener() {
            @Override
            public void onValidityChanged(boolean isValidNumber) {
                if (isValidNumber) {
                    imgValidity.setBackgroundColor(Color.GREEN);
                    tvValidity.setText("Valid Number");
                } else {
                    imgValidity.setBackgroundColor(Color.RED);
                    tvValidity.setText("Invalid Number");
                }
            }
        });
//        ccpGetNumber.registerCarrierNumberEditText(editTextGetCarrierNumber);
//        ccpLoadNumber.registerCarrierNumberEditText(editTextLoadCarrierNumber);
    }

    private void assignView() {
        //load number
        editTextLoadFullNumber = view.findViewById(R.id.editText_loadFullNumber);
        editTextLoadCarrierNumber = view.findViewById(R.id.editText_loadCarrierNumber);
        ccpLoadNumber = view.findViewById(R.id.ccp_loadFullNumber);
        buttonLoadNumber = view.findViewById(R.id.button_loadFullNumber);

        //get number
        editTextGetCarrierNumber = view.findViewById(R.id.editText_getCarrierNumber);
        editTextGetFullNumber = view.findViewById(R.id.textView_getFullNumber);
        buttonGetNumber = view.findViewById(R.id.button_getFullNumber);
        buttonGetNumberWithPlus = view.findViewById(R.id.button_getFullNumberWithPlus);
        ccpGetNumber = view.findViewById(R.id.ccp_getFullNumber);
        buttonFormattedFullNumber = view.findViewById(R.id.button_getFormattedFullNumberWithPlus);
        tvValidity = view.findViewById(R.id.tv_validity);
        imgValidity = view.findViewById(R.id.img_validity);

        buttonNext = view.findViewById(R.id.button_next);

    }
}
