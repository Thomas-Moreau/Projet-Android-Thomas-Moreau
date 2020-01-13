package android.eservices.webrequests.data.db.dao;

import android.eservices.webrequests.data.db.entity.DrinkEntity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Objet DAO qui permet les intéractions avec la base en local
 */

@Dao
public interface DrinkEntityDao {

    @Query("SELECT * FROM DrinkEntity")
    Flowable<List<DrinkEntity>> getAllDrinkEntity();

    @Query("SELECT * FROM DrinkEntity WHERE idDrink = :idDrink")
    Flowable<DrinkEntity> findDrinkEntityById(String idDrink);

    @Insert
    Completable insert(DrinkEntity drinkEntity);

    @Delete
    Completable delete(DrinkEntity drinkEntity);
}
