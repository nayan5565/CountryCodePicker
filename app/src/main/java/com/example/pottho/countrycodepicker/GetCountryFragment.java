package com.example.pottho.countrycodepicker;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetCountryFragment extends Fragment {
    private View view;
    TextView textViewCountryName, textViewCountryCode, textViewCountryNameCode, add_field_button;
    Button buttonReadCountry;
    EditText edtPhone;
    CountryCodePicker ccp;
    private boolean isValidatedNumber;
    private LinearLayout lnEditField;
    private int click = 0;
    String msg;

    List<String> allNumber;

    public GetCountryFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_get_country, container, false);
        assignViews();
        registerCarrierEditText();
        setClickListener();
        return view;
    }

    private void registerCarrierEditText() {
        ccp.registerCarrierNumberEditText(edtPhone);
        ccp.setPhoneNumberValidityChangeListener(new CountryCodePicker.PhoneNumberValidityChangeListener() {
            @Override
            public void onValidityChanged(boolean isValidNumber) {
                isValidatedNumber = isValidNumber;
                if (isValidNumber) {
                    Toast.makeText(getContext(), "Valid Number: ", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(getContext(), "Invalid Number ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setClickListener() {
        buttonReadCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidatedNumber) {
                    Toast.makeText(getContext(), "Invalid Number ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Valid Number: ", Toast.LENGTH_SHORT).show();
                }
                textViewCountryName.setText(ccp.getSelectedCountryName());
                textViewCountryCode.setText(ccp.getSelectedCountryCodeWithPlus());
                textViewCountryNameCode.setText(ccp.getSelectedCountryNameCode());
                for (int i = 0; i < click; i++) {
                    msg = getTextFromEdit(i);
                    allNumber.add(getTextFromEdit(i));
                }
                popup();
            }
        });

        add_field_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click++;
                prepareDisplayEdittext();
            }
        });


    }

    private void assignViews() {
        allNumber = new ArrayList<>();
        lnEditField = view.findViewById(R.id.lnEditField);
        ccp = view.findViewById(R.id.ccp);
        textViewCountryCode = view.findViewById(R.id.textView_countryCode);
        edtPhone = view.findViewById(R.id.edtPhone);
        textViewCountryName = view.findViewById(R.id.textView_countryName);
        textViewCountryNameCode = view.findViewById(R.id.textView_countryNameCode);
        add_field_button = view.findViewById(R.id.add_field_button);
        buttonReadCountry = view.findViewById(R.id.buttonReadCountry);
    }

    private void createEdittext(String hint, int id) {
        LinearLayout.LayoutParams mRparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mRparams.setMargins(10, 10, 10, 10);
        EditText myEditText = new EditText(getContext());
        myEditText.setLayoutParams(mRparams);
        myEditText.setId(id);
        myEditText.setTextColor(Color.GRAY);
        myEditText.setHintTextColor(Color.GRAY);

        myEditText.setHint(hint);
        myEditText.setInputType(InputType.TYPE_CLASS_PHONE);
        lnEditField.addView(myEditText);

    }

    private void prepareDisplayEdittext() {
        lnEditField.removeAllViews();
        for (int i = 0; i < click; i++) {
            createEdittext("Alter Number " + i, i);
        }
    }

    private String getTextFromEdit(int id) {
        EditText editText = lnEditField.findViewById(id);
        String s = editText.getText().toString().trim();
        return s;
    }

    private void popup() {

        Dialog dialog = new Dialog(Objects.requireNonNull(getContext()));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.lay__dialog);
        dialog.setCancelable(true);
        TextView tvAllNumber = dialog.findViewById(R.id.tvAllNumber);
//        for (int i = 0; i < click; i++) {
//            tvAllNumber.setText("\n" + allNumber.get(i) + "\n");
//        }
        String numbers = new Gson().toJson(allNumber);
        tvAllNumber.setText(numbers);


        dialog.show();
    }


}
