package Entity;

import java.util.ArrayList;

import Util.Constant;

public class Material {

    private String matName;//牛肉
    private String imgName;
    private String NuDetails;
    private ArrayList<Constant.Nutrtions> Nutrition;
    public Material(String matName,String imgName,String NuDetails){
        this.matName=matName;
        this.imgName=imgName;
        this.NuDetails=NuDetails;
        this.Nutrition=new ArrayList<>();
    }
    public void setMatName(String matName) {
        this.matName = matName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public void setNuDetails(String nuDetails) {
        NuDetails = nuDetails;
    }

    public void setNutrition(ArrayList<Constant.Nutrtions> nutrition) {
        Nutrition = nutrition;
    }

    public String getMatName() {

        return matName;
    }

    public String getImgName() {
        return imgName;
    }

    public String getNuDetails() {
        return NuDetails;
    }

    public ArrayList<Constant.Nutrtions> getNutrition() {
        return Nutrition;
    }

}
