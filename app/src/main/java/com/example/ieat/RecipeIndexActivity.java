package com.example.ieat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import com.example.ieat.RecipeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import Adapter.MenuAdapter;
import Base.BaseActivity;
import Entity.Menu;
import Entity.Recipe;
import Util.Constant;
import Util.LogUtil;

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

//        Intent intent=getIntent();
//        String notice=intent.getStringExtra("notice");
//        if(notice.equals("Haven_foodActivity")){
//            initRecipe1();
//        }else if(notice.equals("home")){
//            initRecipe();
//        }else if(notice.equals("reco")){
//            initRecipe2();
//        }


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
            public void onClick(int position) {
                Intent intent=new Intent(getApplication(),RecipeActivity.class);
                startActivity(intent);
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

    private void initRecipe(){
        Menu menu = new Menu("food_yang","扬州炒饭");
        ArrayList<String> ingrs = new ArrayList<>();
        String ingrs_chaofan[]={"米饭","胡萝卜","玉米","豌豆","火腿"};
        for(String s:ingrs_chaofan){
            ingrs.add(s);
        }
        menu.setMenuIngredients(ingrs);
        menuList.add(menu);
        Menu menu1 = new Menu("food_yi","意大利面");
        ArrayList<String> ingrs1 = new ArrayList<>();
        String ingrs_yidalimian1[]={"番茄","西兰花","黑胡椒酱","意大利面"};
        for(String s:ingrs_yidalimian1){
            ingrs1.add(s);
        }
        menu1.setMenuIngredients(ingrs1);
        menuList.add(menu1);
        Menu menu2 = new Menu("food_beafpai","黑胡椒菲力牛排");
        ArrayList<String> ingrs2 = new ArrayList<>();
        String ingrs_shaobing2[]={"里脊肉","黑胡椒粉","洋葱粒","蒜末","黄油"};
        for(String s:ingrs_shaobing2){
            ingrs2.add(s);
        }
        menu2.setMenuIngredients(ingrs2);
        menuList.add(menu2);
        Menu menu3 = new Menu("food_salad","沙拉");
        ArrayList<String> ingrs3 = new ArrayList<>();
        String ingrs_shala3[]={"生菜","胡萝卜","藕块","番茄"};
        for(String s:ingrs_shala3){
            ingrs3.add(s);
        }
        menu3.setMenuIngredients(ingrs3);
        menuList.add(menu3);
        Menu menu4 = new Menu("food_bing","烧饼");
        ArrayList<String> ingrs4 = new ArrayList<>();
        String ingrs_shaobing4[]={"面粉","韭菜","猪肉"};
        for(String s:ingrs_shaobing4){
            ingrs.add(s);
        }//烧饼 食材：面粉、韭菜、猪肉
        menu4.setMenuIngredients(ingrs4);
        menuList.add(menu4);
        Menu menu5= new Menu("food_salad","沙拉");
        ArrayList<String> ingrs5 = new ArrayList<>();
        String ingrs_shala5[]={"生菜","胡萝卜","藕块","番茄"};
        for(String s:ingrs_shala5){
            ingrs.add(s);
        }//沙拉 食材：生菜、胡萝卜、藕块、番茄
        menu5.setMenuIngredients(ingrs5);
        menuList.add(menu5);
    }
    private void initRecipe1(){

        Intent intent = getIntent();

        int foodNum = intent.getIntExtra("foodNum",1);
        for (int i = 0; i < foodNum; i++){
            String str = intent.getStringExtra("food"+i);
            try {
                JSONObject jsonObject = new JSONObject(str);
                String foodId = jsonObject.getString(Constant.FOODID);
                String foodName = jsonObject.getString(Constant.FOODNAME);
//                String foodStar = jsonObject.getString(Constant.FOODSTAR);
                String foooMaterial = jsonObject.getString(Constant.FOODMATERIAL);// "南瓜\",\"糯米粉\",\"糖\",\"芝士 "

                String imageUrl = jsonObject.getString(Constant.IMAGE);
//                String step = jsonObject.getString(Constant.STEP);
                Menu menu = new Menu(imageUrl,foodName);
                ArrayList<String> ingrs = new ArrayList<>();
                String[] ss = foooMaterial.split(",");
                for (int j = 0; j < ss.length; j++){
                    ingrs.add(ss[j]);
                }
                menu.setMenuIngredients(ingrs);
                menuList.add(menu);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void initRecipe2(){
        Menu menu = new Menu("food_reco1","茄汁海鲜菇");
        ArrayList<String> ingrs = new ArrayList<>();
        String ingrs_chaofan[]={"西红柿","海鲜菇","葱姜","番茄酱"};
        for(String s:ingrs_chaofan){
            ingrs.add(s);
        }
        menu.setMenuIngredients(ingrs);
        menuList.add(menu);

        Menu menu1 = new Menu("food_reco2","虎皮青椒");
        ArrayList<String> ingrs1 = new ArrayList<>();
        String ingrs_yidalimian1[]={"青辣椒","生抽","蒜","姜","料酒"};
        for(String s:ingrs_yidalimian1){
            ingrs1.add(s);
        }
        menu1.setMenuIngredients(ingrs1);
        menuList.add(menu1);

        Menu menu2 = new Menu("food_reco3","美味鸡胸肉干丝");
        ArrayList<String> ingrs2 = new ArrayList<>();
        String ingrs_shaobing2[]={"鸡胸肉","橄榄油","番茄酱","蒜","小葱"};
        for(String s:ingrs_shaobing2){
            ingrs2.add(s);
        }
        menu2.setMenuIngredients(ingrs2);
        menuList.add(menu2);

        Menu menu3 = new Menu("food_reco4","鱼香肉丝");
        ArrayList<String> ingrs3 = new ArrayList<>();
        String ingrs_shala3[]={"猪腿肉","泡椒","姜","蒜","葱"};
        for(String s:ingrs_shala3){
            ingrs3.add(s);
        }
        menu3.setMenuIngredients(ingrs3);


        Menu menu4 = new Menu("food_reco5","凉拌鲫鱼");
        ArrayList<String> ingrs4 = new ArrayList<>();
        String ingrs_shaobing4[]={"鲫鱼","醋","盐","老坛泡菜水"};
        for(String s:ingrs_shaobing4){
            ingrs4.add(s);
        }//烧饼 食材：面粉、韭菜、猪肉
        menu4.setMenuIngredients(ingrs4);

        Menu menu5= new Menu("food_reco6","鸡蛋豆腐羹");
        ArrayList<String> ingrs5 = new ArrayList<>();
        String ingrs_shala5[]={"内脂豆腐","鸡蛋","西兰花","番茄"};
        for(String s:ingrs_shala5){
            ingrs5.add(s);
        }//沙拉 食材：生菜、胡萝卜、藕块、番茄
        menu5.setMenuIngredients(ingrs5);
        menuList.add(menu5);
        menuList.add(menu3);
        menuList.add(menu4);
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
