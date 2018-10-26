package com.example.ieat;

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

import java.sql.SQLOutput;

import Base.BaseActivity;
import Util.Constant;
import Util.LogUtil;
import Util.ToastUtil;
import get.getAccount;
import get.getFoodByMaterial;
import get.getFoodDetail;
import get.getPublishFood;
import get.getUserCollection;
import get.getUserInfo;
import get.recommendFoodToManyPeople;
import get.recommendFoodToOnePeople;
import get.saveUserInfo;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements OnClickListener{
    private EditText password;
    private EditText account;
    private String userId;
    saveUserInfo saveInfo=new saveUserInfo();
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
        account = (EditText) findViewById(R.id.identity);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        forgetPassword.setOnClickListener(this);
    }
    public void onClick(View v){

        JSONObject loginData = new JSONObject();
        JSONObject registerData = new JSONObject();

//        Context context=getContext

        switch (v.getId()){
            case R.id.loginbutton:

               /* JSONArray jsonArray=new JSONArray();


                System.out.println("getFoodByMaterial----------------------------------------------------------------");
                getFoodByMaterial getFoodByMaterial=new getFoodByMaterial();
                jsonArray=getFoodByMaterial.getFood("南瓜,黄瓜");
                System.out.println("你好啊你好");
                for (int i=0;i<jsonArray.length();i++){
                    try {
                        System.out.println(jsonArray.getJSONObject(i).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("getFoodDetail----------------------------------------------------------------");
                getFoodDetail getFoodDetail=new getFoodDetail();

                JSONObject jsonObject = new JSONObject();
                jsonObject=getFoodDetail.getDetail(430+"");*/
                //Constant.REQUEST_TYPE,"getFoodInfoById"
                //Constant.FOODID,foodId "430"

                /*System.out.println(jsonObject.toString());


                System.out.println("getPublishFood----------------------------------------------------------------");

                getPublishFood getPublishFood=new getPublishFood();
                getPublishFood.publishFood(1+"");
                //Constant.REQUEST_TYPE,"getPublishFood";
                //Constant.USERID,userId "1";


                System.out.println("getUserCollection----------------------------------------------------------------");
                getUserCollection getUserCollection=new getUserCollection();
                getUserCollection.getData(1+"");

                System.out.println("getUserInfo----------------------------------------------------------------");
                getUserInfo getUserInfo=new getUserInfo();
                String UserId=new getAccount().getUserId(LoginActivity.this);
                jsonObject = getUserInfo.userInfo(1+"");
                //Constant.REQUEST_TYPE,"getUserInfo";
                //Constant.USERID,userId "1"

                System.out.println("recommendFoodForThisUser----------------------------------------------------------------");
                recommendFoodToOnePeople recommendFoodToOnePeople=new recommendFoodToOnePeople();

                recommendFoodToOnePeople.recommend(5+"");

                System.out.println("recommendFoodToManyPeople----------------------------------------------------------------");
                recommendFoodToManyPeople recommendFoodToManyPeople=new recommendFoodToManyPeople();
                recommendFoodToManyPeople.recommend(4,2,"老人,小孩",3+"");*/


                //ToastUtil.show(this,account.getText().toString()+":"+password.getText().toString());
//                postDatasync();
                try {
                    loginData.put(Constant.REQUEST_TYPE,"login");
                    loginData.put(Constant.LOGIN_TYPE,"tel");
                    loginData.put(Constant.ACCOUNT,account.getText().toString());
                    loginData.put(Constant.PASSWORD,password.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                sendData(loginData,"login");

                //将账号密码保存在本地
//                saveUserInfo saveUserInfo=new saveUserInfo();
                saveUserInfo.saveAccountAndPassword(account.getText().toString(),
                        password.getText().toString(),getApplicationContext());


                // saveUserInfo.saveUserId(userId,getApplicationContext());


                //     ToastUtil.show(this,"login");
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);

                break;
            case R.id.registerbutton:


                try {
                    registerData.put(Constant.REQUEST_TYPE,"register");
                    registerData.put(Constant.REGISTER_TYPE,"tel");
                    registerData.put(Constant.ACCOUNT,account.getText().toString());
                    registerData.put(Constant.PASSWORD,password.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                sendData(registerData,"register");

                //将账号密码保存在本地
                saveInfo.saveAccountAndPassword(account.getText().toString(),
                        password.getText().toString(),getApplicationContext());

                //获取userId并保存在本地
                saveUserInfo.saveUserId("1",getApplicationContext());
                getAccount account=new getAccount();
                String userid=account.getUserId(LoginActivity.this);
                saveInfo.saveUserId(userid,getApplicationContext());
                break;
            case R.id.forgetpassword:
                ToastUtil.show(this,"forgetpassword");
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
                    System.out.println(jsonArray+"000000");
                    jsonObject = jsonArray.getJSONObject(0);
                    System.out.println(jsonObject+"1000000");
                    if (jsonObject.getInt(Constant.STATUSCODE)==Constant.SUCCESS) {
                        if (request_type.equals("login")) {
                            //获取userId并保存在本地
//                            saveUserInfo.saveUserId(jsonObject.getString("userId"),getApplicationContext());
//                            getAccount getAccount=new getAccount();
//                            String userIdtest=getAccount.getUserId(LoginActivity.this);
//                            System.out.println("9999"+userIdtest);
                        }else{
                            ToastUtil.show(LoginActivity.this, "注册成功！");
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
                        if (request_type.equals("login")) {
                            ToastUtil.show(LoginActivity.this, "登录失败！");
                        }else{
                            ToastUtil.show(LoginActivity.this, "注册失败！");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },data);
    }



//    private JSONObject data;


//    public void postDatasync(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
//                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式
//                    String jsonStr = "{\"request_type\":\"login\",\"account\":\"aaa\",\"password\":\"fff\",\"login_type\":\"tel\"}";
//                    RequestBody body = RequestBody.create(JSON, jsonStr);
//                    Request request = new Request.Builder()
//                            .url("http://115.159.127.223/host/servlet/user/ParseJSONServlet")
//                            .post(body)
//                            .build();
//                    Response response = null;
//                    response = client.newCall(request).execute();//得到Response 对象
//                    if (response.isSuccessful()) {
//                        Log.d("kwwl","response.code()=="+response.code());
//                        Log.d("kwwl","response.message()=="+response.message());
//                        Log.d("kwwl","res=="+response.body().string());
//                    }
//                    else{
//                        Log.d("kwwl","fail");
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
}

