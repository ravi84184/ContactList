package com.nikpatel.contactlist;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetCurrentGPSLocation extends AppCompatActivity implements LocationListener{

    private static final String TAG = "GetCurrentGPSLocation";
    LocationManager locationManager;
    String mprovider;
    TextView longitude;
    TextView latitude;
    ProgressBar progressBar;
    private TextView infoText;
    double  longi;
    double  lati;
    Button btnResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_current_gpslocation);
        Log.e(TAG, "onCreate: Start------" );
        infoText = (TextView) findViewById(R.id.tvAddress);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        btnResult = (Button) findViewById(R.id.btnresult);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: ----------- " );
                getAddress(GetCurrentGPSLocation.this,lati,longi);

            }
        });
        Criteria criteria = new Criteria();

        mprovider = locationManager.getBestProvider(criteria, false);

        if (mprovider != null && !mprovider.equals("")) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = locationManager.getLastKnownLocation(mprovider);
            locationManager.requestLocationUpdates(mprovider, 15000, 1, this);


            if (location != null) {
                Log.e(TAG, "onCreate:--------- " +location.toString());
                onLocationChanged(location);
                new GeocodeAsyncTask().execute();
                getAddress(this,lati,longi);
            }
            else{
                Toast.makeText(getBaseContext(), "No Location Provider Found Check Your Code", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void getAddress(Context context, double LATITUDE, double LONGITUDE) {
        progressBar.setVisibility(View.VISIBLE);

        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null && addresses.size() > 0) {



                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                Log.d(TAG, "getAddress:  country ---" +country);
                Log.d(TAG, "getAddress:  address ---" + address);
                Log.d(TAG, "getAddress:  city ---" + city);
                Log.d(TAG, "getAddress:  state ---" + state);
                Log.d(TAG, "getAddress:  postalCode ---" + postalCode);
                Log.d(TAG, "getAddress:  knownName ---" + knownName);
                infoText.setVisibility(View.VISIBLE);
                infoText.setText(knownName+"\n"+postalCode+"\n"+state+"\n"+city+"\n"+address+"\n"+country);
                progressBar.setVisibility(View.GONE);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public void onLocationChanged(Location location) {

        longitude = (TextView) findViewById(R.id.tvLong);
        latitude = (TextView) findViewById(R.id.tvLati);

        longitude.setText("Current Longitude:" + location.getLongitude());
        latitude.setText("Current Latitude:" + location.getLatitude());
        longi = location.getLongitude();
        lati = location.getLatitude();
        Log.e(TAG, "onLocationChanged: Current Longitude:" + location.getLongitude() );
        Log.e(TAG, "onLocationChanged: Current Latitude:" + location.getLatitude() );

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    class GeocodeAsyncTask extends AsyncTask<Void, Void, Address> {

        String errorMessage = "";

        @Override
        protected void onPreExecute() {
            infoText.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Address doInBackground(Void ... none) {
            Log.e(TAG, "doInBackground: ------- " );
            Geocoder geocoder = new Geocoder(GetCurrentGPSLocation.this, Locale.getDefault());
            List<Address> addresses = null;
            double latitude = lati;
            double longitude = longi;

            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
            } catch (IOException ioException) {
                errorMessage = "Service Not Available";
                Log.e(TAG, errorMessage, ioException);
            } catch (IllegalArgumentException illegalArgumentException) {
                errorMessage = "Invalid Latitude or Longitude Used";
                Log.e(TAG, errorMessage + ". " +
                        "Latitude = " + latitude + ", Longitude = " +
                        longitude, illegalArgumentException);
            }
            if(addresses != null && addresses.size() > 0)
                return addresses.get(0);

            return null;
        }

        protected void onPostExecute(Address address) {
            if(address == null) {
                progressBar.setVisibility(View.INVISIBLE);
                infoText.setVisibility(View.VISIBLE);
                infoText.setText(errorMessage);
            }
            else {
                Log.e(TAG, "onPostExecute:----- " );
                String addressName = "";
                for(int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    addressName += " --- " + address.getAddressLine(i);
                }
                progressBar.setVisibility(View.INVISIBLE);
                //infoText.setVisibility(View.VISIBLE);
                //infoText.setText("Address: " + addressName);
            }
        }
    }
}
