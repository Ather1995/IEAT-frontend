package com.example.ieat;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

import net.HttpMethod;
import net.NetConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Base.BaseActivity;
import Util.Constant;
import Util.LogUtil;
import Util.ToastUtil;
import get.getAccount;
import get.saveUserInfo;

public class RegisterActivity extends BaseActivity implements OnClickListener {
    private EditText password;
    private EditText password2;
    private EditText account;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }
    private void initView(){
        Log.e("RegisterActivity","initView");
        setContentView(R.layout.activity_register);
        ImageButton registerButton = (ImageButton) findViewById(R.id.registerButton1);
        password = (EditText) findViewById(R.id.re_password);
        password2 = (EditText) findViewById(R.id.re_password2);
        account = (EditText) findViewById(R.id.re_identity);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.e("RegisterActivity","onClick");
        JSONObject registerData = new JSONObject();
        switch (v.getId()){
            case R.id.registerButton1:
                Log.e(password.getText().toString(),password2.getText().toString());
                if(!password.getText().toString().equals(password2.getText().toString())){
                    password.setText("");
                    password2.setText("");
                    ToastUtil.show(RegisterActivity.this,"两次输入密码不同，请重新输入！");
                }else if(password.getText().toString().length()>12||password.getText().toString().length()<6){
                    password.setText("");
                    password2.setText("");
                    ToastUtil.show(RegisterActivity.this,"密码长度为6-12位，请重新输入！");
                }else {
                    try {
                        registerData.put(Constant.REQUEST_TYPE,"register");
                        registerData.put(Constant.REGISTER_TYPE,"tel");
                        registerData.put(Constant.ACCOUNT,account.getText().toString());
                        //将账号密码保存在本地
//                saveUserInfo saveUserInfo=new saveUserInfo();
                        saveUserInfo.saveAccountAndPassword(account.getText().toString(),
                                password.getText().toString(),getApplicationContext());
                        registerData.put(Constant.PASSWORD,password.getText().toString());
                        sendData(registerData,"login");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



//
//
//                    saveUserInfo.saveUserId("1",getApplicationContext());
//                    getAccount getAccount=new getAccount();
//                    String userIdtest=getAccount.getUserId(RegisterActivity.this);
//                    System.out.println("9999_loginbutton"+userIdtest);
//
//                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                    startActivity(intent);
                }

                break;

            default:
                break;

        }
    }
    public void sendData(JSONObject data, final String request_type){
        new NetConnection(Constant.SERVE_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                System.out.println("444411");
                JSONObject jsonObject= null;
                try {
                    jsonObject = new JSONObject(result);
                    JSONArray jsonArray=jsonObject.getJSONArray("response");
                    jsonObject = jsonArray.getJSONObject(0);
                    if (jsonObject.getInt(Constant.STATUSCODE)==Constant.SUCCESS) {
                        if (request_type.equals("register")) {
                            //获取userId并保存在本地
//                            saveUserInfo.saveUserId(jsonObject.getString("userId"),getApplicationContext());
                            ToastUtil.show(RegisterActivity.this, "注册成功！");
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
//                            getAccount getAccount=new getAccount();
//                            String userIdtest=getAccount.getUserId(RegisterActivity.this);
//                            System.out.println("9999"+userIdtest);
                        }else {
                            Log.e("注册","请求type出错");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail(String result) {
                System.out.println("555511");
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    JSONArray jsonArray=jsonObject.getJSONArray("response");
                    jsonObject=jsonArray.getJSONObject(1);
                    if (jsonObject.getInt(Constant.STATUSCODE)==Constant.FAIL) {
                        if (request_type.equals("register")) {
                            ToastUtil.show(RegisterActivity.this, "注册失败！");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },data);
    }
}
