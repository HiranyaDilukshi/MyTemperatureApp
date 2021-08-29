package com.sliit.mytemperature;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btnCalculate;
    EditText inputTemp;
    TextView txtAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalculate = findViewById(R.id.btnCal);

    }

    @Override
    protected void onResume() {
        super.onResume();

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAnswer();
            }
        });
    }// end of onResume

    private void calculateAnswer() {

        inputTemp = findViewById(R.id.txtViewEnter);
        String temp = inputTemp.getText().toString();

        if (temp.equals("")) { //empty temp value
            Toast.makeText(this, "Please input a value to convert.", Toast.LENGTH_LONG).show();
        } else {

            //validate the value

            Float TempNumber = 0.0f;
            Boolean error = Boolean.FALSE;

            try {
                TempNumber = Float.parseFloat(temp);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid Entry.", Toast.LENGTH_LONG).show();
                error = Boolean.TRUE;
            }

            if (!error) {

                RadioGroup rg = findViewById(R.id.rgTemp);
                int id = rg.getCheckedRadioButtonId();
                float answer;
                float finalAnswer = Float.valueOf(inputTemp.getText().toString());


                if (id == R.id.rbCelcius) {

                    answer = convertCelciusToFahrenheit(finalAnswer);

                }else{

                    answer = convertFahrenheitToCelcius(finalAnswer);

                }

                String displayAnswer = Float.toString(answer);

                txtAnswer = findViewById(R.id.txtViewAnswer);
                txtAnswer.setText(" "+displayAnswer);


            }

        }


    }

    protected float convertCelciusToFahrenheit(Float value) {
        Float ans = (value * 9 / 5) + 32;
        return ans;
    }

    protected float convertFahrenheitToCelcius(Float value) {
        Float ans = (value - 32) * 5 / 9;
        return ans;
    }
}

