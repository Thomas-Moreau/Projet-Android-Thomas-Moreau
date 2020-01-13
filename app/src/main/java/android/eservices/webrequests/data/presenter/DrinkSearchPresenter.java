package android.eservices.webrequests.data.presenter;

import android.eservices.webrequests.data.api.model.Drink;
import android.eservices.webrequests.data.api.model.DrinkSearchResponse;
import android.eservices.webrequests.data.db.entity.DrinkEntity;
import android.eservices.webrequests.data.repository.DrinkSearchDataRepository;
import android.eservices.webrequests.presentation.drinkdisplay.search.DrinkSearchContract;
import android.eservices.webrequests.presentation.drinkdisplay.search.mapper.DrinkToViewModelMapper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class DrinkSearchPresenter implements DrinkSearchContract.Presenter{

    private DrinkSearchContract.View view;
    private CompositeDisposable compositeDisposable;
    private DrinkSearchDataRepository repository;
    private DrinkToViewModelMapper mapper;

    /**
     * Présenter lié à l'écran principal lors de l'ouverture de l'application (Liste des cocktails)
     * @param mapper
     * @param repository
     */

    public DrinkSearchPresenter(DrinkToViewModelMapper mapper, DrinkSearchDataRepository repository){
        this.mapper = mapper;
        this.repository = repository;
        this.compositeDisposable = new CompositeDisposable();
    }

    /**
     * Méthode de récupération des données à afficher
     * @param keywords
     */
    @Override
    public void searchDrinks(final String keywords) {
        this.compositeDisposable.clear();
        // Appel à l'API
        compositeDisposable.add(repository.getDrinkSearchResponse(keywords)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<DrinkSearchResponse>() {

                    @Override
                    public void onSuccess(DrinkSearchResponse drinkSearchResponse) {
                        final List<Drink> listDrink = drinkSearchResponse.getDrinkList();
                        // On recherche les données locales pour passer les objets concernés en favori
                        compositeDisposable.add(repository.getAllDrinkEntity().subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new ResourceSubscriber<List<DrinkEntity>>() {
                                    @Override
                                    public void onNext(List<DrinkEntity> drinkEntityList) {
                                        List<String> listIdFavorites = new ArrayList<>();
                                        for(DrinkEntity drinkEntity : drinkEntityList){
                                            listIdFavorites.add(drinkEntity.getIdDrink());
                                        }
                                        for(Drink drink : listDrink){
                                            drink.setFavorite(listIdFavorites.contains(drink.getIdDrink()));
                                        }
                                        // On affiche enfin les données
                                        view.displayDrinks(mapper.map(listDrink));
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        e.printStackTrace();
                                    }

                                    @Override
                                    public void onComplete() {
                                        //Do Nothing
                                    }

                                }));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Search","Impossible de récupérer les boissons avec les keywords "+keywords);
                    }
                }));
    }

    /**
     * Méthode d'ajout en favori à l'aide du switch
     * @param drinkId
     */
    @Override
    public void addDrinkToFavorite(final String drinkId) {
        Log.i("","Tentative d'ajout en favoris "+drinkId+" méthode addDrinkToFavorite");
        this.compositeDisposable.clear();

        // Appel en base pour ajouter l'objet
        this.compositeDisposable.add(repository.addDrinkFavorite(drinkId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver(){

                    @Override
                    public void onComplete() {
                        Log.i("OK", "onComplete, favorite ajouté "+drinkId);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("KO", e.getMessage() );
                        Log.i("KO", "onComplete, impossible d'ajouter le drink ID="+drinkId);
                    }
                }));
    }

    /**
     * Méthode de suppression des favoris à l'aide du switch
     * @param drinkId
     */
    @Override
    public void removeDrinkFromFavorites(final String drinkId) {
        Log.i("","Tentative de remove en favoris "+drinkId+" méthode removeDrinkFromFavorite");
        this.compositeDisposable.clear();

        this.compositeDisposable.add(repository.removeDrinkFromFavorites(drinkId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver(){

                    @Override
                    public void onComplete() {
                        Log.i("OK", "onComplete, favorite supprimé "+drinkId);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("KO", e.getMessage() );
                        Log.i("KO", "onComplete, impossible de supprimer le drink ID="+drinkId);
                    }
                }));
    }

    @Override
    public void attachView(DrinkSearchContract.View view) {
        this.view = view;
    }

    @Override
    public void cancelSubscription() {
        this.compositeDisposable.clear();
    }

    @Override
    public void detachView() {
        this.compositeDisposable.dispose();
        this.view = null;
    }

}
