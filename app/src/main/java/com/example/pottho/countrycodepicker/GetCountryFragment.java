package com.example.pottho.countrycodepicker;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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

import static android.support.v4.content.ContextCompat.getSystemService;

public class GetCountryFragment extends Fragment {
    private View view;
    TextView textViewCountryName, textViewCountryCode, textViewCountryNameCode, add_field_button, tvDelete;
    Button buttonReadCountry, delete_button;
    EditText edtPhone;
    CountryCodePicker ccp;
    private boolean isValidatedNumber;
    private LinearLayout lnEditField, lnDelete;
    private int click = 0;
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
                Toast.makeText(getContext(), " Click: " + click, Toast.LENGTH_SHORT).show();
                allNumber.clear();
                for (int i = 0; i < click; i++) {
                    allNumber.add(getTextFromEdit(i));
                    Utils.savePref("Number" + i, "");

                }
                popup();
            }
        });

        add_field_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click++;
                prepareDisplayEdittext();
//                createView();
            }
        });
    }

    private void assignViews() {
        allNumber = new ArrayList<>();
        lnEditField = view.findViewById(R.id.lnEditField);
        lnDelete = view.findViewById(R.id.lnDelete);
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
        myEditText.setBackgroundResource(R.drawable.rounded_edittext);

        if (Utils.getPref("Number" + id, "").equals("")) {
            myEditText.setHint(hint);
        } else {
            myEditText.setText(Utils.getPref("Number" + id, ""));
        }

        myEditText.setInputType(InputType.TYPE_CLASS_PHONE);
        lnEditField.addView(myEditText);

    }


    private void createTextView(int id) {
        LinearLayout.LayoutParams mRparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mRparams.setMargins(0, 0, 0, 22);
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(mRparams);
        textView.setId(id);
        textView.setBackgroundResource(R.drawable.ic_remove);
        lnDelete.addView(textView);
        lnDelete.setVerticalGravity(View.TEXT_ALIGNMENT_CENTER);
    }

    private void visibilityGone(int id) {
        TextView textView = lnDelete.findViewById(id);
        textView.setVisibility(View.INVISIBLE);
    }

    private void visibilityView(int id) {
        TextView textView = lnDelete.findViewById(id);
        textView.setVisibility(View.VISIBLE);
    }

    private void createView() {
        LayoutInflater inflater = (LayoutInflater) Objects.requireNonNull(getContext()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        final View rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.
        lnEditField.addView(rowView, lnEditField.getChildCount() - 1);
    }

    private void prepareDisplayEdittext() {
        lnEditField.removeAllViews();
        lnDelete.removeAllViews();
        for (int i = 0; i < click; i++) {
            createEdittext("Alter Number " + i, i);
            createTextView(i);
            setOnClickListener(i);

            if (i > 0) {
                visibilityGone(i - 1);
            }
            setOnDateFromClickListner(i);
        }
    }

    private String getTextFromEdit(int id) {
        EditText editText = lnEditField.findViewById(id);
        String s = editText.getText().toString().trim();
        return s;
    }


    private void setOnClickListener(final int id) {
        EditText editText = lnEditField.findViewById(id);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Utils.savePref("Number" + id, getTextFromEdit(id));
                Toast.makeText(getContext(), "Number: " + id, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setOnDateFromClickListner(final int id) {
        TextView tvRemove = lnDelete.findViewById(id);
        tvRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click--;
                Toast.makeText(getContext(), "Remove: " + id + " Click: " + click, Toast.LENGTH_SHORT).show();
                lnEditField.removeViewAt(id);

//                lnEditField.removeView(getFromEdit(id));
//                lnEditField.removeViewAt(lnEditField.getChildCount() - 1);
                lnDelete.removeViewAt(id);
                if (click >= 1)
                    visibilityView(lnDelete.getChildCount() - 1);
            }
        });
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
