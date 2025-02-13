package com.example.yumlyst.ui.adapters;

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
import com.example.yumlyst.model.CategoryDTO;
import com.example.yumlyst.model.Helper;
import com.example.yumlyst.model.MealDTO;

import java.util.ArrayList;
import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {
    List<MealDTO.ingradientmeasure> dtos;
    private onitemclick onitemclick;

    public DetailsAdapter(List<MealDTO.ingradientmeasure> dtos) {
        this.dtos = new ArrayList<>(dtos);
    }

    public void setOnitemclick(onitemclick onitemclick) {
        this.onitemclick = onitemclick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealDTO.ingradientmeasure pdto = dtos.get(position);

        holder.name.setText(pdto.getIngradient());
        holder.measure.setText(pdto.getMeasure());

        Glide.with(holder.itemView.getContext())
                .load("https://www.themealdb.com/images/ingredients/" + pdto.getIngradient() + "-Small.png")
                .placeholder(R.drawable.beef).into(holder.imageView);
        holder.imageView.setBackgroundResource(Helper.getRandomDrawable());


    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    public void setList(List<MealDTO.ingradientmeasure> dtos) {

        this.dtos = dtos;
    }

    public interface onitemclick {
        void onclick(CategoryDTO categoryDTO);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView measure;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            findById(itemView);
        }

        private void findById(@NonNull View itemView) {
            imageView = itemView.findViewById(R.id.hamad3);
            name = itemView.findViewById(R.id.hamada);
            measure = itemView.findViewById(R.id.hamada2);
            cardView = itemView.findViewById(R.id.detais_card);
        }

    }
}
