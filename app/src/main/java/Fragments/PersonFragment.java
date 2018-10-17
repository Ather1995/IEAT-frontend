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

import com.example.ieat.Person_infoActivity;
import com.example.ieat.R;
import com.example.ieat.SettingActivity;

public class PersonFragment extends Fragment {
    private Button person_infor;
    private Button person_set;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_person, null);
        person_infor = (Button) view.findViewById(R.id.person_infor);
        person_infor.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Person_infoActivity.class);
                startActivity(intent);

            }
        });
        person_set = (Button) view.findViewById(R.id.person_set);
        person_set.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void init(){

    }
    @Override
    public void onPause(){
        super.onPause();
    }
}
