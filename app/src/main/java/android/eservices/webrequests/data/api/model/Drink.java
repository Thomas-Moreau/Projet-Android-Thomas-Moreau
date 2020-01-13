package android.eservices.webrequests.data.api.model;

public class Drink {

    private String idDrink;

    private String strDrink;
    private String strDrinkThumb; // L'URL de l'image

    private String strCategory;
    private String strGlass;
    private String strInstructions;

    private boolean favorite;

    private String dateModified;

    public String getIdDrink() {
        return idDrink;
    }

    public String getStrDrink() {
        return strDrink;
    }

    public String getStrDrinkThumb() {
        return strDrinkThumb;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrGlass() {
        return strGlass;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public String getDateModified() {
        return dateModified;
    }

    public boolean isFavorite(){
        return favorite;
    }

    public void setFavorite(boolean favorite){
        this.favorite = favorite;
    }

}
