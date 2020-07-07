package com.example.nhom11_tuan03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultForm extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_form);

        /* Nhận thông tin từ activity register*/
        Intent intent = getIntent();
        TextView textView = findViewById(R.id.txtUserName);
        textView.setText(intent.getStringExtra("vUsername"));

        TextView textView2 = findViewById(R.id.txtPass);
        textView2.setText(intent.getStringExtra("vPassword"));

        TextView textView3 = findViewById(R.id.txtBirthdate);
        textView3.setText(intent.getStringExtra("vBirthdate"));

        TextView textView4 = findViewById(R.id.txtMale);
        textView4.setText(intent.getStringExtra("vGender"));

        TextView textView5 = findViewById(R.id.txtHobibies);
        textView5.setText(intent.getStringExtra("vHobbies"));
    }

    /* Đóng ứng dụng */
    public void Exit() {
        finish();
    }
}