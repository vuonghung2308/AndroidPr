package com.example.hellotoat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static int INT_REQUEST = 1;
    public static final String EXTRA_INT = MainActivity.class.getName() + ".extra.INT";

    private int count = 0;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView_count);
    }

    public void countAction(View view) {
        count = count + 1;
        textView.setText(String.valueOf(count));
    }

    public void helloAction(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_INT, count);
        startActivityForResult(intent, INT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == INT_REQUEST) {
            if(resultCode == RESULT_OK) {
                count = data.getIntExtra(SecondActivity.EXTRA_INT, 0);
                textView.setText(String.valueOf(count));
            }
        }
    }
}