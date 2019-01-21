package com.example.aashitachowdary.bakingrecipieapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.aashitachowdary.bakingrecipieapp.BakingRecipie.Ingredient;
import java.util.ArrayList;
class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsHolder> {
    Context context;
    ArrayList<Ingredient> ingredientArrayList;
    public IngredientsAdapter(ItemListActivity itemListActivity, ArrayList<Ingredient> ingredientList) {
        this.context = itemListActivity;
        this.ingredientArrayList = ingredientList;
    }
    @NonNull
    @Override
    public IngredientsAdapter.IngredientsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.ingredient,viewGroup,false);
        return new IngredientsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.IngredientsHolder ingredientsHolder, int i) {
    ingredientsHolder.ingtext.setText(ingredientArrayList.get(i).getIngredient());
    ingredientsHolder.quantitytext.setText(ingredientArrayList.get(i).getQuantity());
    ingredientsHolder.measuretext.setText(ingredientArrayList.get(i).getMeasure());
    }

    @Override
    public int getItemCount() {
        return ingredientArrayList.size();
    }

    public class IngredientsHolder extends RecyclerView.ViewHolder {
        TextView ingtext;
        TextView quantitytext;
        TextView measuretext;
        public IngredientsHolder(@NonNull View itemView) {
            super(itemView);
            ingtext=itemView.findViewById(R.id.ingredients);
            quantitytext=itemView.findViewById(R.id.quantity);
            measuretext=itemView.findViewById(R.id.measure);
        }
    }
}

