package get;

import android.app.Activity;
import android.content.SharedPreferences;

public class getAccount {

    public String getUserAccount(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("accountAndPassword", activity.MODE_PRIVATE);
        String account = sharedPreferences.getString("account", "");
        return account;

    }

    public String getUserPassword(Activity activity){
        SharedPreferences sharedPreferences=activity.getSharedPreferences("accountAndPassword",activity.MODE_PRIVATE);
        String password=sharedPreferences.getString("password","");
        return password;
    }

    public String getUserId(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("userId", activity.MODE_PRIVATE);
        String userId=sharedPreferences.getString("userId","");
        if (userId==null||userId==""){

        }
        return userId;
    }
}