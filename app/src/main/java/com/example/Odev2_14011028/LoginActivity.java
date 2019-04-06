package com.example.Odev2_14011028;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    private EditText editUsername;
    private EditText editPassword;
    private Button butLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editUsername=(EditText)findViewById(R.id.editUsername);
        editPassword=(EditText)findViewById(R.id.editPass);
        butLogin=(Button)findViewById(R.id.button_login);
        butLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(editUsername.getText().toString().equals("admin") && editPassword.getText().toString().equals("password")){
                    Intent intent=new Intent(".MainActivity");
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this,"User or Pass is incorrect",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
