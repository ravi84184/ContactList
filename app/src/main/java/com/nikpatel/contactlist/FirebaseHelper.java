package com.nikpatel.contactlist;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.nikpatel.contactlist.model.ContactBean;

import java.util.*;
/**
 * Created by nikpatel on 03/09/17.
 */

public class FirebaseHelper {


    DatabaseReference db;
    Boolean saved;
    ArrayList<ContactBean> contactBeens = new ArrayList<>();

    public FirebaseHelper(DatabaseReference db) {

        this.db = db;
    }
    public Boolean contactSave(ArrayList contactBean){
        if (contactBean == null){
            saved = false;
        }
        else {
            try {
                db.child("Mobile").push().child("Contact").setValue(contactBean);
                saved = true;
            }catch (DatabaseException e){
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }
    public Boolean smsSave(ArrayList contactBean){
        if (contactBean == null){
            saved = false;
        }
        else {
            try {
                db.child("Mobile").push().child("SMS").setValue(contactBean);
                saved = true;
            }catch (DatabaseException e){
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }
}
