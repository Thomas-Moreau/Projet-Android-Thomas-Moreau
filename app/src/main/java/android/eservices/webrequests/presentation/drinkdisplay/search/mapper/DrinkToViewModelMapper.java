package android.eservices.webrequests.presentation.drinkdisplay.search.mapper;

import android.eservices.webrequests.data.api.model.Drink;
import android.eservices.webrequests.presentation.drinkdisplay.search.adapter.DrinkItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class DrinkToViewModelMapper {

    private DrinkItemViewModel map(Drink drink) {
        DrinkItemViewModel drinkItemViewModel = new DrinkItemViewModel();

        drinkItemViewModel.setDrinkId(drink.getIdDrink());
        drinkItemViewModel.setDrinkCategory(drink.getStrCategory());
        drinkItemViewModel.setDrinkThumb(drink.getStrDrinkThumb());
        drinkItemViewModel.setDrinkTitle(drink.getStrDrink());
        drinkItemViewModel.setFavorite(drink.isFavorite());



        return drinkItemViewModel;
    }

    public List<DrinkItemViewModel> map(List<Drink> drinkList) {
        List<DrinkItemViewModel> drinkItemViewModelList = new ArrayList<>();
        for (Drink drink : drinkList) {
            drinkItemViewModelList.add(map(drink));
        }
        return drinkItemViewModelList;
    }

}
