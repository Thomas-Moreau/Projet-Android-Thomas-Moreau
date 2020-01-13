package android.eservices.webrequests.data.repository.remote;

import android.eservices.webrequests.data.api.model.DrinkSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Méthode de service listant les différentes méthodes fournies par l'API
 */

public interface DrinkService {

    @Headers({
            "x-rapidapi-key: ce94202097mshf7ff97c30836051p15b2cdjsn3bc3c4dbd467",
            "x-rapidapi-host: the-cocktail-db.p.rapidapi.com"
    })
    @GET("filter.php")
    Single<DrinkSearchResponse> getDrinkSearchResponse(@Query("c") String keyWords);


    @Headers({
            "x-rapidapi-key: ce94202097mshf7ff97c30836051p15b2cdjsn3bc3c4dbd467",
            "x-rapidapi-host: the-cocktail-db.p.rapidapi.com"
    })
    @GET("lookup.php")
    Single<DrinkSearchResponse> getDrinkById(@Query("i") String idDrink);
}
