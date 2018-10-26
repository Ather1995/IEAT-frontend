package Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengfei on 2018/4/26.
 */

public class Menu {
    private String imgName;//food_yang
    private String menuName;//扬州炒饭
    private ArrayList<String> menuIngredients;
    public Menu(String imgName,String menuName){
        this.imgName=imgName;
        this.menuName = menuName;
        this.menuIngredients = new ArrayList<>();
    }
    public void setImgName(String imgName){
        this.imgName = imgName;
    }
    public void setMenuIngredients(ArrayList<String> menuIngredients){
        this.menuIngredients = menuIngredients;
    }
    public List<String>getMenuIngredients(){return this.menuIngredients;}
    public String getImgName(){return this.imgName;}
    public String getMenuName(){return this.menuName;}
    @Override
    public String toString(){
        return this.menuName;
    }
}
