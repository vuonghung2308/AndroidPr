package com.example.hellotoat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static String EXTRA_INT = SecondActivity.class.getName() + ".extra.INT";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.text_count);
        Intent intent = getIntent();
        int count = intent.getIntExtra(MainActivity.EXTRA_INT, 0);
        textView.setText(String.valueOf(count));
    }

    public void resetAction(View view) {
        Intent respon = new Intent();
        respon.putExtra(EXTRA_INT, 0);
        setResult(RESULT_OK, respon);
        finish();
    }
}