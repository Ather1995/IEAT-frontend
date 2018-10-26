package com.example.ieat;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.TextView;

import net.HttpMethod;
import net.NetConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Constant;
import Util.LogUtil;
import get.getAccount;

public class TreatActivity extends Activity implements View.OnClickListener{
    private TextView renshu;
    private ImageButton renshu_plus;
    private ImageButton renshu_minus;
    private TextView caishu;
    private ImageButton caishu_plus;
    private ImageButton caishu_minus;
    private ImageButton la;
    private ImageButton ku;
    private ImageButton haixian;
    private ImageButton hunshi;
    private ImageButton guwu;
    private ImageButton laoren;
    private ImageButton ertong;
    private ImageButton tangniao;
    private ImageButton gaoxueya;
    private ImageButton tuijian;
    int tmp;
    boolean flag_la;
    boolean flag_ku;
    boolean flag_haixian;
    boolean flag_hunshi;
    boolean flag_guwu;
    boolean flag_laoren;
    boolean flag_ertong;
    boolean flag_tangniao;
    boolean flag_gaoxueya;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_treat);
        LogUtil.Log(this,"start","");
        init();
    }

    public void init(){

        flag_la = false;
        flag_ku= false;
        flag_haixian= false;
        flag_hunshi= false;
        flag_guwu= false;
        flag_laoren= false;
        flag_ertong= false;
        flag_tangniao= false;
        flag_gaoxueya= false;

        renshu = (TextView) findViewById(R.id.renshu);
        caishu = (TextView) findViewById(R.id.caishu);
        renshu_plus = (ImageButton) findViewById(R.id.renshu_plus);
        renshu_minus = (ImageButton) findViewById(R.id.renshu_minus);
        caishu_plus = (ImageButton) findViewById(R.id.caishu_plus);
        caishu_minus = (ImageButton) findViewById(R.id.caishu_minus);
        la = (ImageButton) findViewById(R.id.la);
        ku = (ImageButton) findViewById(R.id.ku);
        haixian = (ImageButton) findViewById(R.id.haixian);
        hunshi = (ImageButton) findViewById(R.id.hunshi);
        guwu = (ImageButton) findViewById(R.id.guwu);
        laoren = (ImageButton) findViewById(R.id.laoren);
        ertong = (ImageButton) findViewById(R.id.ertong);
        tangniao = (ImageButton) findViewById(R.id.tangniao);
        gaoxueya = (ImageButton) findViewById(R.id.gaoxueya);
        tuijian = (ImageButton) findViewById(R.id.tuijian);

        renshu_plus.setOnClickListener(this);
        renshu_minus.setOnClickListener(this);
        caishu_minus.setOnClickListener(this);
        caishu_plus.setOnClickListener(this);
        la.setOnClickListener(this);
        ku.setOnClickListener(this);
        haixian.setOnClickListener(this);
        hunshi.setOnClickListener(this);
        guwu.setOnClickListener(this);
        laoren.setOnClickListener(this);
        ertong.setOnClickListener(this);
        tangniao.setOnClickListener(this);
        gaoxueya.setOnClickListener(this);
        tuijian.setOnClickListener(this);


    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.renshu_minus:
                tmp = Integer.parseInt(renshu.getText().toString());
                if (tmp > 0) {
                    tmp = tmp - 1;
                    renshu.setText(Integer.toString(tmp));
                }
                break;
            case R.id.renshu_plus:
                tmp = Integer.parseInt(renshu.getText().toString());
                tmp = tmp + 1;
                renshu.setText(Integer.toString(tmp));
                break;
            case R.id.caishu_minus:
                tmp = Integer.parseInt(caishu.getText().toString());
                if (tmp > 0) {
                    tmp = tmp - 1;
                    caishu.setText(Integer.toString(tmp));
                }
                break;
            case R.id.caishu_plus:
                tmp = Integer.parseInt(caishu.getText().toString());
                tmp = tmp + 1;
                caishu.setText(Integer.toString(tmp));
                break;
            case R.id.la:
                flag_la = !flag_la;
                if (flag_la)
                    la.setBackgroundResource(R.drawable.treat_gou);
                else la.setBackgroundResource(R.drawable.treat_kuang);
                break;
            case R.id.ku:
                flag_ku = !flag_ku;
                if (flag_ku)
                    ku.setBackgroundResource(R.drawable.treat_gou);
                else ku.setBackgroundResource(R.drawable.treat_kuang);
                break;
            case R.id.haixian:
                flag_haixian = !flag_haixian;
                if (flag_haixian)
                    haixian.setBackgroundResource(R.drawable.treat_gou);
                else haixian.setBackgroundResource(R.drawable.treat_kuang);
                break;
            case R.id.hunshi:
                flag_hunshi = !flag_hunshi;
                if (flag_hunshi)
                    hunshi.setBackgroundResource(R.drawable.treat_gou);
                else hunshi.setBackgroundResource(R.drawable.treat_kuang);
                break;
            case R.id.guwu:
                flag_guwu = !flag_guwu;
                if (flag_guwu)
                    guwu.setBackgroundResource(R.drawable.treat_gou);
                else guwu.setBackgroundResource(R.drawable.treat_kuang);
                break;
            case R.id.laoren:
                flag_laoren = !flag_laoren;
                if (flag_laoren) {
                    laoren.setBackgroundResource(R.drawable.treat_gou);
                }
                else {
                    laoren.setBackgroundResource(R.drawable.treat_kuang);
                }
                break;
            case R.id.ertong:
                flag_ertong = !flag_ertong;
                if (flag_ertong){
                    ertong.setBackgroundResource(R.drawable.treat_gou);
                }
                else {
                    ertong.setBackgroundResource(R.drawable.treat_kuang);
                }
                break;
            case R.id.tangniao:
                flag_tangniao = !flag_tangniao;
                if (flag_tangniao){
                    tangniao.setBackgroundResource(R.drawable.treat_gou);
                }
                else {
                    tangniao.setBackgroundResource(R.drawable.treat_kuang);
                }
                break;
            case R.id.gaoxueya:
                flag_gaoxueya = !flag_gaoxueya;
                if (flag_gaoxueya){
                    gaoxueya.setBackgroundResource(R.drawable.treat_gou);
                }
                else {
                    gaoxueya.setBackgroundResource(R.drawable.treat_kuang);
                }
                break;
            case R.id.tuijian:
                JSONObject treatData = new JSONObject();

                getAccount getAccount = new getAccount();
                String userId = getAccount.getUserId(TreatActivity.this);
                System.out.println(userId+"898989");
                String request_type = "recommendFood";
                String avoidFood = getavoidFood();
                String suitPeople = getSuitPeople();

                try {
                    treatData.put(Constant.REQUEST_TYPE,request_type);
                    treatData.put(Constant.USERID,userId);

                    treatData.put(Constant.AVOIDFOODTYPE,avoidFood);
                    treatData.put(Constant.SUITPEOPLE,suitPeople);
                    treatData.put(Constant.NUM_PEOPLE,renshu.getText().toString());
                    treatData.put(Constant.NUM_FOOD,caishu.getText().toString());

//                    if (flag_laoren)
//                        treatData.put(Constant.ISOLD,"1");
//                    else treatData.put(Constant.ISOLD,"0");
//                    if (flag_ertong)
//                        treatData.put(Constant.ISCHILD,"1");
//                    else treatData.put(Constant.ISCHILD,"0");
//                    if (flag_tangniao)
//                        treatData.put(Constant.ISDIABETE,"1");
//                    else treatData.put(Constant.ISDIABETE,"0");
//                    if (flag_gaoxueya)
//                        treatData.put(Constant.ISHIGHBLOOD,"1");
//                    else treatData.put(Constant.ISHIGHBLOOD,"0");
//
//                    if (flag_la)
//                        treatData.put(Constant.ISSPICY,"1");
//                    else treatData.put(Constant.ISSPICY,"0");
//                    if (flag_ku) treatData.put(Constant.ISBITTER,"1");
//                    else treatData.put(Constant.ISBITTER,"0");
//                    if (flag_haixian) treatData.put(Constant.ISSEAFOOD,"1");
//                    else treatData.put(Constant.ISSEAFOOD,"0");
//                    if (flag_hunshi) treatData.put(Constant.ISPORK,"1");
//                    else treatData.put(Constant.ISPORK,"0");
//                    if (flag_guwu) treatData.put(Constant.ISVEGE,"1");
//                    else treatData.put(Constant.ISVEGE,"0");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                sendData(treatData,request_type);
                break;
            default:
                break;
        }
    }

    public void sendData(JSONObject data, final String request_type){
        new NetConnection(Constant.SERVE_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject= null;
                try {
                    jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    if (request_type.equals("recommendFood")) {
                        jsonObject=jsonArray.getJSONObject(0);

                        Intent intent = new Intent(TreatActivity.this, RecipeIndexActivity.class);
                        intent.putExtra("notice","TreatActivity");
                        int length = jsonArray.length();
                        intent.putExtra("foodNum",length);

                        for (int i = 0; i < length; i++){
                            jsonObject = jsonArray.getJSONObject(i);
                            String str = jsonObject.toString();
                            intent.putExtra("food"+i, str);
                        }
                        TreatActivity.this.finish();
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail(String result) {
                System.out.println("22222");
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
}


