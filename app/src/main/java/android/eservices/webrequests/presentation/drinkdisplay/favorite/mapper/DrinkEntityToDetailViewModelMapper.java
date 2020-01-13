package android.eservices.webrequests.presentation.drinkdisplay.favorite.mapper;


import android.eservices.webrequests.data.db.entity.DrinkEntity;
import android.eservices.webrequests.presentation.drinkdisplay.favorite.adapter.DrinkFavoriteViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper utilisé pour passer d'un objet en base à l'objet dont on se sert pour afficher les informations sur la vue favoriss
 */

public class DrinkEntityToDetailViewModelMapper {

    public List<DrinkFavoriteViewModel> map(List<DrinkEntity> drinkEntityList) {
        List<DrinkFavoriteViewModel> drinkFavoriteViewModelList = new ArrayList<>();

        for(DrinkEntity drinkEntity : drinkEntityList ){
            drinkFavoriteViewModelList.add(map(drinkEntity));
        }

        return drinkFavoriteViewModelList;
    }

    public DrinkFavoriteViewModel map(DrinkEntity drinkEntity) {
        DrinkFavoriteViewModel drinkItemViewModel  = new DrinkFavoriteViewModel();
        drinkItemViewModel.setDrinkId(drinkEntity.getIdDrink());
        drinkItemViewModel.setDrinkCategory(drinkEntity.getStrCategory());
        drinkItemViewModel.setDrinkThumb(drinkEntity.getStrDrinkThumb());
        drinkItemViewModel.setDrinkTitle(drinkEntity.getStrDrink());
        drinkItemViewModel.setDrinkGlass(drinkEntity.getStrGlass());
        drinkItemViewModel.setDrinkInstructions(drinkEntity.getStrInstructions());
        drinkItemViewModel.setDrinkDateModified(drinkEntity.getDateModified());

        return drinkItemViewModel;
    }

    private String languageMapper(String input) {
        switch (input) {
            case "en":
                return "English";
            case "fr":
                return "French";
            default:
                return "Unknown (" + input + ")";
        }
    }
}
