package Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengfei on 2018/4/26.
 */

public class Menu {
    private String imageUrlL;
    private String imageUrlR;
    private String menuNameL;//扬州炒饭
    private String menuNameR;//扬州炒饭
    private String foodIdL;
    private String foodIdR;
    private ArrayList<String> menuIngredientsL=new ArrayList<>();
    private ArrayList<String> menuIngredientsR=new ArrayList<>();
    public Menu(String imageUrlL,String menuNameL,String foodIdL,String imageUrlR,String menuNameR,String foodIdR){
        this.imageUrlL=imageUrlL;
        this.menuNameL = menuNameL;
        this.foodIdL=foodIdL;
        this.imageUrlR=imageUrlR;
        this.menuNameR=menuNameR;
        if(foodIdR==""){
            this.foodIdR="0";
        }else this.foodIdR=foodIdR;
    }

    public void setFoodIdL(String foodIdL) {
        this.foodIdL = foodIdL;
    }

    public void setFoodIdR(String foodIdR) {
        this.foodIdR = foodIdR;
    }

    public String getImageUrlL() {
        return imageUrlL;
    }

    public void setImageUrlL(String imageUrlL) {
        this.imageUrlL = imageUrlL;
    }

    public ArrayList<String> getMenuIngredientsL() {
        return menuIngredientsL;
    }

    public String getMenuNameL() {
        return menuNameL;
    }

    public void setMenuNameL(String menuNameL) {
        this.menuNameL = menuNameL;
    }

    public String getMenuNameR() {
        return menuNameR;
    }

    public void setMenuNameR(String menuNameR) {
        this.menuNameR = menuNameR;
    }

    public String getFoodIdL() {
        return foodIdL;
    }

    public String getFoodIdR() {
        return foodIdR;
    }

    public void setMenuIngredientsL(ArrayList<String> menuIngredientsL) {
        this.menuIngredientsL = menuIngredientsL;
    }

    public ArrayList<String> getMenuIngredientsR() {
        return menuIngredientsR;
    }

    public void setMenuIngredientsR(ArrayList<String> menuIngredientsR) {
        this.menuIngredientsR = menuIngredientsR;
    }

    public String getImageUrlR() {
        return imageUrlR;
    }

    public void setImageUrlR(String imageUrlR) {
        this.imageUrlR = imageUrlR;
    }

    public Menu(String imageUrlL) {

        this.imageUrlL = imageUrlL;
    }


    public String toStringL(){
        return this.menuNameL;
    }
    public String toStringR(){
        return this.menuNameR;
    }
}
