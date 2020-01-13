package android.eservices.webrequests.presentation.drinkdisplay.detail.fragment;

import android.eservices.webrequests.R;
import android.eservices.webrequests.data.di.FakeDependencyInjectionDrink;
import android.eservices.webrequests.data.presenter.DrinkDetailPresenter;
import android.eservices.webrequests.presentation.drinkdisplay.detail.DrinkDetailContract;
import android.eservices.webrequests.presentation.drinkdisplay.detail.adapter.DetailedDrinkAdapter;
import android.eservices.webrequests.presentation.drinkdisplay.favorite.adapter.DrinkDetailActionInterface;
import android.eservices.webrequests.presentation.drinkdisplay.favorite.adapter.DrinkFavoriteViewModel;
import android.eservices.webrequests.presentation.drinkdisplay.favorite.mapper.DrinkEntityToDetailViewModelMapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DetailFragmentDrink extends Fragment implements DrinkDetailContract.View, DrinkDetailActionInterface {

    /**
     * Fragment lié à la vue de détail.
     */

    public static final String TAB_NAME = "Detail";
    private String idCocktail;
    private View rootView;
    private RecyclerView recyclerView;
    DrinkDetailContract.Presenter drinkDetailPresenter;
    private DetailedDrinkAdapter drinkAdapter;

    private DetailFragmentDrink(String idCocktail) {
        this.idCocktail = idCocktail;
    }

    public static DetailFragmentDrink newInstance(String id) {
        return new DetailFragmentDrink(id);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();

        drinkDetailPresenter = new DrinkDetailPresenter(new DrinkEntityToDetailViewModelMapper(), FakeDependencyInjectionDrink.getDrinkDisplayRepository());
        drinkDetailPresenter.attachView(this);
        // A la création de l'activité, on cherche à récupérer le cocktail selectionné précédemment
        drinkDetailPresenter.getCocktailById(idCocktail);
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view_detail);
        drinkAdapter = new DetailedDrinkAdapter(this);
        recyclerView.setAdapter(drinkAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void displayDetail(DrinkFavoriteViewModel drinkFavoriteViewModel) {
        drinkAdapter.bindViewModels(drinkFavoriteViewModel);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        drinkDetailPresenter.detachView();
    }

    public void onRemoveFavorite(String drinkId){}
}
