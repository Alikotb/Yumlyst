package com.example.yumlyst.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yumlyst.R;
import com.example.yumlyst.helper.BitmapTypeConverter;
import com.example.yumlyst.model.MealDTO;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {
    List<MealDTO> dtos;
    private onitemclick onitemclick;

    public PlanAdapter(List<MealDTO> dtos) {
        this.dtos = dtos;
    }

    public void setOnitemclick(onitemclick onitemclick) {
        this.onitemclick = onitemclick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealDTO pdto = dtos.get(position);
        holder.textView.setText(pdto.getStrArea());
        holder.imageView.setImageBitmap(BitmapTypeConverter.toBitmap(pdto.getBitmap()));
        holder.itemView.setOnClickListener(v -> {
            onitemclick.onclick(pdto);
        });

    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }
    public void removeMeal(MealDTO meal) {
        int position = dtos.indexOf(meal);
        if (position != -1) {
            dtos.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, dtos.size());
        }
    }

    public void setList(List<MealDTO> dtos) {
        this.dtos = dtos;
        notifyDataSetChanged();
    }

    public interface onitemclick {
        void onclick(MealDTO areaDTO);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            findById(itemView);
        }
        private void findById(@NonNull View itemView) {
            imageView = itemView.findViewById(R.id.ingredient_img);
            textView = itemView.findViewById(R.id.category_name);
        }

    }
}
