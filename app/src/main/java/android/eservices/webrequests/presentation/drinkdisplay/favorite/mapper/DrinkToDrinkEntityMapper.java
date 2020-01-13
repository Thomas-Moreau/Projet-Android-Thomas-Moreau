package android.eservices.webrequests.presentation.drinkdisplay.favorite.mapper;

import android.eservices.webrequests.data.api.model.Drink;
import android.eservices.webrequests.data.api.model.DrinkSearchResponse;
import android.eservices.webrequests.data.db.entity.DrinkEntity;

public class DrinkToDrinkEntityMapper {

    public DrinkEntity map(Drink drink){
        DrinkEntity drinkEntity = new DrinkEntity();

        drinkEntity.setIdDrink(drink.getIdDrink());
        drinkEntity.setDateModified(drink.getDateModified());
        drinkEntity.setStrCategory(drink.getStrCategory());
        drinkEntity.setStrDrink(drink.getStrDrink());
        drinkEntity.setStrDrinkThumb(drink.getStrDrinkThumb());
        drinkEntity.setStrGlass(drink.getStrGlass());
        drinkEntity.setStrInstructions(drink.getStrInstructions());
        
        return drinkEntity;

    }

    public DrinkEntity map(DrinkSearchResponse drinkSearchResponse){
        return this.map(drinkSearchResponse.getDrinkList().get(0));

    }

}
