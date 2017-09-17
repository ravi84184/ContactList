package com.nikpatel.contactlist;

import android.Manifest;

/**
 * Created by User on 7/10/2017.
 */

public class Permissions {

    public static final String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.LOCATION_HARDWARE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAPTURE_AUDIO_OUTPUT,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.WRITE_CALL_LOG

    };

    public static final String[] CAMERA_PERMISSION = {
            Manifest.permission.CAMERA
    };

    public static final String[] WRITE_STORAGE_PERMISSION = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static final String[] READ_STORAGE_PERMISSION = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    public static final String[] READ_CALL_LOG = {
            Manifest.permission.READ_CALL_LOG
    };
    public static final String[] WRITE_CALL_LOG = {
            Manifest.permission.WRITE_CALL_LOG
    };
    public static final String[] CALL_PHONE = {
            Manifest.permission.CALL_PHONE
    };
    public static final String[] READ_SMS = {
            Manifest.permission.READ_SMS
    };
    public static final String[] ACCESS_COARSE_LOCATION = {
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    public static final String[] READ_CONTACTS = {
            Manifest.permission.READ_CONTACTS
    };
    public static final String[] WRITE_CONTACTS = {
            Manifest.permission.WRITE_CONTACTS
    };
    public static final String[] RECORD_AUDIO = {
            Manifest.permission.RECORD_AUDIO
    };
}
