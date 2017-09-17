package com.nikpatel.contactlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.nikpatel.contactlist.model.ContactBean;
import com.nikpatel.contactlist.model.SmsModel;

import java.util.ArrayList;

/**
 * Created by nikpatel on 03/09/17.
 */

public class FSMSAdapter extends BaseAdapter {


    Context mContext;
    ArrayList<SmsModel> smsModels ;

    public FSMSAdapter(Context mContext, ArrayList<SmsModel> smsModels) {
        this.mContext = mContext;
        this.smsModels = smsModels;
    }

    @Override
    public int getCount() {
        return smsModels.size();
    }

    @Override
    public Object getItem(int i) {
        return smsModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.alluser_sms_row,viewGroup,false);
        }
        TextView tvAddress = (TextView) view.findViewById(R.id.tvAddress);
        TextView tvId = (TextView) view.findViewById(R.id.tvId);
        TextView tvBody = (TextView) view.findViewById(R.id.tvBody);
        TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
        final SmsModel c =(SmsModel) this.getItem(i);
        tvAddress.setText(c.getAddress());
        tvId.setText(c.getId());
        tvBody.setText(c.getBody());
        tvDate.setText(c.getDate());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,c.getAddress(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
