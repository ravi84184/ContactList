package com.nikpatel.contactlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.nikpatel.contactlist.model.ContactBean;

import java.util.*;
/**
 * Created by nikpatel on 03/09/17.
 */

public class FContactAdapter extends BaseAdapter {


    Context mContext;
    ArrayList<ContactBean> contactBeens;

    public FContactAdapter(Context mContext, ArrayList<ContactBean> contactBeens) {
        this.mContext = mContext;
        this.contactBeens = contactBeens;
    }

    @Override
    public int getCount() {
        return contactBeens.size();
    }

    @Override
    public Object getItem(int i) {
        return contactBeens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.alluser_row,viewGroup,false);
        }
        TextView tvname = (TextView) view.findViewById(R.id.tvname);
        TextView tvPhoneNo = (TextView) view.findViewById(R.id.tvphone);
        final ContactBean c =(ContactBean) this.getItem(i);
        tvname.setText(c.getName());
        tvPhoneNo.setText(c.getPhoneNo());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,c.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
