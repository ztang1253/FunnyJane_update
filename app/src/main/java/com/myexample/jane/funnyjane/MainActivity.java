package com.myexample.jane.funnyjane;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    public static final String FILE_PATH = "saved.dat";
    public static final int RECORD_MAX = 10;

    private EditText et_name;
    private RadioGroup rg_sex;
    private EditText et_age;
    private CheckBox cb_married;
    private ToggleButton tb_job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        et_name = (EditText) findViewById(R.id.et_name);
        rg_sex = (RadioGroup) findViewById(R.id.rg_sex);
        et_age = (EditText) findViewById(R.id.et_age);
        cb_married = (CheckBox) findViewById(R.id.cb_marriage);
        cb_married.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(MainActivity.this, compoundButton.isChecked() ? "Married" : "Unmarried", Toast.LENGTH_SHORT).show();
            }
        });

        tb_job = (ToggleButton) findViewById(R.id.tb_job);
        tb_job.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(MainActivity.this, compoundButton.isChecked() ? "FullTime" : "PartTime", Toast.LENGTH_SHORT).show();
            }
        });

        et_name.setImeOptions(EditorInfo.IME_ACTION_DONE);
        et_age.setImeOptions(EditorInfo.IME_ACTION_DONE);

        et_age.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    }

    public void click(View view) {
        if(et_name.getText() == null || "".equals(et_name.getText().toString().trim())){
            et_name.requestFocus();
            Toast.makeText(MainActivity.this, "Please enter your name.", Toast.LENGTH_SHORT).show();
            return;
        }

        int age = 0;
        try {
            age = Integer.parseInt(et_age.getText().toString());
        } catch (NumberFormatException e) {
            et_age.requestFocus();
            Toast.makeText(MainActivity.this, "Please enter your age.", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = et_name.getText().toString().trim();
        int id = rg_sex.getCheckedRadioButtonId();
        int sex;
        if(id == R.id.rb_male) {
            sex = Sex.MALE;
        } else if (id == R.id.rb_female) {
            sex = Sex.FEMALE;
        } else if (id == R.id.rb_unknown) {
            sex = Sex.UNKNOWN;
        } else {
            sex = Sex.UNDIFFERENTIATED;
        }


        try {
            age = Integer.parseInt(et_age.getText().toString());
        } catch (NumberFormatException e) {

        }

        PersonEntity p = new PersonEntity(name, sex, age, cb_married.isChecked(), tb_job.isChecked());
        Intent intent = new Intent();

        Bundle bundle = new Bundle();
        bundle.putSerializable("content", p);
        intent.putExtras(bundle);
        intent.setAction("com.myexample.jane.funnyjane");
        startActivity(intent);
    }

    public void clickTop10(View view) {
        Intent i = new Intent();
        i.setClass(this, PersonListActivity.class);
        startActivity(i);
    }
}
