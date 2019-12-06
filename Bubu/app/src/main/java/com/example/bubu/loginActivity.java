package com.example.bubu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    ImageView passwordIcon;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);

        initializeComponents();
    }


    private void initializeComponents(){
        passwordIcon = findViewById(R.id.password_icon);
        password = findViewById(R.id.et_passord);
    }

    public void login(View view) {
        //Toast.makeText(this, "Yeah", Toast.LENGTH_SHORT).show();
        Intent main = new Intent(this, mainActivity.class);
        startActivity(main);
    }

    public void showPassword(View view) {
        int id = R.drawable.hide;
        int inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;

        if(password.getInputType() == inputType) {
            id = R.drawable.view;
            inputType = InputType.TYPE_CLASS_TEXT;
        }
        ((ImageView)view).setImageDrawable(getDrawable(id));
        password.setInputType(inputType);
        password.setSelection(password.getText().length());
    }

    public void userIcon(View view) {
        Toast.makeText(this, "walang function 'to, bubu", Toast.LENGTH_SHORT).show();
    }
}
