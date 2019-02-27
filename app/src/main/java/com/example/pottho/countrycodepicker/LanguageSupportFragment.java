package com.example.pottho.countrycodepicker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

public class LanguageSupportFragment extends Fragment {

    private View rootView;
    RadioGroup radioGroup;
    RadioButton radioEnglish;
    RadioButton radioJapanese,radioSpanish;
    CountryCodePicker ccp;
    public LanguageSupportFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =inflater.inflate(R.layout.fragment_language_support,container,false);
        assignViews();
        setClickListener();
        return rootView;
    }

    private void setClickListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioEnglish:
                        ccp.changeDefaultLanguage(CountryCodePicker.Language.ENGLISH);
                        Toast.makeText(getContext(),"Language is updated to ENGLISH",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioJapanese:
                        ccp.changeDefaultLanguage(CountryCodePicker.Language.JAPANESE);
                        Toast.makeText(getContext(),"Language is updated to JAPANESE",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioSpanish:
                        ccp.changeDefaultLanguage(CountryCodePicker.Language.SPANISH);
                        Toast.makeText(getContext(),"Language is updated to SPANISH",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    private void assignViews() {
        ccp=rootView.findViewById(R.id.ccp);
        radioGroup= rootView.findViewById(R.id.radioGroup);
        radioEnglish=rootView.findViewById(R.id.radioEnglish);
        radioJapanese= rootView.findViewById(R.id.radioJapanese);
        radioSpanish=rootView.findViewById(R.id.radioSpanish);
    }
}
