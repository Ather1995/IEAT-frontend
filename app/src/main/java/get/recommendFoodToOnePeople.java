package get;

import net.HttpMethod;
import net.NetConnection;
import net.parseJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Constant;

public class recommendFoodToOnePeople {

    String userId=null;

    JSONArray jsonArray=new JSONArray();

    public JSONArray recommend(String userId){
        JSONObject jsonObject=new JSONObject();

        try {
            jsonObject.put(Constant.REQUEST_TYPE,"recommendFoodForThisUser");
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
