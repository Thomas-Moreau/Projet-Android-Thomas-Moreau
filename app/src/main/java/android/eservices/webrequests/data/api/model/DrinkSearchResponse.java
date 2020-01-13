package android.eservices.webrequests.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Objet utilisé lors de l'appel à l'API pour récupérer une liste de cocktails
 */
public class DrinkSearchResponse {

    @SerializedName("drinks")
    List<Drink> drinkList;
    public List<Drink> getDrinkList() {
        return drinkList;
    }

}
