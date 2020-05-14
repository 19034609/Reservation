package sg.edu.rp.c346.id19034609.reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etFirstName, etLastName, etMobileNum, etGroupSize;
    DatePicker dp;
    TimePicker tp;
    CheckBox cbArea;
    TextView tvDetails;
    Button btnSubmit, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstName = findViewById(R.id.editTextFirstName);
        etLastName = findViewById(R.id.editTextLastName);
        etMobileNum = findViewById(R.id.editTextMobileNum);
        etGroupSize = findViewById(R.id.editTextGroupSize);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        cbArea = findViewById(R.id.checkboxArea);
        tvDetails = findViewById(R.id.textViewDetails);
        btnSubmit = findViewById(R.id.buttonSubmit);
        btnReset = findViewById(R.id.buttonReset);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String smokeStatus = "";
                String message = "";

                if (etFirstName.getText().toString().trim().length() == 0 || etLastName.getText().toString().trim().length() == 0){
                    Toast.makeText(MainActivity.this, "Please enter first and last name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etMobileNum.getText().toString().trim().length() == 0){
                    Toast.makeText(MainActivity.this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etGroupSize.getText().toString().trim().length() == 0){
                    Toast.makeText(MainActivity.this, "Please enter size of group", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (cbArea.isChecked()){
                    smokeStatus = "Yes";
                }
                else {
                    smokeStatus = "No";
                }

                message += "Name " + etLastName.getText().toString().trim() + " " + etFirstName.getText().toString().trim();
                message += "\nMobile Number: " + etMobileNum.getText().toString().trim();
                message += "\nGroup Size: " + etGroupSize.getText().toString();
                message += "\nDate: " + dp.getDayOfMonth() + (dp.getMonth() + 1) + dp.getYear();
                message += "\nTime: " + tp.getCurrentHour() + ":" + tp.getCurrentMinute();
                message += "\nSmoking Area: " + smokeStatus;
                tvDetails.setText(message);
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String messageClosed = "Reservation allowed between 8AM and 8:59PM";
                if (tp.getCurrentHour() < 8){
                    Toast.makeText(MainActivity.this, messageClosed, Toast.LENGTH_SHORT).show();
                    tp.setCurrentHour(8);
                }
                else if (tp.getCurrentHour() >= 21) {
                    Toast.makeText(MainActivity.this, messageClosed, Toast.LENGTH_SHORT).show();
                    tp.setCurrentHour(20);
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    public void reset(){
        etFirstName.setText("");
        etLastName.setText("");
        etMobileNum.setText("");
        etGroupSize.setText("");
        dp.updateDate(2020,5,1);
        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);
        cbArea.setChecked(false);
        tvDetails.setText("");
    }
}
