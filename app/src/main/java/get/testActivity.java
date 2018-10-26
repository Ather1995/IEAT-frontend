package get;//package get;
//
//import android.os.Bundle;
//
//import com.example.ieat.R;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import Base.BaseActivity;
//
//public class testActivity extends BaseActivity{
//
//    private JSONObject jsonObject=new JSONObject();
//    private JSONArray jsonArray=new JSONArray();
//    @Override
//    protected void onCreate(Bundle saveInstanceState) {
//        super.onCreate(saveInstanceState);
//        setContentView(R.layout.test);
//
//
//        getFoodByMaterial getFoodByMaterial=new getFoodByMaterial();
//        jsonArray=getFoodByMaterial.getFood("南瓜,黄瓜");
//        System.out.println("你好啊你好");
//        for (int i=0;i<jsonArray.length();i++){
//            try {
//                System.out.println(jsonArray.getJSONObject(i).toString());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//        System.out.println("----------------------------------------------------------------");
//        getFoodDetail getFoodDetail=new getFoodDetail();
//
//        jsonArray=getFoodByMaterial.getFood(430+"");
//
//
//        System.out.println("----------------------------------------------------------------");
//
//        getPublishFood getPublishFood=new getPublishFood();
//        getPublishFood.publishFood(1+"");
//
//        System.out.println("----------------------------------------------------------------");
//        getUserCollection getUserCollection=new getUserCollection();
//        getUserCollection.getData(1+"");
//
//        System.out.println("----------------------------------------------------------------");
////        getUserIdFromLocal getUserIdFromLocal=new getUserIdFromLocal("4444",);
//
//    }
//
//
//}
