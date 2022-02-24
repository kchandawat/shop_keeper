package com.marquedo.marquedo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Locale;

//import android.support.annotation.NonNull;

public class MyCustomAdapter extends ArrayAdapter {

    private List contactsInfoList;
    private Context context;

    public MyCustomAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.contactsInfoList = objects;
        this.context = context;
    }

    private class ViewHolder {
        TextView displayName;
        TextView phoneNumber;
        CheckBox contactChecked;
    }
    @Override
    public int getCount() {
        return contactsInfoList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.contact_info, null);

            holder = new ViewHolder();
            holder.displayName = (TextView) convertView.findViewById(R.id.displayName);
            holder.phoneNumber = (TextView) convertView.findViewById(R.id.phoneNumber);
            holder.contactChecked = (CheckBox) convertView.findViewById(R.id.contactChecked);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ContactsInfo contactsInfo = (ContactsInfo) contactsInfoList.get(position);
        holder.displayName.setText(contactsInfo.getDisplayName().substring(0, 2).toUpperCase(Locale.ROOT));
        holder.phoneNumber.setText(contactsInfo.getPhoneNumber());
        holder.contactChecked.setChecked(contactsInfo.getSelected());
        //holder.contactChecked.setTag(1, convertView);
        holder.contactChecked.setTag(position);
        holder.contactChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pos = (Integer) holder.contactChecked.getTag();

                if (contactsInfo.getSelected()) {
                    contactsInfo.setSelected(false);
                } else {
                    contactsInfo.setSelected(true);
                }


            }
        });

        return convertView;
    }
}