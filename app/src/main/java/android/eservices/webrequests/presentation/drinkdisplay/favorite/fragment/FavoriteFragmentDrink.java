package android.eservices.webrequests.presentation.drinkdisplay.favorite.fragment;

import android.eservices.webrequests.R;
import android.eservices.webrequests.data.di.FakeDependencyInjectionDrink;
import android.eservices.webrequests.data.presenter.DrinkFavoritePresenter;
import android.eservices.webrequests.data.presenter.DrinkSearchPresenter;
import android.eservices.webrequests.presentation.drinkdisplay.favorite.DrinkFavoriteContract;
import android.eservices.webrequests.presentation.drinkdisplay.favorite.adapter.DrinkDetailActionInterface;
import android.eservices.webrequests.presentation.drinkdisplay.favorite.adapter.DrinkFavoriteAdapter;
import android.eservices.webrequests.presentation.drinkdisplay.favorite.adapter.DrinkFavoriteViewModel;
import android.eservices.webrequests.presentation.drinkdisplay.favorite.mapper.DrinkEntityToDetailViewModelMapper;
import android.eservices.webrequests.presentation.drinkdisplay.search.DrinkSearchContract;
import android.eservices.webrequests.presentation.drinkdisplay.search.mapper.DrinkToViewModelMapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoriteFragmentDrink extends Fragment implements DrinkFavoriteContract.View, DrinkDetailActionInterface {

    /**
     * Fragment favoris
     */

    public static final String TAB_NAME = "Favoris";
    private View rootView;
    private RecyclerView recyclerView;
    private DrinkFavoriteContract.Presenter drinkFavoritePresenter;
    private DrinkSearchContract.Presenter drinkSearchPresenter;
    private DrinkFavoriteAdapter drinkAdapter;

    private FavoriteFragmentDrink() {
    }

    public static FavoriteFragmentDrink newInstance() {
        return new FavoriteFragmentDrink();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        drinkSearchPresenter = new DrinkSearchPresenter(new DrinkToViewModelMapper(), FakeDependencyInjectionDrink.getDrinkDisplayRepository());
        drinkFavoritePresenter = new DrinkFavoritePresenter(new DrinkEntityToDetailViewModelMapper(),FakeDependencyInjectionDrink.getDrinkDisplayRepository());
        drinkFavoritePresenter.attachView(this);
        // A l'affichage, on récupère les favoris
        drinkFavoritePresenter.getFavorites();
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        drinkAdapter = new DrinkFavoriteAdapter(this);
        recyclerView.setAdapter(drinkAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
    }

    @Override
    public void displayFavorites(List<DrinkFavoriteViewModel> drinkFavoriteViewModelList) {
        drinkAdapter.bindViewModels(drinkFavoriteViewModelList);
    }

    @Override
    public void onRemoveFavorite(String drinkId) {
        /*drinkFavoritePresenter.removeDrinkFromFavorites(drinkId);

        System.out.println("Favorite fragment Remove drink " + drinkId);*/
    }

    @Override
    public void onDrinkRemoved() {
        //Do nothing yet
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        drinkFavoritePresenter.detachView();
    }
}
