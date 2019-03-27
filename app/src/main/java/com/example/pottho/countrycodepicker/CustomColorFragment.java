package com.example.pottho.countrycodepicker;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;


public class CustomColorFragment extends Fragment {

    TextView textViewTitle;
    EditText editTextPhone;
    CountryCodePicker ccp;
    RelativeLayout relativeColor1, relativeColor2, relativeColor3;
    int selectedColor = -1;
    private View view;

    public CustomColorFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_custom_color, container, false);
        assignViews();
        setClickListener();
        return view;
    }


    private void setClickListener() {
        relativeColor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColor(1, getActivity().getResources().getColor(R.color.color1));
            }
        });

        relativeColor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColor(2, getActivity().getResources().getColor(R.color.color2));
            }
        });

        relativeColor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColor(3, getActivity().getResources().getColor(R.color.color3));
            }
        });



        ccp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ccp.launchCountrySelectionDialog();
                if (selectedColor != -1) {
                    ccp.setContentColor(selectedColor);
                }
            }
        });
    }

    private void setColor(int selection, int color) {

        ccp.setContentColor(color);
        //textView
        textViewTitle.setTextColor(color);

        //editText
        editTextPhone.setTextColor(color);
        editTextPhone.setHintTextColor(color);
        editTextPhone.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);

        //remove selected bg
        resetBG();

        //set selected bg
        int selectedBGColor = getActivity().getResources().getColor(R.color.selectedTile);
        switch (selection) {
            case 1:
                relativeColor1.setBackgroundColor(selectedBGColor);
                break;
            case 2:
                relativeColor2.setBackgroundColor(selectedBGColor);
                break;
            case 3:
                relativeColor3.setBackgroundColor(selectedBGColor);
                break;
        }


    }

    private void resetBG() {
        relativeColor1.setBackgroundColor(getActivity().getResources().getColor(R.color.dullBG));
        relativeColor2.setBackgroundColor(getActivity().getResources().getColor(R.color.dullBG));
        relativeColor3.setBackgroundColor(getActivity().getResources().getColor(R.color.dullBG));
    }

    private void assignViews() {
        textViewTitle = view.findViewById(R.id.textView_title);
        editTextPhone = view.findViewById(R.id.editText_phone);
        ccp = view.findViewById(R.id.ccp);
        relativeColor1 = view.findViewById(R.id.relative_color1);
        relativeColor2 = view.findViewById(R.id.relative_color2);
        relativeColor3 = view.findViewById(R.id.relative_color3);
    }
}
