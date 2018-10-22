package get;

import net.HttpMethod;
import net.NetConnection;

import org.json.JSONException;
import org.json.JSONObject;

import Util.Constant;


/**
 * 根据foodId来获取菜品的详细信息
 */
public class getFoodDetail {


    JSONObject data=new JSONObject();

    public JSONObject getDetail(String foodId){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put(Constant.REQUEST_TYPE,"getFoodInfoById");
            jsonObject.put(Constant.FOODID,foodId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new NetConnection(Constant.SERVE_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    data=new JSONObject(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail(String result) {

            }
        },jsonObject);


        System.out.println(data.toString());

        return data;
    }
}
