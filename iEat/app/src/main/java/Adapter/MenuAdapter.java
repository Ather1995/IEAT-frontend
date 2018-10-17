package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ieat.R;

import java.util.List;

import Entity.Menu;
import Entity.Recipe;
import Util.XCRoundRectImageView;

/**
 * Created by dengfei on 2018/4/26.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    Context mContext;
    List<Menu> mDatas;
    private LayoutInflater inflater;
    private MenuAdapter.OnItemClickListener mOnItemClickListener;
    public MenuAdapter(Context context, List<Menu> datas) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MenuViewHolder holder, final int position) {
        int resID1=mContext.getResources().getIdentifier(mDatas.get(2*position).getImgName(), "drawable", mContext.getPackageName());;
        int resID2=mContext.getResources().getIdentifier(mDatas.get(2*position+1).getImgName(), "drawable", mContext.getPackageName());;

        String mIngrs = "";
        for(String s:mDatas.get(2*position).getMenuIngredients()){
            mIngrs+=s+"、";
        }
        String mIngrs1 = "";
        for(String s:mDatas.get(2*position+1).getMenuIngredients()){
            mIngrs1+=s+"、";
        }
            holder.textTestLeft.setText(mDatas.get(2*position).getMenuName());
            holder.imageViewLeft.setBackground(mContext.getResources().getDrawable(resID1));
            holder.textIngresLeft.setText(mIngrs);

            holder.textTestRight.setText(mDatas.get(2*position+1).getMenuName());
            holder.imageViewRight.setBackground(mContext.getResources().getDrawable(resID2));
            holder.textIngresRight.setText(mIngrs1);


                if( mOnItemClickListener!= null){
            holder.itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("onClick","position");
                    mOnItemClickListener.onClick(position);
                }
            });
            holder.itemView.setOnLongClickListener( new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }
    }

    @Override
    public MenuAdapter.MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.menu_rec_layout, parent, false);
        MenuAdapter.MenuViewHolder holder = new MenuAdapter.MenuViewHolder(view);
        return holder;
    }


    class MenuViewHolder extends RecyclerView.ViewHolder {
        private TextView textTestLeft;
        private TextView textTestRight;
        private TextView textIngresLeft;
        private TextView textIngresRight;
        private XCRoundRectImageView imageViewLeft;
        private XCRoundRectImageView imageViewRight;
        public MenuViewHolder(View view) {
            super(view);
            imageViewLeft = (XCRoundRectImageView) view.findViewById(R.id.menu_rec_img_left);
            imageViewRight = (XCRoundRectImageView) view.findViewById(R.id.menu_rec_img_right);
            imageViewLeft.setType(XCRoundRectImageView.TYPE_ROUND);
            imageViewLeft.setRoundRadius(20);
            imageViewRight.setType(XCRoundRectImageView.TYPE_ROUND);
            imageViewRight.setRoundRadius(20);
            textTestLeft = (TextView) view.findViewById(R.id.menu_rec_text_left);
            textTestLeft.getPaint().setFakeBoldText(true);
            textTestRight = (TextView) view.findViewById(R.id.menu_rec_text_right);
            textTestRight.getPaint().setFakeBoldText(true);
            textIngresLeft = (TextView) view.findViewById(R.id.menu_ingresType_left) ;
            textIngresLeft.getPaint().setFakeBoldText(true);
            textIngresRight = (TextView) view.findViewById(R.id.menu_ingresType_right) ;
            textIngresRight.getPaint().setFakeBoldText(true);
        }
    }

    public interface OnItemClickListener{
        void onClick( int position);
        void onLongClick( int position);
    }
    public void setOnItemClickListener(MenuAdapter.OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }
}

