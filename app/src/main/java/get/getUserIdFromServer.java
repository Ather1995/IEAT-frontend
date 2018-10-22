package get;

import net.HttpMethod;
import net.NetConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Constant;

/**
 * 从服务器上获取userId
 */
public class getUserIdFromServer {

    JSONObject jsonObject=new JSONObject();
    String userId=null;
    public String getUserId(String account){
        try {
            jsonObject.put(Constant.REQUEST_TYPE,"getUserIdByAccount");
            jsonObject.put(Constant.ACCOUNT,account);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new NetConnection(Constant.SERVE_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject response=new JSONObject(result);
                    JSONArray jsonArray=response.getJSONArray("response");
                    JSONObject data=jsonArray.getJSONObject(0);
                    userId=data.getString("userId");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail(String result) {

            }
        },jsonObject);
        return userId;
    }
}
