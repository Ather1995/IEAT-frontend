package get;

import net.HttpMethod;
import net.NetConnection;
import net.parseJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Constant;

public class recommendFoodToManyPeople {
    public int num_people=0;
    public int num_food=0;
    public String suit_people=null;
    public String userId=null;

    JSONArray jsonArray=new JSONArray();

    public JSONArray recommend(int num_food,int num_people,String suit_people,String userId) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put(Constant.REQUEST_TYPE,"recommendFood");
            jsonObject.put("num_people",num_people);
            jsonObject.put("num_food",num_food);
            jsonObject.put("suit_people",suit_people);
            jsonObject.put(Constant.USERID,userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
