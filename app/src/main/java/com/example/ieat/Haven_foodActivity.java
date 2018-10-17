package com.example.ieat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import android.view.View.OnClickListener;
import Adapter.HavenMatAdapter;
import Adapter.MenuAdapter;
import Base.BaseActivity;
import Entity.Material;
import Util.LogUtil;
import Util.ToastUtil;

public class Haven_foodActivity extends BaseActivity implements OnClickListener{
    private RecyclerView matRecyeclerView;
    private HavenMatAdapter havenMatAdapter;
    private Button button_peicai;
    private TextView add_mat;
    private List<Material> matList=new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_haven_food);
        Log.e("Haven_foodActivity","111111");
        button_peicai=(Button) findViewById(R.id.button_peicai);
        button_peicai.setOnClickListener(this);
        add_mat=(TextView)findViewById(R.id.add_mat);
        add_mat.setOnClickListener(this);

//        initView();
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.add_mat:
                Intent intent = new Intent(Haven_foodActivity.this, Haven_matActivity.class);
                startActivity(intent);
                break;
            case R.id.button_peicai:
                Intent intent1 = new Intent(Haven_foodActivity.this, RecipeIndexActivity.class);
                intent1.putExtra("notice","mat");
                this.finish();
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
    private void initView(){
        if(matList.size()>0){
            return;
        }
        initMat();
//        matRecyeclerView=(RecyclerView)findViewById(R.id.mat_recView);
        havenMatAdapter=new HavenMatAdapter(this,matList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        matRecyeclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        matRecyeclerView.setAdapter(havenMatAdapter);
        //设置增加或删除条目的动画
        matRecyeclerView.setItemAnimator(new DefaultItemAnimator());

        havenMatAdapter.setOnItemClickListener(new HavenMatAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent(getApplication(),RecipeActivity.class);
                startActivity(intent);
            }
            @Override
            public void onLongClick(int position) {
                Toast.makeText(getApplicationContext(),"您长按点击了"+position+"行",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initMat(){
        Material material1=new Material("牛肉","beaf","蛋白质含量高，而脂肪含量低，味道鲜美，属高蛋白、低脂肪，富含多种氨基酸和矿物质元素，具有消化吸收率高等特点");
        matList.add(material1);

        Material material2=new Material("番茄","potato","含有丰富的维生素、矿物质、碳水化合物、有机酸及少量的蛋白质，有促进消化、利尿、抑制多种细菌作用。番茄中维生素D可保护血管，治高血压");
        matList.add(material2);
        Material material3=new Material("牛肉","beaf","蛋白质含量高，而脂肪含量低，味道鲜美，属高蛋白、低脂肪，富含多种氨基酸和矿物质元素，具有消化吸收率高等特点");
        matList.add(material3);

        Material material4=new Material("番茄","potato","含有丰富的维生素、矿物质、碳水化合物、有机酸及少量的蛋白质，有促进消化、利尿、抑制多种细菌作用。番茄中维生素D可保护血管，治高血压");
        matList.add(material4);
        Material material5=new Material("牛肉","beaf","蛋白质含量高，而脂肪含量低，味道鲜美，属高蛋白、低脂肪，富含多种氨基酸和矿物质元素，具有消化吸收率高等特点");
        matList.add(material5);

        Material material6=new Material("番茄","potato","含有丰富的维生素、矿物质、碳水化合物、有机酸及少量的蛋白质，有促进消化、利尿、抑制多种细菌作用。番茄中维生素D可保护血管，治高血压");
        matList.add(material6);
        Material material7=new Material("牛肉","beaf","蛋白质含量高，而脂肪含量低，味道鲜美，属高蛋白、低脂肪，富含多种氨基酸和矿物质元素，具有消化吸收率高等特点");
        matList.add(material7);

        Material material8=new Material("番茄","potato","含有丰富的维生素、矿物质、碳水化合物、有机酸及少量的蛋白质，有促进消化、利尿、抑制多种细菌作用。番茄中维生素D可保护血管，治高血压");
        matList.add(material8);

    }

    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    @Override
    public void onPause(){
        super.onPause();
    }
}
