package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ieat.R;
import java.util.List;

import Entity.Material;
import Util.XCRoundRectImageView;

public class HavenMatAdapter extends RecyclerView.Adapter<HavenMatAdapter.HavenMatViewHolder> {
    Context mContext;
    List<Material> mDatas;
    private LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    public HavenMatAdapter(Context context, List<Material> datas) {
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
    public void onBindViewHolder(HavenMatViewHolder holder, final int position) {
        holder.textView.setText(mDatas.get(position).getMatName());
        int resID = mContext.getResources().getIdentifier(mDatas.get(position).getImgName(), "drawable", mContext.getPackageName());
        holder.imageView.setBackground(mContext.getResources().getDrawable(resID));
        holder.textNuDtail.setText(mDatas.get(position).getNuDetails());

        for(int i=0;i<4;i++){
            if(i>=mDatas.get(position).getNutrition().size()){
                holder.nutrType[i].setBackground(null);
            }
            else{
                int resIDD = mContext.getResources().getIdentifier(mDatas.get(position).getNutrition().get(i).toString(), "drawable", mContext.getPackageName());
                holder.nutrType[i].setBackground(mContext.getResources().getDrawable(resIDD));
            }
        }
        if( mOnItemClickListener!= null){
            holder.itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
    public HavenMatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.basket_rec_layout, parent, false);
        HavenMatViewHolder holder = new HavenMatViewHolder(view);
        return holder;
    }


    class HavenMatViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;//牛肉
        private TextView textNuDtail;//具体文字
        private ImageView [] nutrType = {null,null,null,null};//营养成分
        private XCRoundRectImageView imageView;//
        public HavenMatViewHolder(View view) {
            super(view);
            imageView = (XCRoundRectImageView) view.findViewById(R.id.basket_rec_img);
            imageView.setType(XCRoundRectImageView.TYPE_ROUND);
            imageView.setRoundRadius(20);
            textView = (TextView) view.findViewById(R.id.basket_rec_text);
            textNuDtail = (TextView) view.findViewById(R.id.ingres);
            textNuDtail.getPaint().setFakeBoldText(true);
            nutrType[0] = (ImageView) view.findViewById(R.id.basket_type1);
            nutrType[1] = (ImageView) view.findViewById(R.id.basket_type2);
            nutrType[2] = (ImageView) view.findViewById(R.id.basket_type3);
            nutrType[3] = (ImageView) view.findViewById(R.id.basket_type4);
        }
    }

    public interface OnItemClickListener{
        void onClick( int position);
        void onLongClick( int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }
}
