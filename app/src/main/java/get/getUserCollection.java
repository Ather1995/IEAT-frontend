package get;

import net.HttpMethod;
import net.NetConnection;
import net.parseJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Constant;

public class getUserCollection {

//    String account="44444";

    //userId可以通过本地的缓存来获取
    String userId=null;
    //通过account来获取推荐的菜品
    JSONArray jsonArray=new JSONArray();
    public JSONArray getData(String userId) {
        JSONObject json = new JSONObject();
        try {
            json.put(Constant.REQUEST_TYPE, "getUserCollection");
            json.put(Constant.USERID, 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new NetConnection(Constant.SERVE_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                parseJSON parseJSON=new parseJSON();
                jsonArray =parseJSON.parseToArray(result);
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail(String result) {

            }
        }, json);

        for (int i=0;i<jsonArray.length();i++){
            try {
                System.out.println("菜篮中的信息为"+jsonArray.getJSONArray(i).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }
}
