package android.eservices.webrequests.data.presenter;

import android.eservices.webrequests.data.db.entity.DrinkEntity;
import android.eservices.webrequests.data.repository.DrinkSearchDataRepository;
import android.eservices.webrequests.presentation.drinkdisplay.detail.DrinkDetailContract;
import android.eservices.webrequests.presentation.drinkdisplay.favorite.mapper.DrinkEntityToDetailViewModelMapper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class DrinkDetailPresenter implements DrinkDetailContract.Presenter {

    private DrinkDetailContract.View view;

    private DrinkSearchDataRepository drinkSearchDataRepository;
    private CompositeDisposable compositeDisposable;
    private DrinkEntityToDetailViewModelMapper drinkEntityToDetailViewModelMapper;

    /**
     * Présenter de la partie Détail (Objet Drink unique)
     * @param drinkEntityToDetailViewModelMapper
     * @param drinkSearchDataRepository
     */

    public DrinkDetailPresenter(DrinkEntityToDetailViewModelMapper drinkEntityToDetailViewModelMapper, DrinkSearchDataRepository drinkSearchDataRepository) {
        this.drinkEntityToDetailViewModelMapper = drinkEntityToDetailViewModelMapper;
        this.drinkSearchDataRepository = drinkSearchDataRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void attachView(DrinkDetailContract.View view) {
        this.view = view;
    }

    /**
     * Méthode de récupération d'un cocktail stocké en base en fonction de son ID.
     * @param id
     */
    public void getCocktailById(String id){
        drinkSearchDataRepository.findDrinkEntityById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new ResourceSubscriber<DrinkEntity>() {

                @Override
                public void onNext(DrinkEntity drinkEntity) {
                    view.displayDetail(drinkEntityToDetailViewModelMapper.map(drinkEntity));
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onComplete() {
                    //Do Nothing
                }
            });
    }

    @Override
    public void detachView() {
        this.compositeDisposable.dispose();
        this.view = null;
    }
}
