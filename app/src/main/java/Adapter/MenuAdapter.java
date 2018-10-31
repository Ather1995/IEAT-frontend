package Adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ieat.R;
import com.example.ieat.RecipeActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
//import java.util.logging.Handler;

import Entity.Menu;
import Util.Constant;
import Util.StreamTool;
import Util.XCRoundRectImageView;

/**
 * Created by dengfei on 2018/4/26.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private static final String TAG = "MenuAdapter";
    Context mContext;
    List<Menu> mDatas;
    private LayoutInflater inflater;
    private MenuAdapter.OnItemClickListener mOnItemClickListener;
    private Handler handler = null;
    private ImageView getImg1;
    private ImageView getImg2;
    static String url1;
    static String url2;
    private byte[] data1,data2;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private int foodIdR;
    private int foodIdL;
    public MenuAdapter(Context context, List<Menu> datas) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
/*
    1、当item还在加载图片的过程中，被移出屏幕可视范围，不需要继续加载这张图片了，可以在onRecycled中取消图片的加载。
    这样就不会造成图片加载完成设置到其他item的ImageView中了。

    2、每一个经过屏幕可视区域的item，加载的图片都要放进缓存中，即使item离开了可视区域，也要加载完毕并放入缓存中，方便下次浏览时能快速加载。
    每次onBind时对ImageView设置Tag标记，如果Tag标记已经被更改，旧线程加载好的图片不再设置到ImageView中。
*/
    @Override
    public void onViewRecycled(MenuViewHolder holder) {
        super.onViewRecycled(holder);
        AsyncTask asyncTask = (AsyncTask) holder.imageViewLeft.getTag(foodIdL);
        AsyncTask asyncTask1 = (AsyncTask) holder.imageViewRight.getTag(foodIdR);
        asyncTask.cancel(true);
        asyncTask1.cancel(true);
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final MenuViewHolder holder, final int position) {
        url1=mDatas.get(position).getImageUrlL();
        url2=mDatas.get(position).getImageUrlR();
        getImg1 = holder.imageViewLeft;
        getImg2 = holder.imageViewRight;
        foodIdL= Integer.parseInt(mDatas.get(position).getFoodIdL());
        foodIdR= Integer.parseInt(mDatas.get(position).getFoodIdR());


        //图片的占位符，若无，则可能出现错误
        getImg1.setImageDrawable(mContext.getDrawable(R.mipmap.ic_launcher));
        getImg2.setImageDrawable(mContext.getDrawable(R.mipmap.ic_launcher));

        AsyncTask asyncTask =new AsyncTask<Void, Void, Bitmap[]>() {
            @Override
            protected Bitmap[] doInBackground(Void... params) {
                try {
                    Bitmap[] bitmaps=new Bitmap[2];
                    URL urlL = new URL(mDatas.get(position).getImageUrlL());
                    URL urlR;
                    if(mDatas.get(position).getImageUrlR()==""){
                       /* 这是一张网上的空白图像*/
                        urlR= new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1540743430789&di=2f63e48eb87080fccb2de258515a33f2&imgtype=0&src=http%3A%2F%2Fwww.tiantang6.com%2Fuptupian%2Ft20144815281.jpg");
                    }else {
                        urlR = new URL(mDatas.get(position).getImageUrlR());
                    }


                    Bitmap bitmapL = BitmapFactory.decodeStream(urlL.openStream());
                    Bitmap bitmapR = BitmapFactory.decodeStream(urlR.openStream());
                    bitmaps[0]=bitmapL;
                    bitmaps[1]=bitmapR;
                    return bitmaps;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Bitmap[] bitmaps) {
                super.onPostExecute(bitmaps);
                holder.imageViewLeft.setImageBitmap(bitmaps[0]);
                holder.imageViewRight.setImageBitmap(bitmaps[1]);
            }
        }.execute();

        holder.imageViewLeft.setTag(R.id.menu_rec_img_left,asyncTask);
        holder.imageViewRight.setTag(R.id.menu_rec_img_right,asyncTask);
        String mIngrs = "";
        for(String s:mDatas.get(position).getMenuIngredientsL()){
            mIngrs+=s+"、";
        }
        String mIngrs1 = "";
        for(String s:mDatas.get(position).getMenuIngredientsR()){
            mIngrs1+=s+"、";
        }
            holder.textTestLeft.setText(mDatas.get(position).getMenuNameL());
            holder.textIngresLeft.setText(mIngrs);

            holder.textTestRight.setText(mDatas.get(position).getMenuNameR());
            holder.textIngresRight.setText(mIngrs1);


            if( mOnItemClickListener!= null){
                holder.menu_left.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Menu menu = mDatas.get(position);
                        Log.e("admenu_left",menu.getFoodIdL());
                        mOnItemClickListener.onClick(position,menu.getFoodIdL());
                    }
                });
                holder.menu_right.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Menu menu = mDatas.get(position);
                        Log.e("admenu_right",menu.getFoodIdR());
                        mOnItemClickListener.onClick(position,menu.getFoodIdR());
                    }
                });
        }
    }

    @Override
    public MenuAdapter.MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.menu_rec_layout, parent, false);
        final MenuAdapter.MenuViewHolder holder = new MenuAdapter.MenuViewHolder(view);
//        设置监视器，点击时候将foodId传给recipeIndexActivity
        holder.menu_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Menu menu = mDatas.get(position);
                Log.e("menu_right",menu.getFoodIdR());
                Intent intent = new Intent(view.getContext(),RecipeActivity.class);
                intent.putExtra(Constant.FOODID,menu.getFoodIdR());
            }
        });
        holder.menu_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Menu menu = mDatas.get(position);
                Intent intent = new Intent(view.getContext(),RecipeActivity.class);
                intent.putExtra(Constant.FOODID,menu.getFoodIdL());
                Log.e("menu_left",menu.getFoodIdL());
            }
        });
        return holder;
    }


    class MenuViewHolder extends RecyclerView.ViewHolder {
        View menuView;
        private LinearLayout menu_left;
        private LinearLayout menu_right;
        private TextView textTestLeft;
        private TextView textTestRight;
        private TextView textIngresLeft;
        private TextView textIngresRight;
        private XCRoundRectImageView imageViewLeft;
        private XCRoundRectImageView imageViewRight;
        public MenuViewHolder(View view) {
            super(view);
            menuView = view;
            menu_left = (LinearLayout) view.findViewById(R.id.menu_left);
            menu_right = (LinearLayout) view.findViewById(R.id.menu_right);
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
        void onClick(int position, String foodIdL);
        void onLongClick( int position);
    }
    public void setOnItemClickListener(MenuAdapter.OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }

    /**
     * @param urlpath String类型
     * @return byte[]类型
     * @throws IOException
     */
    public static byte[] GetUserHead(String urlpath) throws IOException {
        URL url = new URL(urlpath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET"); // 设置请求方法为GET
        conn.setReadTimeout(5 * 1000); // 设置请求过时时间为5秒
        InputStream inputStream = conn.getInputStream(); // 通过输入流获得图片数据
        byte[] data = StreamTool.readInputStream(inputStream); // 获得图片的二进制数据
        return data;

    }
    /*
     * 在新开的线程中设置图片显示
     */
    Runnable runnable = new Runnable() {

        public void run() {
            getImg1.setImageBitmap(bitmap1);
            getImg2.setImageBitmap(bitmap2);
        }
    };
}

