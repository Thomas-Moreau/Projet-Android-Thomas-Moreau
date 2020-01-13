package android.eservices.webrequests.data.presenter;

import android.eservices.webrequests.data.db.entity.DrinkEntity;
import android.eservices.webrequests.data.repository.DrinkSearchDataRepository;
import android.eservices.webrequests.presentation.drinkdisplay.favorite.DrinkFavoriteContract;
import android.eservices.webrequests.presentation.drinkdisplay.favorite.mapper.DrinkEntityToDetailViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class DrinkFavoritePresenter implements DrinkFavoriteContract.Presenter {

    private DrinkFavoriteContract.View view;

    private DrinkSearchDataRepository drinkSearchDataRepository;
    private CompositeDisposable compositeDisposable;
    private DrinkEntityToDetailViewModelMapper drinkEntityToDetailViewModelMapper;

    /**
     * Présenter lié à la deuxième partie de l'écran principal (Favori)
     * @param drinkEntityToDetailViewModelMapper
     * @param drinkSearchDataRepository
     */

    public DrinkFavoritePresenter(DrinkEntityToDetailViewModelMapper drinkEntityToDetailViewModelMapper, DrinkSearchDataRepository drinkSearchDataRepository){
        this.drinkEntityToDetailViewModelMapper = drinkEntityToDetailViewModelMapper;
        this.drinkSearchDataRepository = drinkSearchDataRepository;
        this.compositeDisposable = new CompositeDisposable();
    }


    @Override
    public void attachView(DrinkFavoriteContract.View view) {
        this.view = view;
    }

    /**
     * Méthode de récupération des favoris pour les afficher par la suite
     */
    @Override
    public void getFavorites() {
        compositeDisposable.add(drinkSearchDataRepository.getFavoriteDrinks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<DrinkEntity>>() {

                    @Override
                    public void onNext(List<DrinkEntity> drinkEntityList) {
                        // Si on a des données, on les affiche gràce à un DTO et à un Mapper
                        view.displayFavorites(drinkEntityToDetailViewModelMapper.map(drinkEntityList));
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

    /**
     * Méthode permettant la suppression des favoris
     * @param drinkId
     */
    @Override
    public void removeDrinkFromFavorites(final String drinkId) {
        compositeDisposable.add(drinkSearchDataRepository.removeDrinkFromFavorites(drinkId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onDrinkRemoved();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void detachView() {
        this.compositeDisposable.dispose();
        this.view = null;
    }
}
