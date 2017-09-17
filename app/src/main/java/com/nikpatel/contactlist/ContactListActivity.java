package com.nikpatel.contactlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nikpatel.contactlist.model.ContactBean;

public class ContactListActivity extends Activity {

	private static final String TAG = "ContactListActivity";
	private ListView listView;
	private ArrayList<ContactBean> list = new ArrayList<ContactBean>();
	private FirebaseHelper helper;
	private DatabaseReference db;
	private ContactBean contactBeans;
	private Cursor phones;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		listView = (ListView) findViewById(R.id.list);
//		listView.setOnItemClickListener(this);

		phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
		while (phones.moveToNext()) {

			String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			Log.e(TAG, "onCreate: Name: "+name );
			String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			Log.e(TAG, "onCreate: Number: "+phoneNumber );
			ContactBean objContact = new ContactBean();
			objContact.setName(name);
			objContact.setPhoneNo(phoneNumber);
			list.add(objContact);
		}
		phones.close();
		/*add data in firebase*/
		db = FirebaseDatabase.getInstance().getReference();
		helper = new FirebaseHelper(db);
		Boolean save = helper.contactSave(getItems());

		/*set adapter in list view*/
		FContactAdapter adapter = new FContactAdapter(this,list);
		listView.setAdapter(adapter);

		if (null != list && list.size() != 0) {
			Collections.sort(list, new Comparator<ContactBean>() {

				@Override
				public int compare(ContactBean lhs, ContactBean rhs) {
					return lhs.getName().compareTo(rhs.getName());
				}
			});
//			AlertDialog alert = new AlertDialog.Builder(
//					ContactListActivity.this).create();
//			alert.setTitle("");
//
//			alert.setMessage(list.size() + " Contact Found!!!");
//
//			alert.setButton("OK", new DialogInterface.OnClickListener() {
//
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					dialog.dismiss();
//				}
//			});
//			alert.show();

		} else {
			showToast("No Contact Found!!!");
		}
	}
	private ArrayList getItems(){
		ArrayList<ContactBean> arrayList = new ArrayList<>();
		contactBeans = new ContactBean();
		phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
		while (phones.moveToNext()) {
			String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			contactBeans = new ContactBean();
			contactBeans.setName(name);
			contactBeans.setPhoneNo(phoneNumber);
			arrayList.add(contactBeans);
		}
		return arrayList;
	}

	private void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

//	@Override
//	public void onItemClick(AdapterView<?> listview, View v, int position,
//							long id) {
//		ContactBean bean = (ContactBean) listview.getItemAtPosition(position);
//		showCallDialog(bean.getName(), bean.getPhoneNo());
//	}

//	private void showCallDialog(String name, final String phoneNo) {
//		AlertDialog alert = new AlertDialog.Builder(ContactListActivity.this)
//				.create();
//		alert.setTitle("Call?");
//
//		alert.setMessage("Are you sure want to call " + name + " ?");
//
//		alert.setButton("No", new DialogInterface.OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				dialog.dismiss();
//			}
//		});
//		alert.setButton2("Yes", new DialogInterface.OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				String phoneNumber = "tel:" + phoneNo;
//				Intent intent = new Intent(Intent.ACTION_CALL, Uri
//						.parse(phoneNumber));
//				if (ActivityCompat.checkSelfPermission(ContactListActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//					// TODO: Consider calling
//					//    ActivityCompat#requestPermissions
//					// here to request the missing permissions, and then overriding
//					//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//					//                                          int[] grantResults)
//					// to handle the case where the user grants the permission. See the documentation
//					// for ActivityCompat#requestPermissions for more details.
//					return;
//				}
//				startActivity(intent);
//			}
//		});
//		alert.show();
//	}
}
