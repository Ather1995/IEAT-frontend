package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ieat.HomeActivity;
import com.example.ieat.R;
import com.example.ieat.RecipeActivity;

import net.HttpMethod;
import net.NetConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.BasketRecipeAdapter;
import Entity.Recipe;
import Util.Constant;
import Util.LogUtil;
import Util.ToastUtil;
import get.getAccount;

public class BasketFragment extends Fragment {

    private RecyclerView basketRecyeclerView;
    private BasketRecipeAdapter basketRecipeAdapter;
    private List<Recipe> recipes = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_basket, null);
        return view;
    }
    @Override
    public void onStart(){
        super.onStart();
        initView();
    }

    private void initView(){
        if(recipes.size()>0){
            return;
        }
        basketRecyeclerView =  (RecyclerView) getView().findViewById(R.id.basket_recView);
        String userId = getAccount.getUserId(getActivity());
        if(userId==""||userId==null){
            ToastUtil.show(getActivity(), "请登录！");
        }else {
            Log.e("BasketInitRecipe",userId);
            initRecipe();
        }
        LogUtil.Log(getContext(),"RecipeNum",recipes.size()+"");
        basketRecipeAdapter = new BasketRecipeAdapter(getContext(), recipes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //设置布局管理器
        basketRecyeclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        basketRecyeclerView.setAdapter(basketRecipeAdapter);
        //设置增加或删除条目的动画
        basketRecyeclerView.setItemAnimator(new DefaultItemAnimator());
        basketRecipeAdapter.setOnItemClickListener(new BasketRecipeAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position, String foodId) {
                Intent intent=new Intent(getActivity(), RecipeActivity.class);
                intent.putExtra(Constant.FOODID,foodId);
//                当点击的不是空白的时候，即单数菜单的最后一个为fooodId为0
                if(foodId!=""){
                    Log.e("basketReci:foodId:",foodId);
                    startActivity(intent);
                }else {
                    Log.e("foodId为空","basketRecipeAdapter");
                }
            }
            @Override
            public void onLongClick(int position) {
                Toast.makeText(getActivity(),"您长按点击了"+position+"行",Toast.LENGTH_SHORT).show();
            }
        });
        basketRecyeclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

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
                            ((HomeActivity) getActivity()).hideBottom();
                        }
                        else{
                            ((HomeActivity) getActivity()).showBottom();
                        }
                        break;
                    case 1:
                        ((HomeActivity) getActivity()).hideBottom();
                        break;
                    case 2:
                        break;
                }
            }
        });
    }

    private void initRecipe(){
        JSONObject basketData = new JSONObject();
        getAccount getAccount = new getAccount();
        String userId = getAccount.getUserId(getActivity());

        try {
            basketData.put(Constant.REQUEST_TYPE,"getUserCollection");
            basketData.put(Constant.USERID,userId);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendData(basketData,"getUserCollection");
//        try {
//            JSONObject jsonObject = new JSONObject("{\"response\":\n" +
//                    "   \t\t    \t[\n" +
//                    "   \t\t    \t\t{\n" +
//                    "   \t\t    \t\t\t\"foodId\":1,\n" +
//                    "   \t\t    \t\t\t\"foodName\":\"冰黄瓜\",\n" +
//                    "   \t\t    \t\t\t\"foodStar\":\"5\",\n" +
//                    "   \t\t    \t\t\t\"foodMaterial\":\"新鲜黄瓜\\\",\\\"手捏少量盐 \",\n" +
//                    "   \t\t    \t\t\t\"imageUrl\":\"http://s2.cdn.xiachufang.com/1872317a87c811e6a9a10242ac110002_2048w_1536h.jpg?imageView2/1/w/280/h/216/interlace/1/q/90 \"\n" +
//                    "   \t\t    \t\t},\n" +
//                    "   \t\t    \t\t{\n" +
//                    "   \t\t    \t\t\t\"foodId\":2,\n" +
//                    "   \t\t    \t\t\t\"foodName\":\"西红柿炒鸡蛋\",\n" +
//                    "   \t\t    \t\t\t\"foodStar\":\"3\",\n" +
//                    "   \t\t    \t\t\t\"foodMaterial\":\"2个(中等大小)西红柿\\\",\\\"2个鸡蛋\\\",\\\"适量盐\\\",\\\"适量糖 \",\n" +
//                    "   \t\t    \t\t\t\"imageUrl\":\"http://s2.cdn.xiachufang.com/197c6db087c811e6b87c0242ac110003_3264w_2448h.jpg?imageView2/1/w/280/h/216/interlace/1/q/90 \"\n" +
//                    "   \t\t    \t\t},\n" +
//                    "   \t\t    \t\t{\n" +
//                    "   \t\t    \t\t\t\"foodId\":430,\n" +
//                    "   \t\t    \t\t\t\"foodName\":\"密瓜梨汁\",\n" +
//                    "   \t\t    \t\t\t\"foodStar\":\"4\",\n" +
//                    "   \t\t    \t\t\t\"foodMaterial\":\"1个蜜瓜\\\",\\\"2只梨\\\",\\\"2瓶乳酸菌饮料 \",\n" +
//                    "   \t\t    \t\t\t\"imageUrl\":\"http://s1.cdn.xiachufang.com/adc8271487ca11e6b87c0242ac110003_800w_600h.jpg@2o_50sh_1pr_1l_280w_216h_1c_1e_90q_1wh \"}\n" +
//                    "   \t\t    \t\t]\n" +
//                    "   \t\t    }\n");
//            JSONArray jsonArray = jsonObject.getJSONArray("response");
//
//            int length = jsonArray.length();
//
//            for (int i = 0; i < length; i++){
//                jsonObject = jsonArray.getJSONObject(i);
//                String str = jsonObject.toString();
//
//                ArrayList<Constant.Nutrtions> nutrs = new ArrayList<>();
//                Recipe recipe = new Recipe(jsonObject.getString(Constant.FOODNAME));
//                recipe.setStars(Integer.parseInt(jsonObject.getString(Constant.FOODSTAR)));
//                recipe.setImgUrl(jsonObject.getString(Constant.IMAGE));
//                recipe.setFoodId(jsonObject.getInt(Constant.FOODID)+"");
//                ArrayList<String> ingrs = new ArrayList<>();
//                String[] food_material = jsonObject.getString(Constant.FOODMATERIAL).replace("\"","").split(",");
//                for(String s:food_material){
//                    ingrs.add(s);
//                }
//                recipe.setIngredients(ingrs);
//                recipes.add(recipe);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
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

                    int length = jsonArray.length();

                    for (int i = 0; i < length; i++){
                        jsonObject = jsonArray.getJSONObject(i);
                        String str = jsonObject.toString();

                        ArrayList<Constant.Nutrtions> nutrs = new ArrayList<>();
                        Recipe recipe = new Recipe(jsonObject.getString(Constant.FOODNAME));
                        recipe.setStars(Integer.parseInt(jsonObject.getString(Constant.FOODSTAR)));
                        recipe.setImgUrl(jsonObject.getString(Constant.IMAGE));
                        recipe.setFoodId(jsonObject.getInt(Constant.FOODID)+"");
                        ArrayList<String> ingrs = new ArrayList<>();
                        String[] food_material = jsonObject.getString(Constant.FOODMATERIAL).replace("\"","").split(",");
                        for(String s:food_material){
                            ingrs.add(s);
                        }
                        recipe.setIngredients(ingrs);
                        recipes.add(recipe);
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
