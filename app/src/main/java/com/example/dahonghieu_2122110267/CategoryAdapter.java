package com.example.dahonghieu_2122110267;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {
    private final List<CategoryItem> data;

    public CategoryAdapter(List<CategoryItem> data) {
        this.data = data;
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView  name;
        Holder(@NonNull View itemView) {
            super(itemView);
            img  = itemView.findViewById(R.id.imgCat);
            name = itemView.findViewById(R.id.txtCatName);
        }
    }

    @NonNull @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new Holder(v);
    }

    @Override public void onBindViewHolder(@NonNull Holder h, int position) {
        CategoryItem it = data.get(position);
        h.img.setImageResource(it.imageRes);
        h.name.setText(it.name);
    }

    @Override public int getItemCount() { return data.size(); }
}
