package com.example.vanbaliem_2122110304;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Holder> {
    public interface OnProductClick { void onOpen(ProductItem item); }

    private final List<ProductItem> data;
    private final OnProductClick onProductClick;

    public ProductAdapter(List<ProductItem> data, OnProductClick onProductClick) {
        this.data = data;
        this.onProductClick = onProductClick;
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView  name, price;
        Button    btnAdd;
        Holder(@NonNull View itemView) {
            super(itemView);
            img   = itemView.findViewById(R.id.imgProduct);
            name  = itemView.findViewById(R.id.txtProductName);
            price = itemView.findViewById(R.id.txtProductPrice);
            btnAdd= itemView.findViewById(R.id.btnAddToCart);
        }
    }

    @NonNull @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new Holder(v);
    }

    @Override public void onBindViewHolder(@NonNull Holder h, int position) {
        ProductItem it = data.get(position);
        h.img.setImageResource(it.imageRes);
        h.name.setText(it.name);
        h.price.setText(it.price);
        h.btnAdd.setOnClickListener(v -> {
            if (onProductClick != null) onProductClick.onOpen(it);
        });
    }

    @Override public int getItemCount() { return data.size(); }
}
