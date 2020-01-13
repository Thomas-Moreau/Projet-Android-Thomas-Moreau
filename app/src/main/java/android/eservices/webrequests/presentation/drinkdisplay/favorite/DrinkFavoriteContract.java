package android.eservices.webrequests.presentation.drinkdisplay.favorite;

import android.eservices.webrequests.presentation.drinkdisplay.favorite.adapter.DrinkFavoriteViewModel;

import java.util.List;

public interface DrinkFavoriteContract {

    interface View {
        void displayFavorites(List<DrinkFavoriteViewModel> drinkFavoriteViewModelList);

        void onDrinkRemoved();
    }

    interface Presenter {
        void attachView(View view);

        void getFavorites();

        void removeDrinkFromFavorites(String drinkId);

        void detachView();
    }
}
