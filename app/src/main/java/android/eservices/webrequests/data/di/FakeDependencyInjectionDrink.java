package android.eservices.webrequests.data.di;

import android.content.Context;
import android.eservices.webrequests.data.db.DrinkDatabase;
import android.eservices.webrequests.data.db.dao.DrinkEntityDao;
import android.eservices.webrequests.data.repository.DrinkSearchDataRepository;
import android.eservices.webrequests.data.repository.local.DrinkSearchLocalDataSource;
import android.eservices.webrequests.data.repository.remote.DrinkSearchRemoteDataSource;
import android.eservices.webrequests.data.repository.remote.DrinkService;
import android.eservices.webrequests.presentation.drinkdisplay.favorite.mapper.DrinkToDrinkEntityMapper;

import androidx.room.Room;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
    Utilisation d'un FakeDependency comme dans le TP API Books
 */
public class FakeDependencyInjectionDrink {

    private static Retrofit retrofit;
    private static Gson gson;

    private static DrinkService drinkService;
    private static DrinkEntityDao drinkEntityDao;

    private static Context applicationContext;
    private static DrinkSearchDataRepository drinkSearchDataRepository;
    private static DrinkDatabase drinkDatabase;

    public static DrinkSearchDataRepository getDrinkDisplayRepository(){
        if(drinkSearchDataRepository == null){
            drinkSearchDataRepository = new DrinkSearchDataRepository(new DrinkSearchRemoteDataSource(getDrinkService()), new DrinkSearchLocalDataSource(getDrinkEntityDao()), new DrinkToDrinkEntityMapper());
        }
        return drinkSearchDataRepository;
    }

    /**
     * Méthode de récupération de l'objet permettant l'utilisation de l'API
     * @return
     */
    public static DrinkService getDrinkService(){
        if(drinkService == null){
            drinkService = getRetrofit().create(DrinkService.class);
        }
        return drinkService;
    }

    public static DrinkEntityDao getDrinkEntityDao(){
        if(drinkEntityDao == null){
            drinkEntityDao = getDrinkDatabase().drinkEntityDao();
        }
        return drinkEntityDao;
    }

    /**
     * Récupération de l'objet rétrofit permettant l'instanciation à l'API
     * @return
     */
    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://the-cocktail-db.p.rapidapi.com/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }

    /**
     * Création de la base de données.
     * C'est ici que l'on aurait pu migrer la base vers une autre version, en ajoutant d'autres informations en base.
     * @return
     */
    public static DrinkDatabase getDrinkDatabase(){
        if(drinkDatabase == null){
            drinkDatabase = Room.databaseBuilder(applicationContext,DrinkDatabase.class, "database-name").build();
        }
        return drinkDatabase;
    }

}
