package com.example.dahonghieu_2122110267;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter implements ListAdapter {
    ArrayList<SubjectData> arrayList;
    Context context;

    public CustomAdapter(Context context, ArrayList<SubjectData> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(android.database.DataSetObserver observer) { }

    @Override
    public void unregisterDataSetObserver(android.database.DataSetObserver observer) { }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false);
        }

        SubjectData subjectData = arrayList.get(position);

        TextView title = convertView.findViewById(R.id.title);
        ImageView img = convertView.findViewById(R.id.list_image);

        title.setText(subjectData.SubjectName);
        Picasso.get().load(subjectData.Image).into(img);

        // Xử lý click mở link
        convertView.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(subjectData.Link));
            context.startActivity(i);
        });

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0; // tất cả item cùng 1 loại
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }
}
