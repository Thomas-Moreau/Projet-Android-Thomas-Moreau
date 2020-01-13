package android.eservices.webrequests.presentation.drinkdisplay;

import android.content.Intent;
import android.eservices.webrequests.R;
import android.eservices.webrequests.presentation.drinkdisplay.detail.fragment.DetailFragmentDrink;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class DrinkDetailActivity extends AppCompatActivity {

    /**
     * Activité liée à l'écran de détail
     */

    private ViewPager viewPager;
    private String idCocktail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupView();
        setupViewPagerAndTabs();
    }

    /**
     * Méthode de récupération de l'id envoyé par la vue précedente
     */
    public void setupView(){
        Intent intent = getIntent();
        idCocktail = intent.getStringExtra("id");
    }

    private void setupViewPagerAndTabs() {
        viewPager = findViewById(R.id.tab_viewpagerdetail);
        final DetailFragmentDrink detailFragment = DetailFragmentDrink.newInstance(idCocktail);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return detailFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return detailFragment.TAB_NAME;
            }

            @Override
            public int getCount() {
                return 1;
            }
        });
    }

}
