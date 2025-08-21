package com.example.vanbaliem_2122110304;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private final Context context;
    private final List<SubjectData> data;
    private final LayoutInflater inflater;

    public CustomAdapter(Context context, List<SubjectData> data) {
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override public int getCount() { return data.size(); }
    @Override public Object getItem(int position) { return data.get(position); }
    @Override public long getItemId(int position) { return position; }

    static class ViewHolder {
        ImageView img;
        TextView  title;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder h;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row, parent, false);
            h = new ViewHolder();
            h.img = convertView.findViewById(R.id.list_image);
            h.title = convertView.findViewById(R.id.title);
            convertView.setTag(h);
        } else {
            h = (ViewHolder) convertView.getTag();
        }

        SubjectData item = data.get(position);
        h.title.setText(item.SubjectName);

        Picasso.get()
                .load(item.Image)
                .placeholder(android.R.drawable.ic_menu_report_image)
                .error(android.R.drawable.ic_delete)
                .fit()              // co ảnh theo kích thước view
                .centerCrop()
                .into(h.img);

        return convertView;
    }
}
