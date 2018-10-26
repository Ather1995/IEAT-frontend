package net;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class parseJSON {


    /**
     * 解析服务器发送来的json数据
     * 数据格式类似于{'response':[{'foodId':'id','foodName':'name','foodMaterial':'material','imageURL':'URL'},
     *          {'foodId':'id','foodName':'name','foodMaterial':'material','imageURL':'URL'}]}
//     * @param data
     */


    JSONObject json=new JSONObject();
    public void parse(String result){
        try {
            JSONObject jsonObject=new JSONObject(result);
            JSONArray jsonArray=jsonObject.getJSONArray("response");
            for (int i=0;i<jsonArray.length();i++){
                json=jsonArray.getJSONObject(i);
                System.out.println(json.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public JSONArray parseToArray(String result){
        JSONArray data=new JSONArray();
        try {
            JSONObject jsonObject=new JSONObject(result);
            JSONArray jsonArray=jsonObject.getJSONArray("response");
            for (int i=0;i<jsonArray.length();i++){
                json=jsonArray.getJSONObject(i);
                System.out.println(json.toString());
                data.put(json);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

    /**
     * 解析服务器对用户登录注册的响应消息
     * @param data
     */
    public void parseResponse(String data){
        try {
            JSONObject jsonObject=new JSONObject(data);
            JSONArray array=new JSONArray(data);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void parseFood(){

    }


}
