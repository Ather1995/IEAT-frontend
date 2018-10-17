package com.example.ieat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Person_infoActivity extends Activity implements View.OnClickListener{
    private EditText height;
    private EditText weight;
    private ImageButton la;
    private ImageButton ku;
    private ImageButton haixian;
    private ImageButton hunshi;
    private ImageButton guwu;
    private ImageButton zhurou;
    private EditText jikou_qita;
    private ImageButton laoren;
    private ImageButton ertong;
    private ImageButton tangniao;
    private ImageButton gaoxueya;
    private EditText teshu_qita;
    private ImageButton click_;
    int tmp;
    boolean flag_la;
    boolean flag_ku;
    boolean flag_haixian;
    boolean flag_hunshi;
    boolean flag_guwu;
    boolean flag_zhurou;
    boolean flag_laoren;
    boolean flag_ertong;
    boolean flag_tangniao;
    boolean flag_gaoxueya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        init();
    }

    public void init(){
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        jikou_qita = (EditText) findViewById(R.id.jikou_qita);
        teshu_qita = (EditText) findViewById(R.id.teshu_qita);
        la = (ImageButton) findViewById(R.id.la);
        ku = (ImageButton) findViewById(R.id.ku);
        haixian = (ImageButton) findViewById(R.id.haixian);
        hunshi = (ImageButton) findViewById(R.id.hunshi);
        guwu = (ImageButton) findViewById(R.id.guwu);
        zhurou = (ImageButton) findViewById(R.id.zhurou);
        laoren = (ImageButton) findViewById(R.id.laoren);
        ertong = (ImageButton) findViewById(R.id.ertong);
        tangniao = (ImageButton) findViewById(R.id.tangniao);
        gaoxueya = (ImageButton) findViewById(R.id.gaoxueya);
        click_ = (ImageButton) findViewById(R.id.click_);

        la.setOnClickListener(this);
        ku.setOnClickListener(this);
        haixian.setOnClickListener(this);
        hunshi.setOnClickListener(this);
        guwu.setOnClickListener(this);
        zhurou.setOnClickListener(this);
        laoren.setOnClickListener(this);
        ertong.setOnClickListener(this);
        tangniao.setOnClickListener(this);
        gaoxueya.setOnClickListener(this);
        click_.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.la:
                flag_la = !flag_la;
                if (flag_la)
                    la.setImageResource(R.drawable.treat_gou);
                else la.setImageResource(R.drawable.treat_kuang);
                break;
            case R.id.ku:
                flag_ku = !flag_ku;
                if (flag_ku)
                    ku.setImageResource(R.drawable.treat_gou);
                else ku.setImageResource(R.drawable.treat_kuang);
                break;
            case R.id.haixian:
                flag_haixian = !flag_haixian;
                if (flag_haixian)
                    haixian.setImageResource(R.drawable.treat_gou);
                else haixian.setImageResource(R.drawable.treat_kuang);
                break;
            case R.id.hunshi:
                flag_hunshi = !flag_hunshi;
                if (flag_hunshi)
                    hunshi.setImageResource(R.drawable.treat_gou);
                else hunshi.setImageResource(R.drawable.treat_kuang);
                break;
            case R.id.guwu:
                flag_guwu = !flag_guwu;
                if (flag_guwu)
                    guwu.setImageResource(R.drawable.treat_gou);
                else guwu.setImageResource(R.drawable.treat_kuang);
                break;
            case R.id.zhurou:
                flag_zhurou = !flag_zhurou;
                if (flag_zhurou)
                    zhurou.setImageResource(R.drawable.treat_gou);
                else zhurou.setImageResource(R.drawable.treat_kuang);
                break;
            case R.id.laoren:
                flag_laoren = !flag_laoren;
                if (flag_laoren)
                    laoren.setImageResource(R.drawable.treat_gou);
                else laoren.setImageResource(R.drawable.treat_kuang);
                break;
            case R.id.ertong:
                flag_ertong = !flag_ertong;
                if (flag_ertong)
                    ertong.setImageResource(R.drawable.treat_gou);
                else ertong.setImageResource(R.drawable.treat_kuang);
                break;
            case R.id.tangniao:
                flag_tangniao = !flag_tangniao;
                if (flag_tangniao)
                    tangniao.setImageResource(R.drawable.treat_gou);
                else tangniao.setImageResource(R.drawable.treat_kuang);
                break;
            case R.id.gaoxueya:
                flag_gaoxueya = !flag_gaoxueya;
                if (flag_gaoxueya)
                    gaoxueya.setImageResource(R.drawable.treat_gou);
                else gaoxueya.setImageResource(R.drawable.treat_kuang);
                break;
            default:
                break;
        }
    }

}
