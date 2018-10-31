package com.example.ieat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import Adapter.MenuAdapter;
import Base.BaseActivity;
import Entity.Menu;
import Util.Constant;

public class RecipeIndexActivity extends BaseActivity {
    private RecyclerView menuRecyeclerView;
    private MenuAdapter menuAdapter;
    private List<Menu> menuList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_recipe_info);
        initView();
    }

    private void initView() {
        menuList=new ArrayList<>();
        if(menuList.size()>0){
            return;
        }

        initRecipe1();


        menuRecyeclerView=(RecyclerView)findViewById(R.id.menu_recView);
        menuAdapter = new MenuAdapter(this, menuList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        menuRecyeclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        menuRecyeclerView.setAdapter(menuAdapter);
        //设置增加或删除条目的动画
        menuRecyeclerView.setItemAnimator(new DefaultItemAnimator());

        menuAdapter.setOnItemClickListener(new MenuAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position, String foodId) {
                Intent intent=new Intent(getApplication(),RecipeActivity.class);
                intent.putExtra(Constant.FOODID,foodId);
//                当点击的不是空白的时候，即单数菜单的最后一个为fooodId为0
                if(foodId!=""){
                    startActivity(intent);
                }
            }
            @Override
            public void onLongClick(int position) {
                Toast.makeText(getApplicationContext(),"您长按点击了"+position+"行",Toast.LENGTH_SHORT).show();
            }
        });
        menuRecyeclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch(newState)
                {
                    case 0:
                        if(isSlideToBottom(recyclerView)) {
//                            Log.e("sssss","0000");
                        }
                        else{
//                            Log.e("sssss","0000showBottom()");
                        }
                        break;
                    case 1:
//                        Log.e("sssss","1111");
                        break;
                    case 2:
                        break;
                }
            }
        });
    }


    private void initRecipe1(){

        Intent intent = getIntent();

        int foodNum = intent.getIntExtra("foodNum",1);
        Log.e("initRecipe1", String.valueOf(foodNum));
        for (int i=0; foodNum>0; foodNum-=2,i++){
            String str = intent.getStringExtra("food"+i*2);
            String str1="";
            if(foodNum!=1){
            str1 = intent.getStringExtra("food"+(i*2+1));
            }
            try {
                JSONObject jsonObject = new JSONObject(str);
                String foodId = jsonObject.getString(Constant.FOODID);
                String foodName = jsonObject.getString(Constant.FOODNAME);
//                String foodStar = jsonObject.getString(Constant.FOODSTAR);
                String foodMaterial = jsonObject.getString(Constant.FOODMATERIAL);// "南瓜\",\"糯米粉\",\"糖\",\"芝士 "
                foodMaterial = jsonObject.getString(Constant.FOODMATERIAL).replace("\"","");
                String imageUrl = jsonObject.getString(Constant.IMAGE);
                JSONObject jsonObject1;
                String foodId1 = "";
                String foodName1 = "";
//                String foodStar = "";
                String foodMaterial1 = "";// "南瓜\",\"糯米粉\",\"糖\",\"芝士 "
                String imageUrl1 = "";
                if(foodNum!=1){
                    jsonObject1 = new JSONObject(str1);
                    foodId1 = jsonObject1.getString(Constant.FOODID);
                    foodName1 = jsonObject1.getString(Constant.FOODNAME);
//                String foodStar = jsonObject1.getString(Constant.FOODSTAR);
                    foodMaterial1 = jsonObject1.getString(Constant.FOODMATERIAL).replace("\"","");
//                    foodMaterial1 = jsonObject1.getString(Constant.FOODMATERIAL);// "南瓜\",\"糯米粉\",\"糖\",\"芝士 "
                    imageUrl1 = jsonObject1.getString(Constant.IMAGE);
                }
                Menu menu = new Menu(imageUrl,foodName,foodId,imageUrl1,foodName1,foodId1);
                ArrayList<String> ingrs = new ArrayList<>();
                ArrayList<String> ingrs1 = new ArrayList<>();
                String[] ss = foodMaterial.split(",");
                String[] ss1=null;
                if(foodNum!=1){
                    ss1 = foodMaterial1.split(",");
                    for (int j = 0; j < ss1.length; j++){
                        ingrs1.add(ss1[j]);
                        menu.setMenuIngredientsR(ingrs1);
                    }
                }
                for (int j = 0; j < ss.length; j++){
                    ingrs.add(ss[j]);
                }

                menu.setMenuIngredientsL(ingrs);
                menuList.add(menu);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    @Override
    public void onPause(){
        super.onPause();
    }
}
