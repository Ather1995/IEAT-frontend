package Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.ieat.LoginActivity;
import com.example.ieat.Person_infoActivity;
import com.example.ieat.R;
import com.example.ieat.RecipeIndexActivity;
import com.example.ieat.SettingActivity;
import com.example.ieat.TreatActivity;

import net.HttpMethod;
import net.NetConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Constant;
import get.getAccount;

public class PersonFragment extends Fragment implements View.OnClickListener{
    private Button person_infor;
    private ImageButton person_head;
    private Button person_set;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_person, null);
        person_infor = (Button) view.findViewById(R.id.person_infor);
        person_infor.setOnClickListener(this);
        person_set = (Button) view.findViewById(R.id.person_set);
        person_set.setOnClickListener(this);
        person_head=(ImageButton)view.findViewById(R.id.person_head);
        person_head.setOnClickListener(this);
        return view;
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.person_set:
                Intent intent=new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.person_head:
                Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.person_infor:
                Intent intent3 = new Intent(getActivity(),Person_infoActivity.class);
                startActivity(intent3);
//                JSONObject personInforData = new JSONObject();
//                getAccount getAccount = new getAccount();
//                String userId = getAccount.getUserId(getActivity());
//                try {
//                    personInforData.put(Constant.REQUEST_TYPE,"getUserInfo");
//                    personInforData.put(Constant.USERID,userId);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                sendData(personInforData,"getUserInfo");
                break;
            default:
                break;
        }
    }
//    public void sendData(JSONObject data, final String request_type){
//        new NetConnection(Constant.SERVE_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
//            @Override
//            public void onSuccess(String result) {
//                JSONObject jsonObject= null;
//                try {
//                    jsonObject = new JSONObject(result);
//                    JSONArray jsonArray=jsonObject.getJSONArray("response");
//                    if (request_type.equals("getUserInfo")) {
//                        jsonObject=jsonArray.getJSONObject(0);
//                        String userInf = jsonObject.getString("nickName") + "," + jsonObject.getString("height")
//                                + "," + jsonObject.getString("weight") + "," + jsonObject.getString("age")
//                                + "," + jsonObject.getString("sex") + "," + jsonObject.getString("account");
//                        Intent intent = new Intent(getActivity(),Person_infoActivity.class);
//                        intent.putExtra("userInf",userInf);
//                        startActivity(intent);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new NetConnection.FailCallback() {
//            @Override
//            public void onFail(String result) {}
//        },data);
//    }
    @Override
    public void onPause(){
        super.onPause();
    }
}
