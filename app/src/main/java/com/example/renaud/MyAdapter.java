package com.example.renaud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<MyItem> listforview;
    LayoutInflater inflator = null;
    View v;
    ViewHolder vholder;

    public MyAdapter(Context con, ArrayList<MyItem> list) {
        super();
        context = con;
        listforview = list;
        inflator = LayoutInflater.from(con);
    }

    public void addItem(String item) {

        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return listforview.size();
    }

    @Override
    public Object getItem(int position) {
        return listforview.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        v = convertView;
        if (convertView == null) {
            v = inflator.inflate(R.layout.myadapter, null);
            vholder = new ViewHolder();
            vholder.name = (TextView) v.findViewById(R.id.name);
            vholder.surname = (TextView) v.findViewById(R.id.surname);
            vholder.lit = v.findViewById(R.id.lit);
            v.setTag(vholder);
        } else
            vholder = (ViewHolder) v.getTag();
        MyItem item = (MyItem) listforview.get(position);
        vholder.name.setText(item.getName());
        vholder.surname.setText(item.getSurname());
        vholder.lit.setText(item.getTelephone());
        return v;
    }

    private class ViewHolder {
        TextView name, surname, lit;

    }
}
