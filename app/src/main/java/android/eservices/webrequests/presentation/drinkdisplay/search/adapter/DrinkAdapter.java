package android.eservices.webrequests.presentation.drinkdisplay.search.adapter;

import android.eservices.webrequests.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder> {

    /***
     * Classe de l'adapter lié à la vue de recherche (Listing des cocktails)
     */


    public static class DrinkViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewCategory;
        private TextView textViewId;
        private ImageView imageViewThumb;
        private View v;
        private DrinkItemViewModel drinkItemViewModel;
        private DrinkActionInterface drinkActionInterface;
        private Switch favoriteSwitch;

        public DrinkViewHolder(View v, final DrinkActionInterface drinkActionInterface) {
            super(v);
            this.v = v;
            textViewTitle = v.findViewById(R.id.drink_title_textview);
            textViewCategory = v.findViewById(R.id.drink_category_textview);
            textViewId = v.findViewById(R.id.drink_id_textview);
            imageViewThumb = v.findViewById(R.id.drink_thumb_imageview);
            favoriteSwitch = v.findViewById(R.id.favorite_switch);
            this.drinkActionInterface = drinkActionInterface;
            setupListeners();
        }

        /**
         * Mise en place du listener sur le switch
         */
        private void setupListeners() {
            favoriteSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drinkActionInterface.onFavoriteToggle(drinkItemViewModel.getDrinkId(), favoriteSwitch.isChecked());
                }
            });
        }

        void bind(DrinkItemViewModel drinkItemViewModel) {
            this.drinkItemViewModel = drinkItemViewModel;
            textViewTitle.setText(drinkItemViewModel.getDrinkTitle());
            textViewCategory.setText(drinkItemViewModel.getDrinkCategory());
            textViewId.setText(drinkItemViewModel.getDrinkId());
            textViewId.setVisibility(View.GONE); // On n'affiche pas l'id
            favoriteSwitch.setChecked(drinkItemViewModel.isFavorite());
            // Utilisation de glide pour afficher l'image à partir de l'URL
            Glide.with(v)
                    .load(drinkItemViewModel.getDrinkThumb())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop() // Changement stylistique : affichage en rond
                    .into(imageViewThumb);

        }

    }

    private List<DrinkItemViewModel> drinkItemViewModelList;
    private DrinkActionInterface drinkActionInterface;

    // Provide a suitable constructor (depends on the kind of dataset)
    public DrinkAdapter(DrinkActionInterface drinkActionInterface) {
        drinkItemViewModelList = new ArrayList<>();
        this.drinkActionInterface = drinkActionInterface;
    }

    public void bindViewModels(List<DrinkItemViewModel> drinkItemViewModelList) {
        this.drinkItemViewModelList.clear();
        this.drinkItemViewModelList.addAll(drinkItemViewModelList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DrinkViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_drink, parent, false);
        DrinkAdapter.DrinkViewHolder drinkViewHolder = new DrinkAdapter.DrinkViewHolder(v, drinkActionInterface);
        return drinkViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DrinkViewHolder holder, int position) {
        holder.bind(drinkItemViewModelList.get(position));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return drinkItemViewModelList.size();
    }


}