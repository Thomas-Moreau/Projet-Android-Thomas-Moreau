package android.eservices.webrequests;

import android.app.Application;
import android.eservices.webrequests.data.di.FakeDependencyInjectionDrink;

import com.facebook.stetho.Stetho;

/**
 * In this sample application, we decided not to use dependency injection.
 * Thus, there are some objects we don't want to recreate, such as retrofit, gson or API services.
 * Here we use the Application class to store our singletons, but in real case, we shall use DI to
 * handle these issues. In other words, please do not do that in production-aimed apps.
 */
public class DrinkApplication extends Application {

    /**
     * Application principale
     */

    public static final String API_HOST = "https://the-cocktail-db.p.rapidapi.com";

    //API_KEY for Cocktail Database
    public static final String API_KEY = "ce94202097mshf7ff97c30836051p15b2cdjsn3bc3c4dbd467";


    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjectionDrink.setContext(this);
    }
}
