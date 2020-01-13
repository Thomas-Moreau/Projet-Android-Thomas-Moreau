package android.eservices.webrequests.presentation.drinkdisplay;

import android.eservices.webrequests.R;
import android.eservices.webrequests.data.db.DrinkDatabase;
import android.eservices.webrequests.data.db.entity.DrinkEntity;
import android.eservices.webrequests.presentation.drinkdisplay.favorite.fragment.FavoriteFragmentDrink;
import android.eservices.webrequests.presentation.drinkdisplay.search.fragment.SearchFragmentDrink;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.viewpager.widget.ViewPager;

public class DrinkDisplayActivity extends AppCompatActivity {

    /**
     * Activité liée à l'écran principal de l'application : Cocktails + Favoris
     */

    private ViewPager viewPager;

    public DrinkDisplayActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViewPagerAndTabs();
    }

    /**
     * Méthode permettant de récupérer, en fonction de la position , la bonne vue
     */
    private void setupViewPagerAndTabs() {
        viewPager = findViewById(R.id.tab_viewpager);

        final FavoriteFragmentDrink fragmentTwo = FavoriteFragmentDrink.newInstance();
        final SearchFragmentDrink searchFragment = SearchFragmentDrink.newInstance();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return searchFragment;
                }
                return fragmentTwo;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return SearchFragmentDrink.TAB_NAME;
                }
                return FavoriteFragmentDrink.TAB_NAME;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }

}
