package com.example.bloodlife;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DrawerAdapter extends ArrayAdapter<DrawerData> {
    private BaseActivity activity;
    private ArrayList<String> countData;

    public DrawerAdapter(BaseActivity context, int resource, ArrayList<DrawerData> objects) {
        super(context, resource, objects);
        this.activity = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = newView(parent);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        bindView(position, holder);
        return (convertView);
    }

    private View newView(ViewGroup parent) {
        return (activity.getLayoutInflater().inflate(R.layout.list_item_drawer, parent, false));
    }

    private void bindView(int position, Holder holder) {
        DrawerData data = getItem(position);
        if (data != null) {
            holder.nameTV.setText(data.name);
        }

    }

    private class Holder {
        ImageView iconIV;
        TextView nameTV;

        Holder(View view) {
            this.iconIV = view.findViewById(R.id.imageIV);
            this.nameTV = view.findViewById(R.id.textTV);
        }
    }
}
