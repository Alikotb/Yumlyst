package com.example.yumlyst.ui.adapters;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.yumlyst.R;
import com.example.yumlyst.helper.BitmapTypeConverter;
import com.example.yumlyst.helper.Constant;
import com.example.yumlyst.model.MealDTO;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    List<MealDTO> dtos;
    private onitemclick onitemclick;
    private OndDeletClick onDeletClick;
    String type;

    public SearchAdapter(List<MealDTO> dtos,String type) {

        this.dtos = new ArrayList<>(dtos);
        this.type=type;
    }

    public void setOnitemclick(onitemclick onitemclick, OndDeletClick onDeletClick) {
        this.onitemclick = onitemclick;
        this.onDeletClick = onDeletClick;
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
        if(type.equals(Constant.SEARCHFRAG)){
        holder.mealName.setText(pdto.getStrMeal());
        holder.mealCategory.setText(pdto.getStrCategory());
        Glide.with(holder.itemView.getContext()).load(pdto.getStrMealThumb()).placeholder(R.drawable.beef).into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            onitemclick.onclick(pdto.getIdMeal());

        });}
        else if(type.equals(Constant.FAVORITE)){
            holder.removeBtn.setVisibility(View.VISIBLE);
            holder.mealName.setText(pdto.getStrMeal());
            holder.mealCategory.setText(pdto.getStrCategory());
            holder.imageView.setImageBitmap( BitmapTypeConverter.toBitmap(pdto.getBitmap()));
            holder.removeBtn.setOnClickListener(v -> {
                onDeletClick.onclick(pdto);
            });
        }

    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    public void setList(List<MealDTO> dtos ,String type) {
        this.dtos = dtos;
        this.type=type;
        notifyDataSetChanged();
    }

    public interface onitemclick {
        void onclick(String id);
    }
    public void removeMeal(MealDTO meal) {
        int position = dtos.indexOf(meal);
        if (position != -1) {
            dtos.remove(position);
            notifyItemRemoved(position);
        }
    }

    public interface OndDeletClick {
        void onclick(MealDTO meal);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView mealName;
        TextView mealCategory;
        ImageView removeBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            findById(itemView);
        }

        private void findById(@NonNull View itemView) {
            imageView = itemView.findViewById(R.id.mealImg);
            mealName = itemView.findViewById(R.id.mealName);
            mealCategory = itemView.findViewById(R.id.mealCategory);
            removeBtn = itemView.findViewById(R.id.removeBtn);
        }

    }
}
