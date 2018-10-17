package get;

import android.app.Activity;

public class getUserIdFromLocal {

    private String account=null;
    private String userId=null;
    private Activity activity;

    public getUserIdFromLocal(String account, Activity activity){
        this.account=account;
        this.activity=activity;
    }

    public String getUserId(){
        userId=new getAccount().getUserId(activity);
        return userId;
    }
}
