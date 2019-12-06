package com.example.bubu;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class mainActivity extends AppCompatActivity {
    Typeface monoround;
    TextView chatLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_form);

        monoround = Typeface.createFromAsset(getAssets(), "fonts/Monoround.otf");
        initializeComponents();
    }


    private void initializeComponents(){
        chatLabel = findViewById(R.id.tvChat);
        chatLabel.setTypeface(monoround);
    }
}
