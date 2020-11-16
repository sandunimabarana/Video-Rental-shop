package com.example.video_renting_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.R.layout;
import android.app.DatePickerDialog;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    //create class reference
    DatePickerDialog datepicker;
    EditText eText,address,name,NIC;
    RadioGroup radioGr;
    private RadioButton radioSexButton;
    String[] country = {"Choose your age group", "10-17", "18-40", "41-60", "60 - 100)"};
    CheckBox action, comedy, drama, horror;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getid
        name = (EditText) findViewById(R.id.editText_firstname);
        address = (EditText) findViewById(R.id.editText_Address);
        NIC = (EditText) findViewById(R.id.editText_nic);
        radioGr = (RadioGroup) findViewById(R.id.radioGrp);
        //Add titles for the page
        getSupportActionBar().setTitle("Create User Profile");
        action = (CheckBox)findViewById(R.id.chkAndroid);
        comedy = (CheckBox)findViewById(R.id.chkAngular);
        drama = (CheckBox)findViewById(R.id.chkJava);
        horror = (CheckBox)findViewById(R.id.chkPython);
        Button btn = (Button)findViewById(R.id.getBtn);
        //Calender
        eText=(EditText) findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datepicker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datepicker.show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "Selected Courses";
                if(action.isChecked()){
                    result += "\nAction";
                }
                if(comedy.isChecked()){
                    result += "\nComedy";
                }
                if(drama.isChecked()){
                    result += "\nDrama";
                }
                if(horror.isChecked()){
                    result += "\nHorror";
                }
                String daten = eText.getText().toString().trim();
                String Name = name.getText().toString().trim();
                String Address = address.getText().toString().trim();
                String nic = NIC.getText().toString().trim();
                int selectedId = radioGr.getCheckedRadioButtonId();
                radioSexButton = (RadioButton) findViewById(selectedId);


                if (!TextUtils.isEmpty(daten)&& !TextUtils.isEmpty(result) && !TextUtils.isEmpty(Name)
                        && !TextUtils.isEmpty(Address) && !TextUtils.isEmpty(nic)){

                    Toast.makeText(getApplicationContext(), "Create Profile Suucessfully", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, layout.simple_spinner_item,country);
        aa.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        String str="";
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.chkAndroid:
                str = checked?"Action Selected":"Action Deselected";
                break;
            case R.id.chkAngular:
                str = checked?"Comedy Selected":"Comedy Deselected";
                break;
            case R.id.chkJava:
                str = checked?"Drama Selected":"Drama Deselected";
                break;
            case R.id.chkPython:
                str = checked?"Horror Selected":"Horror Deselected";
                break;
        }
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),country[position] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
