package com.example.ontap10;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    private Activity activity;

    private ArrayList<Contact> contactArrayList;

    private LayoutInflater inflater;

    public ContactAdapter(Activity activity, ArrayList<Contact> contactArrayList) {
        this.activity = activity;
        this.contactArrayList = contactArrayList;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return contactArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return contactArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        Contact contact = contactArrayList.get(i);
        return contact.getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=view;
        if(v==null){
            v = inflater.inflate(R.layout.dong_contact, null);
            TextView tvId = v.findViewById(R.id.tvId);
            tvId.setText(String.valueOf(contactArrayList.get(i).getId()));
            TextView tvName = v.findViewById(R.id.tvTen);
            tvName.setText(contactArrayList.get(i).getName());
            TextView tvPhone = v.findViewById(R.id.tvSdt);
            tvPhone.setText(contactArrayList.get(i).getPhone());
        }
        return v;
    }
}
