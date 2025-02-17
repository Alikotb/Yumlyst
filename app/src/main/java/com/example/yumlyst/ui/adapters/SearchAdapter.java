package com.example.yumlyst.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yumlyst.R;
import com.example.yumlyst.helper.BitmapTypeConverter;
import com.example.yumlyst.helper.Constant;
import com.example.yumlyst.model.MealDTO;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    List<MealDTO> dtos;
    String type;
    private onitemclick onitemclick;
    private OndDeletClick onDeletClick;
    private Forward forward;

    public SearchAdapter(List<MealDTO> dtos, String type) {

        this.dtos = new ArrayList<>(dtos);
        this.type = type;
    }

    public void setForward(Forward forward) {
        this.forward = forward;
    }

    public void setOnitemclick(onitemclick onitemclick, OndDeletClick onDeletClick) {
        this.onitemclick = onitemclick;
        this.onDeletClick = onDeletClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealDTO pdto = dtos.get(position);
        if (type.equals(Constant.SEARCHFRAG)) {
            holder.mealName.setText(pdto.getStrMeal());
            Glide.with(holder.itemView.getContext()).load(pdto.getStrMealThumb()).placeholder(R.drawable.beef).into(holder.imageView);

            holder.itemView.setOnClickListener(v -> {
                onitemclick.onclick(pdto.getIdMeal());

            });
        } else if (type.equals(Constant.FAVORITE)) {
            holder.removeBtn.setVisibility(View.VISIBLE);
            holder.removeBtn.setVisibility(View.VISIBLE);
            holder.mealName.setText(pdto.getStrMeal());
            holder.imageView.setImageBitmap(BitmapTypeConverter.toBitmap(pdto.getBitmap()));
            holder.removeBtn.setOnClickListener(v -> {
                onDeletClick.onclick(pdto);
            });
            holder.itemView.setOnClickListener(v -> {
                forward.onclick(pdto);
            });
        }

    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    public void setList(List<MealDTO> dtos, String type) {
        this.dtos = dtos;
        this.type = type;
        notifyDataSetChanged();
    }

    public void removeMeal(MealDTO meal) {
        int position = dtos.indexOf(meal);
        if (position != -1) {
            dtos.remove(position);
            notifyItemRemoved(position);
        }
    }

    public interface Forward {
        void onclick(MealDTO areaDTO);
    }

    public interface onitemclick {
        void onclick(String id);
    }

    public interface OndDeletClick {
        void onclick(MealDTO meal);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView mealName;
        ;
        Button removeBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            findById(itemView);
        }

        private void findById(@NonNull View itemView) {
            imageView = itemView.findViewById(R.id.local_img);
            mealName = itemView.findViewById(R.id.mealNameAll);
            removeBtn = itemView.findViewById(R.id.removeBtn);
        }

    }
}
