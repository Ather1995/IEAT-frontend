package net;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Constant;


/**
 * 核心算法：为几个人来推荐特定的几道菜
 */
public class test {


    public int num_people=0;//几个人吃饭
    public int num_food=0;//几道菜
    public String avoidFoodType=null;//忌口如海鲜猪肉等
    public String suit_people=null;//适合的人群
    JSONObject jsonObject=new JSONObject();

    public test(){
        try {
            jsonObject.put(Constant.NUM_PEOPLE,num_people);
            jsonObject.put(Constant.NUM_FOOD,num_food);
            jsonObject.put(Constant.AVOIDFOODTYPE,avoidFoodType);
            jsonObject.put(Constant.SUITPEOPLE,suit_people);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    JSONArray jsonArray=new JSONArray();
    public JSONArray sendData(JSONObject jsonObject){
        new NetConnection(Constant.SERVE_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                jsonArray=new parseJSON().parseToArray(result);
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail(String result) {

            }
        },jsonObject);
        return jsonArray;
    }
}
