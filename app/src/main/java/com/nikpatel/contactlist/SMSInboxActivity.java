package com.nikpatel.contactlist;

import android.app.Activity;

import java.sql.Date;
import java.util.ArrayList;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.ArrayAdapter;
import android.widget.ListView;import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nikpatel.contactlist.model.SmsModel;

public class SMSInboxActivity extends Activity {

    private FirebaseHelper helper;
    private DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsinbox);
        ListView lViewSMS = (ListView) findViewById(R.id.listViewSMS);
        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);
        if(fetchInbox()!=null)
        {
            FSMSAdapter adapter = new FSMSAdapter(this,fetchInbox());
            lViewSMS.setAdapter(adapter);
            Boolean save = helper.smsSave(fetchInbox());
        }
    }
    public ArrayList fetchInbox()
    {
        ArrayList arrayList = new ArrayList();
        Uri uriSms = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"},null,null,null);
        cursor.moveToFirst();
        while  (cursor.moveToNext())
        {
            SmsModel model = new SmsModel();
            String id = cursor.getString(0);
            String address = cursor.getString(1);
            String date = cursor.getString(2);
            String body = cursor.getString(3);
            long millisecond = Long.parseLong(date);
            String dateString = DateFormat.format("dd/MM/yyyy", new Date(millisecond)).toString();
            model.setId(id);
            model.setAddress(address);
            model.setDate(dateString);
            model.setBody(body);

            arrayList.add(model);
        }
        return arrayList;

    }
}
