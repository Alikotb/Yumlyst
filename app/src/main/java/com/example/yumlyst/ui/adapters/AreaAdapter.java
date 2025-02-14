package com.example.yumlyst.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yumlyst.R;
import com.example.yumlyst.model.AreaDTO;
import com.example.yumlyst.model.Helper;

import java.util.ArrayList;
import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ViewHolder> {
    List<AreaDTO> dtos;
    private onitemclick onitemclick;

    public AreaAdapter(List<AreaDTO> dtos) {
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
        AreaDTO pdto = dtos.get(position);
        holder.textView.setText(pdto.getStrArea());
        holder.imageView.setImageResource(Helper.getFlagResourceByName(holder.imageView.getContext(), pdto.getStrArea()));
        holder.itemView.setOnClickListener(v -> {
            onitemclick.onclick(pdto);
        });

    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    public void setList(List<AreaDTO> dtos) {
        this.dtos = dtos;
        notifyDataSetChanged();
    }

    public interface onitemclick {
        void onclick(AreaDTO areaDTO);
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
