package com.example.yumlyst.view.adapters;

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

public class DailMealAdapter extends RecyclerView.Adapter<DailMealAdapter.ViewHolder> {
    List<MealDTO> dtos;
    private onitemclick onitemclick;

    public DailMealAdapter(List<MealDTO> dtos) {
        this.dtos = new ArrayList<>(dtos);
    }

    public void setOnitemclick(onitemclick onitemclick) {
        this.onitemclick = onitemclick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_meal_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealDTO pdto = dtos.get(position);
        holder.DailyMealName.setText(pdto.getStrMeal());
        holder.DailyMealcat.setText(pdto.getStrCategory());
        Glide.with(holder.itemView.getContext()).load(pdto.getStrMealThumb()).into(holder.imageView);
        holder.itemView.setOnClickListener(v -> {
            onitemclick.onclick(pdto);
        });
    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    public void setList(List<MealDTO> dtos) {

        this.dtos = dtos;
    }

    public interface onitemclick {
        void onclick(MealDTO mealDTO);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView DailyMealName;
        TextView DailyMealcat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            findById(itemView);
        }

        private void findById(@NonNull View itemView) {
            imageView = itemView.findViewById(R.id.mealImg);
            DailyMealName = itemView.findViewById(R.id.Daily_meal_name);
            DailyMealcat = itemView.findViewById(R.id.Daily_meal_category);

        }

    }
}
