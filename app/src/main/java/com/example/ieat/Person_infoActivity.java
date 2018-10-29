package com.example.ieat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import net.HttpMethod;
import net.NetConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Constant;
import get.getAccount;

public class Person_infoActivity extends Activity implements View.OnClickListener{
    private EditText height;
    private EditText weight;
    private EditText age;
    private EditText nickname;
    private EditText sex;
    private ImageButton la;
    private ImageButton ku;
    private ImageButton haixian;
    private ImageButton hunshi;
    private ImageButton guwu;
    private ImageButton laoren;
    private ImageButton ertong;
    private ImageButton tangniao;
    private ImageButton gaoxueya;
    private ImageButton click_;
    boolean flag_la = false;
    boolean flag_ku = false;
    boolean flag_haixian = false;
    boolean flag_hunshi = false;
    boolean flag_guwu = false;
    boolean flag_laoren = false;
    boolean flag_ertong = false;
    boolean flag_tangniao = false;
    boolean flag_gaoxueya = false;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        init();
//        setUserInfo();
    }

    public void init(){
        getAccount getAccount = new getAccount();
        userId = getAccount.getUserId(Person_infoActivity.this);

        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        age = (EditText) findViewById(R.id.age);
        nickname = (EditText) findViewById(R.id.nickname);
        sex = (EditText) findViewById(R.id.sex);

        la = (ImageButton) findViewById(R.id.la);
        ku = (ImageButton) findViewById(R.id.ku);
        haixian = (ImageButton) findViewById(R.id.haixian);
        hunshi = (ImageButton) findViewById(R.id.hunshi);
        guwu = (ImageButton) findViewById(R.id.guwu);

        laoren = (ImageButton) findViewById(R.id.laoren);
        ertong = (ImageButton) findViewById(R.id.ertong);
        tangniao = (ImageButton) findViewById(R.id.tangniao);
        gaoxueya = (ImageButton) findViewById(R.id.gaoxueya);
        click_ = (ImageButton) findViewById(R.id.click_);

        la.setOnClickListener(this);
        ku.setOnClickListener(this);
        haixian.setOnClickListener(this);
        hunshi.setOnClickListener(this);
        guwu.setOnClickListener(this);
        laoren.setOnClickListener(this);
        ertong.setOnClickListener(this);
        tangniao.setOnClickListener(this);
        gaoxueya.setOnClickListener(this);
        click_.setOnClickListener(this);

        SharedPreferences sharedPreferences = this.getSharedPreferences("userInfo", this.MODE_PRIVATE);
        Boolean setPersonInfo = sharedPreferences.getBoolean("setPersonInfo",false);
        if (setPersonInfo == true){
            JSONObject getInfoData = new JSONObject();
            try {
                getInfoData.put(Constant.REQUEST_TYPE,"getUserInfo");
                getInfoData.put(Constant.USERID,userId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(getInfoData);
            sendData(getInfoData,"getUserInfo");
        }

    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.la:
                flag_la = !flag_la;
                if (flag_la)
                    la.setImageResource(R.drawable.treat_gou);
                else la.setImageResource(R.drawable.treat_kuang);
                break;
            case R.id.ku:
                flag_ku = !flag_ku;
                if (flag_ku)
                    ku.setImageResource(R.drawable.treat_gou);
                else ku.setImageResource(R.drawable.treat_kuang);
                break;
            case R.id.haixian:
                flag_haixian = !flag_haixian;
                if (flag_haixian)
                    haixian.setImageResource(R.drawable.treat_gou);
                else haixian.setImageResource(R.drawable.treat_kuang);
                break;
            case R.id.hunshi:
                flag_hunshi = !flag_hunshi;
                if (flag_hunshi)
                    hunshi.setImageResource(R.drawable.treat_gou);
                else hunshi.setImageResource(R.drawable.treat_kuang);
                break;
            case R.id.guwu:
                flag_guwu = !flag_guwu;
                if (flag_guwu)
                    guwu.setImageResource(R.drawable.treat_gou);
                else guwu.setImageResource(R.drawable.treat_kuang);
                break;
            case R.id.laoren:
                flag_laoren = !flag_laoren;
                if (flag_laoren)
                    laoren.setImageResource(R.drawable.treat_gou);
                else laoren.setImageResource(R.drawable.treat_kuang);
                break;
            case R.id.ertong:
                flag_ertong = !flag_ertong;
                if (flag_ertong)
                    ertong.setImageResource(R.drawable.treat_gou);
                else ertong.setImageResource(R.drawable.treat_kuang);
                break;
            case R.id.tangniao:
                flag_tangniao = !flag_tangniao;
                if (flag_tangniao)
                    tangniao.setImageResource(R.drawable.treat_gou);
                else tangniao.setImageResource(R.drawable.treat_kuang);
                break;
            case R.id.gaoxueya:
                flag_gaoxueya = !flag_gaoxueya;
                if (flag_gaoxueya)
                    gaoxueya.setImageResource(R.drawable.treat_gou);
                else gaoxueya.setImageResource(R.drawable.treat_kuang);
                break;
            case R.id.click_:
                JSONObject personInforData = new JSONObject();
                String avoidFood = getavoidFood();
                String suitPeople = getSuitPeople();
                String flag_sex = getSex();

                try {
                    personInforData.put(Constant.REQUEST_TYPE,"updateUserInfo");
                    personInforData.put(Constant.USERID,userId);

                    personInforData.put(Constant.AVOIDFOODTYPE,avoidFood);
                    personInforData.put(Constant.SUITPEOPLE,suitPeople);
                    personInforData.put(Constant.AGE,Integer.parseInt(age.getText().toString()));
                    personInforData.put(Constant.SEX,flag_sex);
                    personInforData.put(Constant.HEIGHT,Integer.parseInt(height.getText().toString()));
                    personInforData.put(Constant.WEIGHT,Integer.parseInt(weight.getText().toString()));
                    personInforData.put(Constant.NICKNAME,nickname.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(personInforData);
                sendData(personInforData,"updateUserInfo");
                break;
            default:
                break;
        }
    }
    private void setUserInfo(){
        JSONObject getUserInfo = new JSONObject();

        try {
            getUserInfo.put(Constant.REQUEST_TYPE,"getUserInfo");
            getUserInfo.put(Constant.USERID,userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendData(getUserInfo,"getUserInfo");
    }
    public void sendData(JSONObject data, final String request_type){
        new NetConnection(Constant.SERVE_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(result);
                    JSONArray jsonArray=jsonObject.getJSONArray("response");
                    jsonObject=jsonArray.getJSONObject(0);
                    if (request_type.equals("getUserInfo")) {
                        nickname.setText(jsonObject.getString(Constant.NICKNAME));
                        height.setText(String.valueOf(jsonObject.getInt(Constant.HEIGHT)));
                        weight.setText(String.valueOf(jsonObject.getInt(Constant.WEIGHT)));
                        if ("0".equals(jsonObject.getString(Constant.AGE)))
                            age.setText("男");
                        else if ("1".equals(jsonObject.getString(Constant.AGE)))
                            age.setText("女");
                        String[] suitList = jsonObject.getString(Constant.SUITPEOPLE).replace("\"","").split(",");
                        String[] avoidList = jsonObject.getString(Constant.AVOIDFOODTYPE).replace("\"","").split(",");

                        for(int i = 0; i < suitList.length; i++){
                            setSuitpeople(suitList[i]);
                        }
                        for(int i = 0; i < avoidList.length; i++){
                            setAvoidFood(avoidList[i]);
                        }
                    }else if (request_type.equals("updateUserInfo")){
                        System.out.println(jsonObject.getString(Constant.NOTICE));
                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putBoolean("setPersonInfo",true);
                        editor.commit();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail(String result) {
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(result);
                    JSONArray jsonArray=jsonObject.getJSONArray("response");
                    jsonObject=jsonArray.getJSONObject(0);
                    if (request_type.equals("getUserInfo")) {

                    }
                    else if (request_type.equals("updateUserInfo")){
                        System.out.println(jsonObject.getString(Constant.NOTICE));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },data);
    }
    private String getavoidFood(){
        String avoidFoodType = "";
        boolean flag = false;
        if (flag_la) {
            if (flag == false){
                avoidFoodType = avoidFoodType + Constant.ISSPICY;
            }
            else{
                avoidFoodType = avoidFoodType + ","+Constant.ISSPICY;
            }
            flag = true;
        }
        if (flag_ku) {
            if (flag == false){
                avoidFoodType = avoidFoodType + Constant.ISBITTER;
            }
            else{
                avoidFoodType = avoidFoodType + ","+Constant.ISBITTER;
            }
            flag = true;
        }
        if (flag_haixian){
            if (flag == false){
                avoidFoodType = avoidFoodType + Constant.ISSEAFOOD;
            }
            else{
                avoidFoodType = avoidFoodType + ","+Constant.ISSEAFOOD;
            }
            flag = true;
        }
        if (flag_hunshi){

            if (flag == false){
                avoidFoodType = avoidFoodType + Constant.ISPORK;
            }
            else{
                avoidFoodType = avoidFoodType + ","+Constant.ISPORK;
            }
            flag = true;
        }
        if (flag_guwu){

            if (flag == false){
                avoidFoodType = avoidFoodType + Constant.ISVEGE;
            }
            else{
                avoidFoodType = avoidFoodType + ","+Constant.ISVEGE;
            }
            flag = true;
        }
//        if (flag_zhurou) avoidFoodType = avoidFoodType + ",zhurou";
//        if (!(jikou_qita.getText().toString() == null || jikou_qita.getText().toString().trim().length() == 0))
//            avoidFoodType = avoidFoodType + "," + jikou_qita.getText().toString();
        return avoidFoodType;
    }
    private String getSuitPeople(){
        String suitPeople = "";
        boolean flag = false;
        if (flag_laoren) {
            if (flag == false){
                suitPeople = suitPeople + Constant.ISOLD;
            }
            else{
                suitPeople = suitPeople + ","+Constant.ISOLD;
            }
            flag = true;
        }
        if (flag_ertong) {
            if (flag == false){
                suitPeople = suitPeople + Constant.ISCHILD;
            }
            else{
                suitPeople = suitPeople + ","+Constant.ISCHILD;
            }
            flag = true;
        }
        if (flag_tangniao) {
            if (flag == false){
                suitPeople = suitPeople + Constant.ISDIABETE;
            }
            else{
                suitPeople = suitPeople + ","+Constant.ISDIABETE;
            }
            flag = true;
        }
        if (flag_gaoxueya) {
            if (flag == false){
                suitPeople = suitPeople + Constant.ISHIGHBLOOD;
            }
            else{
                suitPeople = suitPeople + ","+Constant.ISHIGHBLOOD;
            }
        }
//        if (!(teshu_qita.getText().toString() == null || teshu_qita.getText().toString().trim().length() == 0))
//            suitPeople  = suitPeople + "," + teshu_qita.getText().toString();
        return suitPeople;
    }
    private String getSex(){
        String flag_sex;
        if ("男".equals(sex.getText().toString())) flag_sex = "0";
        else if ("女".equals(sex.getText().toString())) flag_sex = "1";
        else flag_sex = "-1";
        return flag_sex;
    }
    private void setSuitpeople(String suit) {
        if (suit.equals(Constant.ISOLD)){
            flag_laoren = true;
            laoren.setImageResource(R.drawable.treat_gou);
        } else if (suit.equals(Constant.ISCHILD)){
            flag_ertong = true;
            ertong.setImageResource(R.drawable.treat_gou);
        } else if (suit.equals(Constant.ISHIGHBLOOD)){
            flag_gaoxueya = true;
            gaoxueya.setImageResource(R.drawable.treat_gou);
        } else if (suit.equals(Constant.ISDIABETE)){
            flag_tangniao = true;
            tangniao.setImageResource(R.drawable.treat_gou);
        }
    }
    private void setAvoidFood(String avoid){
        if (avoid.equals(Constant.ISBITTER)){
            flag_ku = true;
            ku.setImageResource(R.drawable.treat_gou);
        } else if (avoid.equals(Constant.ISSPICY)){
            flag_la = true;
            la.setImageResource(R.drawable.treat_gou);
        } else if (avoid.equals(Constant.ISSEAFOOD)){
            flag_haixian = true;
            haixian.setImageResource(R.drawable.treat_gou);
        } else if (avoid.equals(Constant.ISVEGE)){
            flag_guwu = true;
            guwu.setImageResource(R.drawable.treat_gou);
        }
    }
}
