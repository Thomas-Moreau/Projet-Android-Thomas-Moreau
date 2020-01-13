package android.eservices.webrequests.data.repository.remote;

import android.eservices.webrequests.data.api.model.DrinkSearchResponse;

import io.reactivex.Single;

/**
 * Objet permettant de récupérer les données à l'aide de l'API (Remote = en ligne)
 */
public class DrinkSearchRemoteDataSource {

    private DrinkService drinkService;

    public DrinkSearchRemoteDataSource(DrinkService drinkService){
        this.drinkService = drinkService;
    }

    public Single<DrinkSearchResponse> getDrinkSearchResponse(String keyWords){
        return this.drinkService.getDrinkSearchResponse(keyWords);
    }

    public Single<DrinkSearchResponse> getDrinkById(String idDrink){
        return this.drinkService.getDrinkById(idDrink);
    }

}
