package get;

import android.content.Context;
import android.content.SharedPreferences;

public class saveUserInfo {


    /**
     * 储存用户名和密码
     * @param account
     * @param password
     * @param context
     */
    public static void saveAccountAndPassword(String account, String password, Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("account",account);
        editor.putString("password",password);
        editor.commit();
    }


    /**
     * 储存userId
     * @param userId
     * @param context
     */
    public static void saveUserId(String userId, Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("userId",userId);
        System.out.println(userId+"3333");
        editor.commit();
    }
}
