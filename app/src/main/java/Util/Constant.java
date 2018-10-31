package Util;

public class Constant {
    static public String[] Foods = {"type_staple", "type_soup", "type_seafood", "type_fruit_vegetable", "type_breakfast", "type_western", "type_dessert"};

    public enum Nutrtions {
        VEGETABLES("vegetables", 1), EGG("egg", 2), OIL("oil", 3), MILK("milk", 4), MEAT("meats", 5), FISH("fish", 6), GRAIN("grain", 7);

        private String name;
        private int ID;

        Nutrtions(String na, int id) {
            this.name = na;
            this.ID = id;
        }

        @Override
        public String toString() {
            return "type_" + name;
        }

        public int getID() {
            return ID;
        }
    }

    //本地访问路径
//    public static String SERVE_URL="http://172.30.248.248:8090/servlet/user/ParseJSONServlet";
//    public static String SERVE_URL="http://190.168.43.202:8090/servlet/user/ParseJSONServlet";
 //   public static String SERVE_URL="http://yapi.demo.qunar.com/mock/25060/api/ieat";
    //服务器访问路径
    public static String SERVE_URL="http://115.159.127.223/ieat/servlet/user/ParseJSONServlet";
//    public static String SERVE_URL="http://172.30.248.248/ieat/servlet/user/ParseJSONServlet";


    public static final String CHARSET="utf-8";

    public static final String REQUEST_TYPE="request_type";
    public static final String REGISTER="register";
    public static final String LOGIN="login";

    public static final String LOGIN_TYPE="login_type";
    public static final String REGISTER_TYPE="register_type";
    public static final String QQ="qq";//QQ登录或注册
    public static final String WEICHAT="weichat";//微信登录或注册
    public static final String TEL="tel";//电话号码登录或注册


    /**
     * USER INFO
     */
    public static final String USERID = "userId";
    public static final String NICKNAME="nickName";
    public static final String HEIGHT="height";
    public static final String WEIGHT="weight";
    public static final String AGE="age";
    public static final String SEX="sex";
    public static final String ACCOUNT="account";
    public static final String PASSWORD="password";


    /**
     * FOOD INFO
     */
    public static final String FOODID="foodId";
    public static final String FOODNAME="foodName";
    public static final String MATERIAL="material";
    public static final String FOODMATERIAL="foodMaterial";
    public static final String STEP="step";
    public static final String IMAGE="imageUrl";
    public static final String FOODSTAR="foodStar";

    public static final String NUM_PEOPLE="num_people";
    public static final String NUM_FOOD="num_food";
    public static final String AVOIDFOODTYPE="avoidFoodType";
    public static final String SUITPEOPLE="suit_people";
    public static final String USERFLAVOUR="userFlavour";

    public static final String ISOLD="isOld";
    public static final String ISCHILD="isChlid";
    public static final String ISHIGHBLOOD="isdis_highBlood";
    public static final String ISDIABETE="isdis_diabete";
    public static final String ISSPICY="isSpicy";
    public static final String ISBITTER="isBitter";
    public static final String ISSEAFOOD="isSeafood";
    public static final String ISVEGE="isVege";
    public static final String ISPORK="isPork";


    /**
     * ACTION
     */
    public static final String GETUSERINFO="getUserInfo";
    public static final String GETFOODINFOBYID="getFoodInfoById";
    public static final String GETPUBLISHFOOD="getPublishFood";
    public static final String GETFOODBYMATERIAL="getFoodByMaterial";
    public static final String FOODCLICK="food_click";


    public static final String RESPONSE="response";
    public static final String NOTICE="notice";
    public static final String ISCOLLECTION="isCollection";



    public static final String STATUSCODE="status_code";
    public static final int FAIL=404;
    public static final int SUCCESS=200;

}
