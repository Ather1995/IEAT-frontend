package com.example.ieat;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.HttpMethod;
import net.NetConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
                    final String imageUrl = jsonObject.getString(Constant.IMAGE);
//                    String imageUrl = "http://s2.cdn.xiachufang.com/1872317a87c811e6a9a10242ac110002_2048w_1536h.jpg?imageView2/1/w/280/h/216/interlace/1/q/90";

                    img.setImageDrawable(getApplication().getDrawable(R.mipmap.ic_launcher));

                    AsyncTask asyncTask =new AsyncTask<Void, Void, Bitmap>() {
                        @Override
                        protected Bitmap doInBackground(Void... params) {
                            try {
                                Bitmap[] bitmaps=new Bitmap[2];
                                URL url;
                                if(imageUrl==""){
                                    /* 这是一张网上的空白图像*/
                                    url= new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540743430789&di=2f63e48eb87080fccb2de258515a33f2&imgtype=0&src=http%3A%2F%2Fwww.tiantang6.com%2Fuptupian%2Ft20144815281.jpg");
                                }else {
                                    url = new URL(imageUrl);
                                }


                                Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                                return bitmap;
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                        @Override
                        protected void onPostExecute(Bitmap bitmap) {
                            super.onPostExecute(bitmap);
                            img.setImageBitmap(bitmap);
                        }
                    }.execute();

                    img.setTag(R.id.img,asyncTask);
                    String step_str = jsonObject.getString(Constant.STEP);
                    foodName.setText(food_name);
                    Log.e("RecipeActivity",imageUrl);
//                    Glide.with(RecipeActivity.this).load(imageUrl).into(img);
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

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("RECIPEON_pause","imghhhhh");
        AsyncTask asyncTask = (AsyncTask) img.getTag(R.id.img);
        asyncTask.cancel(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("RECIPEON_Stop","imghhhhh");
        AsyncTask asyncTask = (AsyncTask) img.getTag(R.id.img);
        asyncTask.cancel(true);
    }
}
