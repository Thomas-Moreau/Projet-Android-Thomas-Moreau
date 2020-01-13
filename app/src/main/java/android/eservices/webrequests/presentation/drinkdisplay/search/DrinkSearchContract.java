package android.eservices.webrequests.presentation.drinkdisplay.search;

import android.eservices.webrequests.presentation.drinkdisplay.search.adapter.DrinkItemViewModel;

import java.util.List;

public interface DrinkSearchContract {

    interface View {
        void displayDrinks(List<DrinkItemViewModel> drinkItemViewModelList);

        void onDrinkAddedToFavorites();

        void onDrinkRemovedFromFavorites();
    }

    interface Presenter {
        void searchDrinks(String keywords);

        void attachView(DrinkSearchContract.View view);

        void cancelSubscription();

        void addDrinkToFavorite(String drinkId);

        void removeDrinkFromFavorites(String drinkId);

        void detachView();
    }

}
