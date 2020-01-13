package android.eservices.webrequests.data.db;

import android.eservices.webrequests.data.db.dao.DrinkEntityDao;
import android.eservices.webrequests.data.db.entity.DrinkEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Configuration de la base de données DrinkEntity
 */

@Database(entities = {DrinkEntity.class}, version = 1, exportSchema = false)
public abstract class DrinkDatabase extends RoomDatabase {
    public abstract DrinkEntityDao drinkEntityDao();
}
