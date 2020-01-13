package android.eservices.webrequests.presentation.drinkdisplay.favorite.adapter;

/**
 * DTO utilisé pour la vue favoris
 */

public class DrinkFavoriteViewModel {

    private String drinkId;
    private String drinkTitle;
    private String drinkThumb;
    private String drinkCategory;
    private String drinkGlass;
    private String drinkInstructions;
    private String drinkDateModified;

    public String getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(String drinkId) {
        this.drinkId = drinkId;
    }

    public String getDrinkTitle() {
        return drinkTitle;
    }

    public void setDrinkTitle(String drinkTitle) {
        this.drinkTitle = drinkTitle;
    }

    public String getDrinkThumb() {
        return drinkThumb;
    }

    public void setDrinkThumb(String drinkThumb) {
        this.drinkThumb = drinkThumb;
    }

    public String getDrinkCategory() {
        return drinkCategory;
    }

    public void setDrinkCategory(String drinkCategory) {
        this.drinkCategory = drinkCategory;
    }

    public String getDrinkGlass() {
        return drinkGlass;
    }

    public void setDrinkGlass(String drinkGlass) {
        this.drinkGlass = drinkGlass;
    }

    public String getDrinkInstructions() {
        return drinkInstructions;
    }

    public void setDrinkInstructions(String drinkInstructions) {
        drinkInstructions = drinkInstructions.replaceAll("\\d.","\r\n");
        this.drinkInstructions = drinkInstructions;
    }

    public String getDrinkDateModified() {
        return drinkDateModified;
    }

    public void setDrinkDateModified(String drinkDateModified) {
        this.drinkDateModified = drinkDateModified;
    }
}
