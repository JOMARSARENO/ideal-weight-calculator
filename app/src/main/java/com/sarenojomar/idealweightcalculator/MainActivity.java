package com.sarenojomar.idealweightcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    RadioGroup rgGender;
    EditText etHeightFoot;
    EditText etHeightInch;
    TextView tvIdealWeight;
    Button btnCalculate;
    String selectedGender = "male";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.rgGender = findViewById(R.id.rg_gender);
        this.etHeightFoot = findViewById(R.id.et_height_foot);
        this.etHeightInch = findViewById(R.id.et_height_inch);
        this.tvIdealWeight = findViewById(R.id.tv_ideal_weight);
        this.btnCalculate = findViewById(R.id.btn_calculate);

        //handle selected gender
        this.rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_gender_male) {
                    selectedGender = "male";
                } else {
                    selectedGender = "female";
                }
            }
        });

        this.btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validation
                if(etHeightFoot.getText() == null || etHeightFoot.getText().toString().equals("")) {
                    //no value entered
                    return;
                }

                if(etHeightInch.getText() == null || etHeightInch.getText().toString().equals("")) {
                    //no value entered
                    return;
                }

                //convert to double
                Double heightFoot = Double.parseDouble(etHeightFoot.getText().toString());
                Double heightInch = Double.parseDouble(etHeightInch.getText().toString());

                Double idealWeight;
                //check if male or female
                if (selectedGender.equals("male")) {
                    //the selected gender is male
                    //Robinson formula: 52 kg + 1.9 kg per every inch over 5 feet
                    idealWeight = 52+(1.9*((12*(heightFoot-5)) + heightInch));

                } else {
                    // the selected gender is female
                    //Robinson formula: 49 kg + 1.7 kg per every inch over 5 feet
                    idealWeight = 49+(1.7*((12*(heightFoot-5)) + heightInch));
                }

                //display the ideal weight
                tvIdealWeight.setText("Ideal Weight: " + idealWeight + " kgs");
            }
        });
    }
}