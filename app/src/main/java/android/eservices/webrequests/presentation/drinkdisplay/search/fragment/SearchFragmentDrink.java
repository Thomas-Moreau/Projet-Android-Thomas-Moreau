package android.eservices.webrequests.presentation.drinkdisplay.search.fragment;

import android.eservices.webrequests.R;
import android.eservices.webrequests.data.di.FakeDependencyInjectionDrink;
import android.eservices.webrequests.data.presenter.DrinkSearchPresenter;
import android.eservices.webrequests.presentation.drinkdisplay.search.DrinkSearchContract;
import android.eservices.webrequests.presentation.drinkdisplay.search.adapter.DrinkActionInterface;
import android.eservices.webrequests.presentation.drinkdisplay.search.adapter.DrinkAdapter;
import android.eservices.webrequests.presentation.drinkdisplay.search.adapter.DrinkItemViewModel;
import android.eservices.webrequests.presentation.drinkdisplay.search.mapper.DrinkToViewModelMapper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchFragmentDrink extends Fragment implements DrinkActionInterface, DrinkSearchContract.View {

    /**
     * Fragment lié à la vue Cocktails
     */

    public static final String TAB_NAME = "Cocktails";
    private View rootView;
    private RecyclerView recyclerView;
    private DrinkAdapter drinkAdapter;
    private DrinkSearchContract.Presenter drinkSearchPresenter;

    private SearchFragmentDrink() {
    }

    public static SearchFragmentDrink newInstance() {
        return new SearchFragmentDrink();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.drinkSearchPresenter = new DrinkSearchPresenter(new DrinkToViewModelMapper(), FakeDependencyInjectionDrink.getDrinkDisplayRepository());
        setupSearchView();
        setupRecyclerView();
        this.drinkSearchPresenter.attachView(this);
    }

    /**
     * Mise en place de la vue.
     * Par soucis de légèreté, on n'affiche que les cocktails dans cette partie
     */
    private void setupSearchView() {
        this.drinkSearchPresenter.searchDrinks("Cocktail");
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        drinkAdapter = new DrinkAdapter(this);
        recyclerView.setAdapter(drinkAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void displayDrinks(List<DrinkItemViewModel> drinkItemViewModelList) {
        drinkAdapter.bindViewModels(drinkItemViewModelList);
    }

    @Override
    /**
     * Méthode appelée au clic sur le switch
     */
    public void onFavoriteToggle(String drinkId, boolean isFavorite) {
        if (isFavorite) {
            drinkSearchPresenter.addDrinkToFavorite(drinkId);
            onDrinkAddedToFavorites();
        } else {
            drinkSearchPresenter.removeDrinkFromFavorites(drinkId);
            onDrinkRemovedFromFavorites();
        }
    }

    @Override
    public void onDrinkAddedToFavorites() {
        Log.i("","add");
    }

    @Override
    public void onDrinkRemovedFromFavorites() {
        Log.i("","remove");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        drinkSearchPresenter.detachView();
    }
}
