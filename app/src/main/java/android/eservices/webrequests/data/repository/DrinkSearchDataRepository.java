package android.eservices.webrequests.data.repository;

import android.eservices.webrequests.data.api.model.DrinkSearchResponse;
import android.eservices.webrequests.data.db.entity.DrinkEntity;
import android.eservices.webrequests.data.repository.local.DrinkSearchLocalDataSource;
import android.eservices.webrequests.data.repository.remote.DrinkSearchRemoteDataSource;
import android.eservices.webrequests.presentation.drinkdisplay.favorite.mapper.DrinkToDrinkEntityMapper;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;

/**
 * Objet permettant de récupérer les informations liées aux cocktails, peu importe comment (Appel à l'API ou appel en base)
 */

public class DrinkSearchDataRepository {

    private DrinkSearchRemoteDataSource drinkSearchRemoteDataSource;
    private DrinkSearchLocalDataSource drinkSearchLocalDataSource;
    private DrinkToDrinkEntityMapper drinkToDrinkEntityMapper;
    private CompositeDisposable compositeDisposable;

    public DrinkSearchDataRepository(DrinkSearchRemoteDataSource drinkSearchRemoteDataSource, DrinkSearchLocalDataSource drinkSearchLocalDataSource,  DrinkToDrinkEntityMapper drinkToDrinkEntityMapper){
        this.drinkSearchRemoteDataSource = drinkSearchRemoteDataSource;
        this.drinkSearchLocalDataSource = drinkSearchLocalDataSource;
        this.drinkToDrinkEntityMapper = drinkToDrinkEntityMapper;
        this.compositeDisposable = new CompositeDisposable();
    }

    /**
     * Recherche d'objets drink en fonction de la catégorie
     * @param keyWords
     * @return
     */
    public Single<DrinkSearchResponse> getDrinkSearchResponse(String keyWords){
        return this.drinkSearchRemoteDataSource.getDrinkSearchResponse(keyWords);
    }

    /**
     * Méthode d'ajout en favori
     * Step 1 : Récupération avec l'API de l'objet
     * Step 2 : On mapppe la réponse à l'aide d'un DTO
     * Step 3 : Ajout en abse de l'objet
     * @param idDrink
     * @return
     */
    public Completable addDrinkFavorite(String idDrink){
        return this.getDrinkById(idDrink).map(new Function<DrinkSearchResponse, DrinkEntity>() {

            public DrinkEntity apply(DrinkSearchResponse drinkSearchResponse) throws Exception {
                return drinkToDrinkEntityMapper.map(drinkSearchResponse);
            }

        }).flatMapCompletable(new Function<DrinkEntity, CompletableSource>() {

            public CompletableSource apply(DrinkEntity drinkEntity) {
                return insertDrinkEntity(drinkEntity);
            }

        });
    }

    public Completable removeDrinkFromFavorites(String idDrink) {
        return this.deleteDrink(idDrink);
    }

    /**
     * Méthode de suppression en favori
     * @param idDrink
     * @return
     */
    public Completable deleteDrink (String idDrink){
        return this.findDrinkEntityById(idDrink).flatMapCompletable(new Function<DrinkEntity, CompletableSource>() {

            public CompletableSource apply(DrinkEntity drinkEntity) {
                System.out.println("Remove du drinkEntity "+drinkEntity.getIdDrink()+" "+drinkEntity.getStrDrink());
                return deleteDrinkEntity(drinkEntity);
            }

        });
    }

    /* DELETE */
    public Completable deleteDrinkEntity(DrinkEntity drinkEntity){
        return this.drinkSearchLocalDataSource.deleteDrinkEntity(drinkEntity);
    }

    public Single<DrinkSearchResponse> getDrinkById(final String idDrink){
       return this.drinkSearchRemoteDataSource.getDrinkById(idDrink);
    }

    public Flowable<DrinkEntity> findDrinkEntityById(String idDrink){
        return this.drinkSearchLocalDataSource.findDrinkEntityById(idDrink);
    }

    /* SELECT */
    public Flowable<List<DrinkEntity>> getAllDrinkEntity(){
        return this.drinkSearchLocalDataSource.getAllDrinkEntity();
    }

    /* INSERT */
    public Completable insertDrinkEntity(DrinkEntity drinkEntity){
        return this.drinkSearchLocalDataSource.insertDrinkEntity(drinkEntity);
    }

    public Flowable<List<DrinkEntity>> getFavoriteDrinks() {
        return this.getAllDrinkEntity();
    }

}
