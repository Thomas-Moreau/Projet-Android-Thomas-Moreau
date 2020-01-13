package android.eservices.webrequests.presentation.drinkdisplay.detail;

import android.eservices.webrequests.presentation.drinkdisplay.favorite.adapter.DrinkFavoriteViewModel;

/**
 * Interface liée au contrat que doivent respecter vue et presenter
 */

public interface DrinkDetailContract {

    interface View {
        void displayDetail(DrinkFavoriteViewModel drinkFavoriteViewModelList);
    }

    interface Presenter {
        void attachView(View view);

        void getCocktailById(String id);

        void detachView();
    }
}
