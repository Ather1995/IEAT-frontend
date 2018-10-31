package com.example.ieat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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

/**
 * Created by fanmiaomiao on 2018/3/29.
 */

public class RecipeActivity extends BaseActivity {

    private TextView foodName;
    private ImageView star1;
    private ImageView star2;
    private ImageView star3;
    private ImageView star4;
    private ImageView star5;
    private TextView material;
    private TextView step;
    private String foodId;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        foodName = (TextView) findViewById(R.id.foodName);
        star1 = (ImageView) findViewById(R.id.Star1);
        star2 = (ImageView) findViewById(R.id.Star2);
        star3 = (ImageView) findViewById(R.id.Star3);
        star4 = (ImageView) findViewById(R.id.Star4);
        star5 = (ImageView) findViewById(R.id.Star5);
        material = (TextView) findViewById(R.id.material);
        step = (TextView) findViewById(R.id.step);
        img = (ImageView) findViewById(R.id.img);
        initView();
    }
    public void initView(){
        Intent intent = getIntent();
        foodId = intent.getStringExtra(Constant.FOODID);
        getAccount getAccount = new getAccount();
        String userId = getAccount.getUserId(this);
        Log.e("RecipeActivity",foodId);
        if(userId==""){
            ToastUtil.show(RecipeActivity.this,"请登陆！");
            return;
        }else {
            Log.e("RecipeActivityfoodId:",foodId);
            JSONObject jsonObject = new JSONObject();
            String request_type = "getFoodInfoById";
            try {
//                jsonObject.put(Constant.USERID,userId);
                jsonObject.put(Constant.REQUEST_TYPE,request_type);
                jsonObject.put(Constant.FOODID,foodId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            sendData(jsonObject,request_type);
        }




//        String food_name = "冰黄瓜";
//        int food_star = 4;
//        String food_material = "新鲜黄瓜\",\"手捏少量盐 ";
//        food_material = food_material.replace("\"","");
//        String imageUrl = "http://s2.cdn.xiachufang.com/1872317a87c811e6a9a10242ac110002_2048w_1536h.jpg?imageView2/1/w/280/h/216/interlace/1/q/90";
//        String step_str = "1.黄瓜削皮削小块 2.手捏少量的盐均匀洒在黄瓜上拌匀，盐量就是只能吃出微微的咸 3.放冰箱冰着，然后就开吃！ ";
//        step_str=step_str.replace(" ","\n");
//        foodName.setText(food_name);
//        Glide.with(this).load(imageUrl).into(img);
//        material.setText(food_material);
//        step.setText(step_str);
//        if (food_star > 0){
//            star1.setImageResource(R.drawable.star_light);
//            if (food_star > 1){
//                star2.setImageResource(R.drawable.star_light);
//                if (food_star > 2){
//                    star3.setImageResource(R.drawable.star_light);
//                    if (food_star > 3){
//                        star4.setImageResource(R.drawable.star_light);
//                        if (food_star > 4){
//                            star5.setImageResource(R.drawable.star_light);
//                        }
//                    }
//                }
//            }
//        }

    }

    public void sendData(JSONObject data, final String request_type){
        new NetConnection(Constant.SERVE_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject= null;
                try {
                    jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    jsonObject=jsonArray.getJSONObject(0);

                    String food_name = jsonObject.getString(Constant.FOODNAME);
                    int food_star = Integer.parseInt(jsonObject.getString(Constant.FOODSTAR));
                    String food_material = jsonObject.getString(Constant.FOODMATERIAL);
                    food_material = food_material.replace("\"","");
                    String imageUrl = jsonObject.getString(Constant.IMAGE);
                    String step_str = jsonObject.getString(Constant.STEP);
                    foodName.setText(food_name);
                    Glide.with(RecipeActivity.this).load(imageUrl).into(img);
                    material.setText(food_material);
                    step.setText(step_str);
                    if (food_star > 0){
                        star1.setImageResource(R.drawable.star_light);
                        if (food_star > 1){
                            star2.setImageResource(R.drawable.star_light);
                            if (food_star > 2){
                                star3.setImageResource(R.drawable.star_light);
                                if (food_star > 3){
                                    star4.setImageResource(R.drawable.star_light);
                                    if (food_star > 4){
                                        star5.setImageResource(R.drawable.star_light);
                                    }
                                }
                            }
                        }
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

}
