package android.eservices.webrequests.data.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Objet Entity qui représente la base de données
 */

@Entity
public class DrinkEntity {

    @PrimaryKey
    @NonNull
    private String idDrink;

    @ColumnInfo(name = "strDrink")
    private String strDrink;

    @ColumnInfo(name = "strDrinkThumb")
    private String strDrinkThumb;

    @ColumnInfo(name = "strCategory")
    private String strCategory;

    @ColumnInfo(name = "strGlass")
    private String strGlass;

    @ColumnInfo(name = "strInstructions")
    private String strInstructions;

    @ColumnInfo(name = "dateModified")
    private String dateModified;

    @NonNull
    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(@NonNull String idDrink) {
        this.idDrink = idDrink;
    }

    public String getStrDrink() {
        return strDrink;
    }

    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }

    public String getStrDrinkThumb() {
        return strDrinkThumb;
    }

    public void setStrDrinkThumb(String strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrGlass() {
        return strGlass;
    }

    public void setStrGlass(String strGlass) {
        this.strGlass = strGlass;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

}
