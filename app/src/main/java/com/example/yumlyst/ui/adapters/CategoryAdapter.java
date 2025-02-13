package com.example.yumlyst.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yumlyst.R;
import com.example.yumlyst.model.CategoryDTO;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<CategoryDTO> dtos;
    private onitemclick onitemclick;

    public CategoryAdapter(List<CategoryDTO> dtos) {
        this.dtos = new ArrayList<>(dtos);
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
        CategoryDTO pdto = dtos.get(position);
        Glide.with(holder.itemView.getContext()).load(pdto.getStrCategoryThumb()).placeholder(R.drawable.beef).into(holder.imageView);
        holder.textView.setText(pdto.getStrCategory());
        holder.itemView.setOnClickListener(v -> {
            onitemclick.onclick(pdto);
        });


    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    public void setList(List<CategoryDTO> dtos) {

        this.dtos = dtos;
    }

    public interface onitemclick {
        void onclick(CategoryDTO categoryDTO);
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
