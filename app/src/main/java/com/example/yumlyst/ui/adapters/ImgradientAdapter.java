package com.example.yumlyst.ui.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yumlyst.R;
import com.example.yumlyst.model.Helper;
import com.example.yumlyst.model.IngredientDTO;

import java.util.ArrayList;
import java.util.List;

public class ImgradientAdapter extends RecyclerView.Adapter<ImgradientAdapter.ViewHolder> {
    List<IngredientDTO> dtos;
    private onitemclick onitemclick;

    public ImgradientAdapter(List<IngredientDTO> dtos) {
        this.dtos = new ArrayList<>(dtos);
    }

    public void setOnitemclick(onitemclick onitemclick) {
        this.onitemclick = onitemclick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingradient_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IngredientDTO pdto = dtos.get(position);
        Log.d("ImageURL", "URL: " + pdto.getStrIngredient());
        holder.textView.setText(pdto.getStrIngredient());
        Glide.with(holder.itemView.getContext())
                .load("https://www.themealdb.com/images/ingredients/" + pdto.getStrIngredient() + "-Small.png")
                .placeholder(R.drawable.beef).into(holder.imageView);
        holder.imageView.setBackgroundResource(Helper.getRandomDrawable());
        holder.itemView.setOnClickListener(v -> {
            onitemclick.onclick(pdto);
        });


    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    public void setList(List<IngredientDTO> dtos) {
        this.dtos = dtos;
        notifyDataSetChanged();
    }

    public interface onitemclick {
        void onclick(IngredientDTO ingredientDTO);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            findById(itemView);
        }

        private void findById(@NonNull View itemView) {
            imageView = itemView.findViewById(R.id.ingredient_img);
            textView = itemView.findViewById(R.id.ingredient_name);
            cardView = itemView.findViewById(R.id.ingredient_image_card);
        }

    }
}
