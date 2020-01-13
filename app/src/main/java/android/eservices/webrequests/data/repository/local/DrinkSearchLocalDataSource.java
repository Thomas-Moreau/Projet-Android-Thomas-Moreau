package android.eservices.webrequests.data.repository.local;

import android.eservices.webrequests.data.db.dao.DrinkEntityDao;
import android.eservices.webrequests.data.db.entity.DrinkEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Objet permettant l'appel aux données locales (Base de données DrinkEntity)
 */
public class DrinkSearchLocalDataSource {

    private DrinkEntityDao drinkEntityDao;

    public DrinkSearchLocalDataSource(DrinkEntityDao drinkEntityDao){
        this.drinkEntityDao = drinkEntityDao;
    }

    public Completable insertDrinkEntity(DrinkEntity drinkEntity){
        return this.drinkEntityDao.insert(drinkEntity);
    }

    public Completable deleteDrinkEntity(DrinkEntity drinkEntity){
        return this.drinkEntityDao.delete(drinkEntity);
    }

    public Flowable<DrinkEntity> findDrinkEntityById(String idDrink){
        return this.drinkEntityDao.findDrinkEntityById(idDrink);
    }

    public Flowable<List<DrinkEntity>> getAllDrinkEntity(){
        return this.drinkEntityDao.getAllDrinkEntity();
    }


}
