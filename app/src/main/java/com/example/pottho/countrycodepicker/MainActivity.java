package com.example.pottho.countrycodepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {

    TextView textViewCountryName, textViewCountryCode, textViewCountryNameCode;
    Button buttonReadCountry;
    CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        setClickListener();
    }

    private void setClickListener() {
        buttonReadCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewCountryName.setText(ccp.getSelectedCountryName());
                textViewCountryCode.setText(ccp.getSelectedCountryCode());
                textViewCountryNameCode.setText(ccp.getSelectedCountryNameCode());
            }
        });


    }

    private void assignViews() {
        ccp = findViewById(R.id.ccp);
        textViewCountryCode = findViewById(R.id.textView_countryCode);
        textViewCountryName = findViewById(R.id.textView_countryName);
        textViewCountryNameCode = findViewById(R.id.textView_countryNameCode);
        buttonReadCountry = findViewById(R.id.buttonReadCountry);
    }
}
