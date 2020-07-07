package com.example.nhom11_tuan03;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.StringJoiner;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* Event button signup*/
    public void SignUp(View view) {

        /* Thông tin từ cntrol*/
        TextView userName = findViewById(R.id.txtUserName);
        TextView password = findViewById(R.id.txtPass);
        TextView retype = findViewById(R.id.txtRepass);
        TextView birthday = findViewById(R.id.txtBirthdate);
        RadioGroup gender = findViewById(R.id.rdbGender);
        RadioButton rdbGenderChecked = findViewById(gender.getCheckedRadioButtonId());
        CheckBox tennis = findViewById(R.id.chkTennis);
        CheckBox futbal = findViewById(R.id.chkFutbal);
        CheckBox other = findViewById(R.id.chkOthers);

        /* Temp variable */
        String txtUserName = userName.getText().toString();
        String txtPassword = password.getText().toString();
        String txtRetype = retype.getText().toString();
        String txtBirthday = birthday.getText().toString();
        String txtGender = rdbGenderChecked.getText().toString();

        StringJoiner joiner = new StringJoiner(", ", "", "");
        if(tennis.isChecked()){
            joiner.add(tennis.getText().toString());
        }
        if(futbal.isChecked()){
            joiner.add(futbal.getText().toString());
        }
        if(other.isChecked()){
            joiner.add(other.getText().toString());
        }
        String txthobbies = joiner.toString();

        /* Kiểm tra mật khẩu*/
        if (txtPassword.length() > 0 && txtRetype.length() > 0) {
            /* Kiểm tra mật khẩu giống nhau*/
            if (txtPassword.equals(txtRetype)) {
                /*Kiểm tra định dạng ngày sinh*/
                if (txtBirthday.matches("^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$")) {

                    /*Chuyển thông tin sang activity mới*/
                    Intent intent = new Intent(this, ResultForm.class);
                    intent.putExtra("vUsername", txtUserName);
                    intent.putExtra("vPassword", txtPassword);
                    intent.putExtra("vBirthdate", txtBirthday);
                    intent.putExtra("vGender", txtGender);
                    intent.putExtra("vHobbies", txthobbies);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Sai định dạng ngày sinh( dd/mm/yyyy).", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Mật khẩu không khớp.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Bạn chưa nhập mật khẩu.", Toast.LENGTH_SHORT).show();
        }
    }

    /* Event button reset */
    public void Reset(View view) {
        /* Thông tin control */
        TextView userName = findViewById(R.id.txtUserName);
        TextView password = findViewById(R.id.txtPass);
        TextView retype = findViewById(R.id.txtRepass);
        TextView birthday = findViewById(R.id.txtBirthdate);
        RadioButton male = findViewById(R.id.rdbMale);
        CheckBox chbTennis = findViewById(R.id.chkTennis);
        CheckBox chbFutbal = findViewById(R.id.chkFutbal);
        CheckBox chbOther = findViewById(R.id.chkOthers);

        /* Set event default */
        userName.setText("");
        password.setText("");
        retype.setText("");
        birthday.setText("");
        chbTennis.setChecked(false);
        chbFutbal.setChecked(false);
        chbOther.setChecked(false);
        male.setChecked(true);
    }

    /* Button chọn ngày sinh*/
    public void SelectDatePicker(View view) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                TextView tetBirthday = findViewById(R.id.txtBirthdate);

                month = month +1;
                String formatBirthday = (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth) + "/" + (month < 10 ? "0" + month : month) + "/" + year;
                tetBirthday.setText(formatBirthday);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}