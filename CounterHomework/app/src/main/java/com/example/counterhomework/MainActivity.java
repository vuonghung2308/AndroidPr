package com.example.counterhomework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int count = 0;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (editText.getText().toString().equals("") == false) {
            outState.putString("text_in", "");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_count);
        editText = findViewById(R.id.edit_text);
        boolean check = savedInstanceState == null;
        Log.d("a", String.valueOf(check));
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("int_count");
            if (count != 0)
                textView.setText(String.valueOf(count));
            if (!editText.getText().toString().equals("")) {
                editText.setText("");
            }
        }
    }

    public void countAction(View view) {
        count += 1;
        textView.setText(String.valueOf(count));
        Log.d("a", editText.getText().toString());
    }
}