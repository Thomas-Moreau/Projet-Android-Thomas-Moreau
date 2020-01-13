package android.eservices.webrequests.presentation.drinkdisplay.detail.adapter;

import android.content.Context;
import android.content.Intent;
import android.eservices.webrequests.R;
import android.eservices.webrequests.presentation.drinkdisplay.DrinkDisplayActivity;
import android.eservices.webrequests.presentation.drinkdisplay.favorite.adapter.DrinkDetailActionInterface;
import android.eservices.webrequests.presentation.drinkdisplay.favorite.adapter.DrinkFavoriteViewModel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class DetailedDrinkAdapter extends RecyclerView.Adapter<DetailedDrinkAdapter.DrinkDetailViewHolder> {

    /**
     * Objet Adapter lié au fragment de détail
     * C'est ici que se fait le mapping Objet en base -> Objets de l'écran
     */

    public static class DrinkDetailViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewGlass;
        private TextView textViewInstructions;
        private TextView textViewModified;
        private Button buttonRetour;
        private ImageView imageViewThumb;
        private View v;

        /**
         * Constructeur permettant de mappper les objets avec ceux de la vue
         * @param v
         * @param drinkDetailActionInterface
         */
        public DrinkDetailViewHolder(View v, final DrinkDetailActionInterface drinkDetailActionInterface) {
            super(v);
            this.v = v;
            textViewTitle = v.findViewById(R.id.drink_title_textview);
            textViewGlass = v.findViewById(R.id.drink_glass_textview);
            textViewInstructions = v.findViewById(R.id.drink_instructions_textview);
            textViewModified = v.findViewById(R.id.drink_modified_textview);
            imageViewThumb = v.findViewById(R.id.drink_thumb_imageview3);
            buttonRetour = v.findViewById(R.id.button_back);
            setupListeners();
        }

        /**
         * Méthode permettant la mise en place du listener permettant le retour à la précédente activité
         */
        private void setupListeners() {
            buttonRetour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DrinkDisplayActivity.class);
                    context.startActivity(intent);
                }
            });
        }

        /**
         * Binding des informations stockées en base avec les éléments de la vue
         * @param drinkFavoriteViewModel
         */
        void bind(DrinkFavoriteViewModel drinkFavoriteViewModel) {
            textViewTitle.setText(drinkFavoriteViewModel.getDrinkTitle().concat(" (").concat(drinkFavoriteViewModel.getDrinkCategory()).concat(")"));
            textViewGlass.setText("Served with a ".concat(drinkFavoriteViewModel.getDrinkGlass()));
            textViewInstructions.setText("Instructions : ".concat(drinkFavoriteViewModel.getDrinkInstructions()));
            textViewModified.setText(drinkFavoriteViewModel.getDrinkDateModified() != null ? "Last modified (".concat(drinkFavoriteViewModel.getDrinkDateModified()).concat(")") : "");
            Glide.with(v)
                    .load(drinkFavoriteViewModel.getDrinkThumb())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(imageViewThumb);


        }

    }

    private List<DrinkFavoriteViewModel> drinkFavoriteViewModelList;
    private DrinkDetailActionInterface drinkDetailActionInterface;

    // Provide a suitable constructor (depends on the kind of dataset)
    public DetailedDrinkAdapter(DrinkDetailActionInterface drinkDetailActionInterface) {
        drinkFavoriteViewModelList = new ArrayList<>();
        this.drinkDetailActionInterface = drinkDetailActionInterface;
    }

    public void bindViewModels(DrinkFavoriteViewModel drinkItemViewModelList) {
        this.drinkFavoriteViewModelList.clear();
        this.drinkFavoriteViewModelList.add(drinkItemViewModelList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DrinkDetailViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detailed_drink, parent, false);
        DrinkDetailViewHolder drinkDetailViewHolder = new DrinkDetailViewHolder(v, drinkDetailActionInterface);
        return drinkDetailViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DrinkDetailViewHolder holder, int position) {
        holder.bind(drinkFavoriteViewModelList.get(position));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return drinkFavoriteViewModelList.size();
    }


}