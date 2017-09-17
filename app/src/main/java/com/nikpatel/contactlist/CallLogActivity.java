package com.nikpatel.contactlist;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class CallLogActivity extends AppCompatActivity {
    private static final String TAG = "CallLogActivity";
    private static final int VERIFY_PERMISSIONS_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);
        Log.e(TAG, "onCreate: start");

        StringBuffer sb = new StringBuffer();
        Cursor c = managedQuery(CallLog.Calls.CONTENT_URI,null,null,null,null);
        int number = c.getColumnIndex(CallLog.Calls.NUMBER);
        int type = c.getColumnIndex(CallLog.Calls.TYPE);
        int date = c.getColumnIndex(CallLog.Calls.DATE);
        int duration = c.getColumnIndex(CallLog.Calls.DURATION);
        Log.e(TAG, "getCallDeatils: Starte" );
        sb.append("Call Details:\n\n");

        while (c.moveToNext()) {
            String phNumber = c.getString(number);
            String calltype = c.getString(type);

            String calldate = c.getString(date);
            Date calldaytime = new Date(Long.valueOf(calldate));
            SimpleDateFormat formate = new SimpleDateFormat("dd-MM-yy HH-mm");
            String datestring = formate.format(calldaytime);

            String callduration = c.getString(duration);

            String dir = null;

            int dircode = Integer.parseInt(calltype);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;

            }
            Log.e(TAG, "getCallDeatils: \nPhone Number: " + phNumber + "\nCallType: " + dir + "\nCall Date: " + datestring + "\nCall Dyration: " + callduration);
            sb.append("\nPhone Number: " + phNumber + "\nCallType: " + dir + "\nCall Date: " + datestring + "\nCall Dyration: " + callduration);
            sb.append("\n---------------------------------------");
        }

        TextView textView = (TextView) findViewById(R.id.txtcall);
        textView.setText(sb);
//        if(checkPermissionsArray(Permissions.PERMISSIONS)){
//            TextView textView = (TextView) findViewById(R.id.txtcall);
//            textView.setText(getCallDeatils());
//        }else{
//            verifyPermissions(Permissions.PERMISSIONS);
//        }

//        if (ContextCompat.checkSelfPermission(CallLogActivity.this,
//                Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(CallLogActivity.this,
//                    Manifest.permission.READ_CALL_LOG)) {
//                ActivityCompat.requestPermissions(CallLogActivity.this,
//                        new String[]{Manifest.permission.READ_CALL_LOG}, 1);
//            } else {
//                ActivityCompat.requestPermissions(CallLogActivity.this,
//                        new String[]{Manifest.permission.READ_CALL_LOG}, 1);
//            }
//        } else {
//
//        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        switch (requestCode) {
//            case 1:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    if (ContextCompat.checkSelfPermission(CallLogActivity.this,
//                            Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
//                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
//                        TextView textView = (TextView) findViewById(R.id.txtcall);
//                        textView.setText(getCallDeatils());
//
//                    }
//                } else {
//                    Toast.makeText(this, "No Permission Granted", Toast.LENGTH_SHORT).show();
//                }
//                return;
//        }
//    }

//    private String getCallDeatils() {
//        StringBuffer sb = new StringBuffer();
//        if (ContextCompat.checkSelfPermission(CallLogActivity.this,
//                Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(CallLogActivity.this,
//                    Manifest.permission.READ_CALL_LOG)) {
//                ActivityCompat.requestPermissions(CallLogActivity.this,
//                        new String[]{Manifest.permission.READ_CALL_LOG}, 1);
//            } else {
//                ActivityCompat.requestPermissions(CallLogActivity.this,
//                        new String[]{Manifest.permission.READ_CALL_LOG}, 1);
//            }
//        } else {
//
//        }
//        Cursor c = getContentResolver().query(CallLog.CONTENT_URI, null, null, null, null);
//        int number = c.getColumnIndex(CallLog.Calls.NUMBER);
//        int type = c.getColumnIndex(CallLog.Calls.TYPE);
//        int date = c.getColumnIndex(CallLog.Calls.DATE);
//        int duration = c.getColumnIndex(CallLog.Calls.DURATION);
//        Log.e(TAG, "getCallDeatils: Starte" );
//        sb.append("Call Details:\n\n");
//
//        while (c.moveToNext()) {
//            String phNumber = c.getString(number);
//            String calltype = c.getString(type);
//            String calldate = c.getString(date);
//            Date calldaytime = new Date(Long.valueOf(calldate));
//            SimpleDateFormat formate = new SimpleDateFormat("dd-MM-yy HH-mm");
//            String datestring = formate.format(calldaytime);
//            String callduration = c.getString(duration);
//            String dir = null;
//
//            int dircode = Integer.parseInt(calltype);
//            switch (dircode) {
//                case CallLog.Calls.OUTGOING_TYPE:
//                    dir = "OUTGOING";
//                    break;
//                case CallLog.Calls.INCOMING_TYPE:
//                    dir = "INCOMING";
//                    break;
//                case CallLog.Calls.MISSED_TYPE:
//                    dir = "MISSED";
//                    break;
//
//            }
//            Log.e(TAG, "getCallDeatils: \nPhone Number: " + phNumber + "\nCallType: " + dir + "\nCall Date: " + datestring + "\nCall Dyration: " + callduration);
//            sb.append("\nPhone Number: " + phNumber + "\nCallType: " + dir + "\nCall Date: " + datestring + "\nCall Dyration: " + callduration);
//            sb.append("\n---------------------------------------");
//        }
//        c.close();;
//        return sb.toString();
//    }
//    /**
//     * verifiy all the permissions passed to the array
//     * @param permissions
//     */
//    public void verifyPermissions(String[] permissions){
//        Log.d(TAG, "verifyPermissions: verifying permissions.");
//
//        ActivityCompat.requestPermissions(
//                CallLogActivity.this,
//                permissions,
//                VERIFY_PERMISSIONS_REQUEST
//        );
//    }
//    /**
//     * Check an array of permissions
//     * @param permissions
//     * @return
//     */
//    public boolean checkPermissionsArray(String[] permissions){
//        Log.d(TAG, "checkPermissionsArray: checking permissions array.");
//
//        for(int i = 0; i< permissions.length; i++){
//            String check = permissions[i];
//            if(!checkPermissions(check)){
//                return false;
//            }
//        }
//        return true;
//    }
//    /**
//     * Check a single permission is it has been verified
//     * @param permission
//     * @return
//     */
//    public boolean checkPermissions(String permission){
//        Log.d(TAG, "checkPermissions: checking permission: " + permission);
//
//        int permissionRequest = ActivityCompat.checkSelfPermission(CallLogActivity.this, permission);
//
//        if(permissionRequest != PackageManager.PERMISSION_GRANTED){
//            Log.d(TAG, "checkPermissions: \n Permission was not granted for: " + permission);
//            return false;
//        }
//        else{
//            Log.d(TAG, "checkPermissions: \n Permission was granted for: " + permission);
//            return true;
//        }
//    }
}
