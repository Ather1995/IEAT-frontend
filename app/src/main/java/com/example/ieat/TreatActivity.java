package com.example.ieat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TreatActivity extends Activity implements View.OnClickListener{
    private TextView renshu;
    private ImageButton renshu_plus;
    private ImageButton renshu_minus;
    private TextView caishu;
    private ImageButton caishu_plus;
    private ImageButton caishu_minus;
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
    private ImageButton tuijian;
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
        setContentView(R.layout.home_treat);
        init();
    }

    public void init(){
        flag_la = false;
        flag_ku= false;
        flag_haixian= false;
        flag_hunshi= false;
        flag_guwu= false;
        flag_zhurou= false;
        flag_laoren= false;
        flag_ertong= false;
        flag_tangniao= false;
        flag_gaoxueya= false;

        renshu = (TextView) findViewById(R.id.renshu);
        caishu = (TextView) findViewById(R.id.caishu);
        jikou_qita = (EditText) findViewById(R.id.jikou_qita);
        teshu_qita = (EditText) findViewById(R.id.teshu_qita);
        renshu_plus = (ImageButton) findViewById(R.id.renshu_plus);
        renshu_minus = (ImageButton) findViewById(R.id.renshu_minus);
        caishu_plus = (ImageButton) findViewById(R.id.caishu_plus);
        caishu_minus = (ImageButton) findViewById(R.id.caishu_minus);
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
        tuijian = (ImageButton) findViewById(R.id.tuijian);

        renshu_plus.setOnClickListener(this);
        renshu_minus.setOnClickListener(this);
        caishu_minus.setOnClickListener(this);
        caishu_plus.setOnClickListener(this);
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
        tuijian.setOnClickListener(this);


    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.renshu_minus:
                tmp = Integer.parseInt(renshu.getText().toString());
                if (tmp > 0) {
                    tmp = tmp - 1;
                    renshu.setText(Integer.toString(tmp));
                }
                break;
            case R.id.renshu_plus:
                tmp = Integer.parseInt(renshu.getText().toString());
                tmp = tmp + 1;
                renshu.setText(Integer.toString(tmp));
                break;
            case R.id.caishu_minus:
                tmp = Integer.parseInt(caishu.getText().toString());
                if (tmp > 0) {
                    tmp = tmp - 1;
                    caishu.setText(Integer.toString(tmp));
                }
                break;
            case R.id.caishu_plus:
                tmp = Integer.parseInt(caishu.getText().toString());
                tmp = tmp + 1;
                caishu.setText(Integer.toString(tmp));
                break;
            case R.id.la:
                flag_la = !flag_la;
                if (flag_la)
                    la.setBackgroundResource(R.drawable.treat_gou);
                else la.setBackgroundResource(R.drawable.treat_kuang);
                break;
            case R.id.ku:
                flag_ku = !flag_ku;
                if (flag_ku)
                    ku.setBackgroundResource(R.drawable.treat_gou);
                else ku.setBackgroundResource(R.drawable.treat_kuang);
                break;
            case R.id.haixian:
                flag_haixian = !flag_haixian;
                if (flag_haixian)
                    haixian.setBackgroundResource(R.drawable.treat_gou);
                else haixian.setBackgroundResource(R.drawable.treat_kuang);
                break;
            case R.id.hunshi:
                flag_hunshi = !flag_hunshi;
                if (flag_hunshi)
                    hunshi.setBackgroundResource(R.drawable.treat_gou);
                else hunshi.setBackgroundResource(R.drawable.treat_kuang);
                break;
            case R.id.guwu:
                flag_guwu = !flag_guwu;
                if (flag_guwu)
                    guwu.setBackgroundResource(R.drawable.treat_gou);
                else guwu.setBackgroundResource(R.drawable.treat_kuang);
                break;
            case R.id.zhurou:
                flag_zhurou = !flag_zhurou;
                if (flag_zhurou)
                    zhurou.setBackgroundResource(R.drawable.treat_gou);
                else zhurou.setBackgroundResource(R.drawable.treat_kuang);
                break;
            case R.id.laoren:
                flag_laoren = !flag_laoren;
                if (flag_laoren)
                    laoren.setBackgroundResource(R.drawable.treat_gou);
                else laoren.setBackgroundResource(R.drawable.treat_kuang);
                break;
            case R.id.ertong:
                flag_ertong = !flag_ertong;
                if (flag_ertong)
                    ertong.setBackgroundResource(R.drawable.treat_gou);
                else ertong.setBackgroundResource(R.drawable.treat_kuang);
                break;
            case R.id.tangniao:
                flag_tangniao = !flag_tangniao;
                if (flag_tangniao)
                    tangniao.setBackgroundResource(R.drawable.treat_gou);
                else tangniao.setBackgroundResource(R.drawable.treat_kuang);
                break;
            case R.id.gaoxueya:
                flag_gaoxueya = !flag_gaoxueya;
                if (flag_gaoxueya)
                    gaoxueya.setBackgroundResource(R.drawable.treat_gou);
                else gaoxueya.setBackgroundResource(R.drawable.treat_kuang);
                break;
            case R.id.tuijian:
                Intent intent1 = new Intent(TreatActivity.this, RecipeIndexActivity.class);
                intent1.putExtra("notice","reco");
                this.finish();
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

}
