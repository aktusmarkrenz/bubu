package com.example.bubu;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class mainActivity extends AppCompatActivity {
    Typeface monoround;
    TextView chatLabel;
    ImageView chatFragmentButton, contactFragmentButton;
    View option1, option2;
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
        chatFragmentButton = findViewById(R.id.message);
        contactFragmentButton = findViewById(R.id.contacts);

        option1 = findViewById(R.id.fragment_chat);
        option2 = findViewById(R.id.fragment_contacts);
        contactFragmentButton.setOnClickListener((this::selectedView));
        contactFragmentButton.setOnClickListener((this::selectedView));
    }


    public void selectedView(View select){
        option1.setVisibility(View.VISIBLE);
        option2.setVisibility(View.VISIBLE);
        if(select != chatFragmentButton) {
            option1.setVisibility(View.INVISIBLE);
            return;
        }
        option2.setVisibility(View.INVISIBLE);
    }
}
