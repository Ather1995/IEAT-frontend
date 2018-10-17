package get;

import net.HttpMethod;
import net.NetConnection;
import net.parseJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Constant;

public class getFoodByMaterial {

    String material=null;

    JSONArray json=new JSONArray();

    public JSONArray getFood(String material){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put(Constant.REQUEST_TYPE,"getFoodByMaterial");
            jsonObject.put(Constant.MATERIAL,material);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new NetConnection(Constant.SERVE_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                json=new parseJSON().parseToArray(result);
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail(String result) {

            }
        },jsonObject);
        return json;
    }
}
