package com.example.ieat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

import Base.BaseActivity;
import Util.LogUtil;
import Util.ToastUtil;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements OnClickListener{
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        LogUtil.Log(this,"start","");
    }
    private void initView(){
        setContentView(R.layout.activity_login);
        ImageButton login = (ImageButton) findViewById(R.id.loginbutton);
        ImageButton register = (ImageButton) findViewById(R.id.registerbutton);
        ImageButton forgetPassword = (ImageButton) findViewById(R.id.forgetpassword);
        password = (EditText) findViewById(R.id.password);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        forgetPassword.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.loginbutton:
                ToastUtil.show(this,"login");
//                this.finish();
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.registerbutton:
                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent1);
                ToastUtil.show(this,"register");
                break;
            case R.id.forgetpassword:
                ToastUtil.show(this,"forgetpassword");
                break;
            default:
                break;
        }
    }
}

