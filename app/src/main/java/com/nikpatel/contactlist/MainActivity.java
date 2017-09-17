package com.nikpatel.contactlist;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private Button btnContact , btnMessage, btnGPS, btnRecord, btnCallLog, btnGallery;
    private static final int VERIFY_PERMISSIONS_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnContact = (Button) findViewById(R.id.btnContact);
        btnMessage = (Button) findViewById(R.id.btnMessage);
        btnGPS = (Button) findViewById(R.id.btnGPS);
        btnRecord = (Button) findViewById(R.id.btnRecord);
        btnCallLog = (Button) findViewById(R.id.btnCallLog);
        btnGallery = (Button) findViewById(R.id.btnGallery);

        if(checkPermissionsArray(Permissions.PERMISSIONS)){

        }else{
            verifyPermissions(Permissions.PERMISSIONS);
        }
        btnContact.setOnClickListener(this);
        btnMessage.setOnClickListener(this);
        btnGPS.setOnClickListener(this);
        btnRecord.setOnClickListener(this);
        btnCallLog.setOnClickListener(this);
        btnGallery.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnContact:
                startActivity(new Intent(this,ContactListActivity.class));
                break;
            case R.id.btnMessage:
                startActivity(new Intent(this,SMSInboxActivity.class));
                break;

            case R.id.btnGPS:
                startActivity(new Intent(this,GetCurrentGPSLocation.class));
                break;
            case R.id.btnRecord:
                startActivity(new Intent(this,RecordActivity.class));
                break;
            case R.id.btnCallLog:
                startActivity(new Intent(this,CallLogActivity.class));
            case R.id.btnGallery:

        }
    }

    /**
     * verifiy all the permissions passed to the array
     * @param permissions
     */
    public void verifyPermissions(String[] permissions){
        Log.d(TAG, "verifyPermissions: verifying permissions.");

        ActivityCompat.requestPermissions(
                MainActivity.this,
                permissions,
                VERIFY_PERMISSIONS_REQUEST
        );
    }
    /**
     * Check an array of permissions
     * @param permissions
     * @return
     */
    public boolean checkPermissionsArray(String[] permissions){
        Log.d(TAG, "checkPermissionsArray: checking permissions array.");

        for(int i = 0; i< permissions.length; i++){
            String check = permissions[i];
            if(!checkPermissions(check)){
                return false;
            }
        }
        return true;
    }
    /**
     * Check a single permission is it has been verified
     * @param permission
     * @return
     */
    public boolean checkPermissions(String permission){
        Log.d(TAG, "checkPermissions: checking permission: " + permission);

        int permissionRequest = ActivityCompat.checkSelfPermission(MainActivity.this, permission);

        if(permissionRequest != PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "checkPermissions: \n Permission was not granted for: " + permission);
            return false;
        }
        else{
            Log.d(TAG, "checkPermissions: \n Permission was granted for: " + permission);
            return true;
        }
    }
}
