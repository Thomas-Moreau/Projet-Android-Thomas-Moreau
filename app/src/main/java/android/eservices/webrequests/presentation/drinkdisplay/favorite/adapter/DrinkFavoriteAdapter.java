package android.eservices.webrequests.presentation.drinkdisplay.favorite.adapter;

import android.content.Context;
import android.content.Intent;
import android.eservices.webrequests.R;
import android.eservices.webrequests.presentation.drinkdisplay.DrinkDetailActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class DrinkFavoriteAdapter extends RecyclerView.Adapter<DrinkFavoriteAdapter.DrinkDetailViewHolder> {

    /**
     * Adapter lié au favori
     */

    public static class DrinkDetailViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewId;
        private ImageView imageViewThumb;
        private ImageButton imageButtonInfo;
        private View v;
        private DrinkFavoriteViewModel drinkFavoriteViewModel;
        private DrinkDetailActionInterface drinkDetailActionInterface;

        public DrinkDetailViewHolder(View v, final DrinkDetailActionInterface drinkDetailActionInterface) {
            super(v);
            this.v = v;
            textViewTitle = v.findViewById(R.id.drink_title_textview);
            textViewId = v.findViewById(R.id.drink_id_textview2);
            imageViewThumb = v.findViewById(R.id.drink_thumb_imageview2);
            imageButtonInfo = v.findViewById(R.id.imageButtonInfo);

           // favoriteSwitch = v.findViewById(R.id.favorite_switch2);
            setupListeners();
            this.drinkDetailActionInterface = drinkDetailActionInterface;
        }

        private void setupListeners() {
            imageButtonInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DrinkDetailActivity.class);
                    String id= textViewId.getText().toString() ;
                    intent.putExtra("id",id);
                    context.startActivity(intent);
                }
            });
        }

        void bind(DrinkFavoriteViewModel drinkFavoriteViewModel) {
            this.drinkFavoriteViewModel = drinkFavoriteViewModel;
            textViewTitle.setText(drinkFavoriteViewModel.getDrinkTitle());
            textViewId.setText(drinkFavoriteViewModel.getDrinkId());
            textViewId.setVisibility(View.GONE);
            //favoriteSwitch.setChecked(true);

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
    public DrinkFavoriteAdapter(DrinkDetailActionInterface drinkDetailActionInterface) {
        drinkFavoriteViewModelList = new ArrayList<>();
        this.drinkDetailActionInterface = drinkDetailActionInterface;
    }

    public void bindViewModels(List<DrinkFavoriteViewModel> drinkItemViewModelList) {
        this.drinkFavoriteViewModelList.clear();
        this.drinkFavoriteViewModelList.addAll(drinkItemViewModelList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DrinkDetailViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detailed_drink, parent, false);
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