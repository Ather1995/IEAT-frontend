package com.example.ieat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import android.view.View.OnClickListener;

import net.HttpMethod;
import net.NetConnection;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Adapter.HavenMatAdapter;
import Adapter.MenuAdapter;
import Base.BaseActivity;
import Entity.Material;
import Util.Constant;
import Util.LogUtil;
import Util.ToastUtil;

public class Haven_foodActivity extends BaseActivity implements OnClickListener{
    private EditText food1;
    private EditText food2;
    private EditText food3;
    private EditText food4;
    private EditText food5;

    private Button click;
    private TextView add;
    private int statu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_haven_food);
        Log.e("Haven_foodActivity","111111");
        init();
    }
    public void init(){
        statu = 1;
        food1 = (EditText) findViewById(R.id.food1);
        food2 = (EditText) findViewById(R.id.food2);
        food3 = (EditText) findViewById(R.id.food3);
        food4 = (EditText) findViewById(R.id.food4);
        food5 = (EditText) findViewById(R.id.food5);
        add = (TextView) findViewById(R.id.add);
        click = (Button) findViewById(R.id.click);
        add.setOnClickListener(this);
        click.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.add:
                addFood();
                break;
            case R.id.click:
                JSONObject getFoodData = new JSONObject();

                String material = getMaterial();
                String request_type = "getFoodByMaterial";
                try {
                    getFoodData.put(Constant.REQUEST_TYPE,request_type);
                    getFoodData.put(Constant.MATERIAL,material);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                sendData(getFoodData,request_type);

                break;
            default:
                break;
        }
    }

    public void sendData(JSONObject data, final String request_type){
        new NetConnection(Constant.SERVE_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject_= null;
                try {
                    jsonObject_ = new JSONObject(result);
                    JSONArray jsonArray=jsonObject_.getJSONArray("response");
                    JSONObject jsonObject = new JSONObject();
                    Intent intent = new Intent(Haven_foodActivity.this, RecipeIndexActivity.class);
//                    intent.putExtra()
                    int length = jsonArray.length();
                    intent.putExtra("foodNum",length);
                    for (int i = 0; i < length; i++){
                        jsonObject = jsonArray.getJSONObject(i);
                        String str = jsonObject.toString();
                        intent.putExtra("food"+i, str);
                    }

//                    String recommendList = jsonObject.getString("");
//                    System.out.println(recommendList);

                    intent.putExtra("notice","Haven_foodActivity");
//                    this.finish();
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail(String result) {
                System.out.println("Fail222222222");
            }
        },data);
    }

    private String getMaterial(){
        String material = "";
        if (StringUtils.isBlank(food1.getText().toString())){
            Toast.makeText(Haven_foodActivity.this,"请输入食材",Toast.LENGTH_SHORT).show();
        }
        else {
            material = material + food1.getText().toString();
            if (statu > 1)
                material = material + food2.getText().toString();
            if (statu > 2)
                material = material + food3.getText().toString();
            if (statu > 3)
                material = material + food4.getText().toString();
            if (statu > 4)
                material = material + food5.getText().toString();
        }
        return material;
    }

    private void addFood(){
        if (statu < 5) {
            if (statu == 1 && StringUtils.isNotBlank(food1.getText().toString())){
                food2.setVisibility(View.VISIBLE);
                statu++;
            }
            else if (statu == 2 && StringUtils.isNotBlank(food2.getText().toString())){
                food3.setVisibility(View.VISIBLE);
                statu++;
            }
            else if (statu == 3 && StringUtils.isNotBlank(food3.getText().toString())){
                food4.setVisibility(View.VISIBLE);
                statu++;
            }
            else if (statu == 4 && StringUtils.isNotBlank(food4.getText().toString())){
                food5.setVisibility(View.VISIBLE);
                statu++;
            }
        }
        else {
            Toast.makeText(Haven_foodActivity.this,"超过上限了哦",Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onPause(){
        super.onPause();
    }
}
