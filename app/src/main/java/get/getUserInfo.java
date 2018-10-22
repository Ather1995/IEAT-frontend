package get;

import net.HttpMethod;
import net.NetConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Constant;


/**
 * 返回用户的详细信息
 */
public class getUserInfo {

    String userId=null;
    JSONObject resultJSON=new JSONObject();


    public JSONObject userInfo(String userId){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put(Constant.REQUEST_TYPE,"getUserInfo");
            jsonObject.put(Constant.USERID,userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new NetConnection(Constant.SERVE_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject json=new JSONObject(result);
                    JSONArray jsonArray=json.getJSONArray("response");
                    resultJSON=jsonArray.getJSONObject(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail(String result) {

            }
        },jsonObject);
        return resultJSON;
    }

}
