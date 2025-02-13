package com.example.yumlyst.ui.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yumlyst.R;
import com.example.yumlyst.model.MealDTO;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    List<MealDTO> dtos;
    private onitemclick onitemclick;

    public SearchAdapter(List<MealDTO> dtos) {
        if (dtos == null)
            Log.d("ali", "aaaaaaaaaa: ");

        this.dtos = new ArrayList<>(dtos);
    }

    public void setOnitemclick(onitemclick onitemclick) {
        this.onitemclick = onitemclick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealDTO pdto = dtos.get(position);
        holder.mealName.setText(pdto.getStrMeal());
        holder.mealCategory.setText(pdto.getStrCategory());
        Glide.with(holder.itemView.getContext()).load(pdto.getStrMealThumb()).placeholder(R.drawable.beef).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    public void setList(List<MealDTO> dtos) {
        this.dtos = dtos;
    }

    public interface onitemclick {
        void onclick(String str);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView mealName;
        TextView mealCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            findById(itemView);
        }

        private void findById(@NonNull View itemView) {
            imageView = itemView.findViewById(R.id.mealImg);
            mealName = itemView.findViewById(R.id.mealName);
            mealCategory = itemView.findViewById(R.id.mealCategory);
        }

    }
}
