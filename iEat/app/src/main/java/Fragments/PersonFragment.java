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

public class PersonFragment extends Fragment {
    private Button person_infor;
    private ImageButton person_head;//login
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_person, null);
        person_infor = (Button) view.findViewById(R.id.person_infor);
        person_head=(ImageButton)view.findViewById(R.id.person_head);
        person_infor.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Person_infoActivity.class);
                startActivity(intent);

            }
        });

        person_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    @Override
    public void onPause(){
        super.onPause();
    }
}
