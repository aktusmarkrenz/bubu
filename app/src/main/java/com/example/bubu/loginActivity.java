package com.example.bubu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Type;

import mehdi.sakout.fancybuttons.FancyButton;

public class loginActivity extends AppCompatActivity {

    //Fonts
    Typeface lato_normal;
    Typeface lato_bold;
    Typeface monoround;

    //TextView
    TextView login_txt;
    TextView forgot_txt;
    TextView register_txt;
    TextView register_link;

    //EditText
    EditText username_txtbox;
    EditText password_txtbox;

    //FancyButton
    FancyButton login_btn;

//    ImageView passwordIcon;
//    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.login_form);

        //Fonts
        lato_normal = Typeface.createFromAsset(getAssets(), "fonts/Lato-Light.ttf");
        lato_bold = Typeface.createFromAsset(getAssets(), "fonts/Lato-Bold.ttf");
        monoround = Typeface.createFromAsset(getAssets(), "fonts/Monoround.otf");

        initializeComponents();
    }


    private void initializeComponents(){
//        passwordIcon = findViewById(R.id.password_icon);
//        password = findViewById(R.id.et_passord);

        login_txt = findViewById(R.id.login_txt);
        login_txt.setTypeface(lato_normal);
        login_txt.setTextSize(16);
    }

    public void login(View view) {
        //Toast.makeText(this, "Yeah", Toast.LENGTH_SHORT).show();
//        Intent main = new Intent(this, mainActivity.class);
//        startActivity(main);
    }

    public void showPassword(View view) {
//        int id = R.drawable.hide;
//        int inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
//
//        if(password.getInputType() == inputType) {
//            id = R.drawable.view;
//            inputType = InputType.TYPE_CLASS_TEXT;
//        }
//        ((ImageView)view).setImageDrawable(getDrawable(id));
//        password.setInputType(inputType);
//        password.setSelection(password.getText().length());
//    }
//
//    public void userIcon(View view) {
//        Toast.makeText(this, "walang function 'to, bubu", Toast.LENGTH_SHORT).show();
    }
}
