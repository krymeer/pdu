package czernik.osada.placezabaw;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import java.io.IOException;
import java.util.Locale;

class FusedLocator implements ActivityCompat.OnRequestPermissionsResultCallback {
    private FusedLocationProviderClient fusedLocationClient;
    private Context context;
    private TextView textView;
    private Activity activity;
    private LocationCallback locationCallback;
    private boolean locationFound;

    FusedLocator(Context context, Activity activity, TextView textView) {
        this.activity       = activity;
        this.context        = context;
        this.textView       = textView;
        locationFound       = false;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        locationCallback    = new LocationCallback() {
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null)
                {
                    return;
                }

                Location location   = locationResult.getLocations().get( 0 );
                locationFound       = true;
                fusedLocationClient.removeLocationUpdates(locationCallback);
                setLocation(location);
            }
        };
    }

    private void setLocation(Location location) {
        if (this.textView != null)
        {
            try
            {
                Geocoder geocoder   = new Geocoder(this.context, Locale.getDefault());
                Address address     = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0);
                String addressLine  = address.getAddressLine(0);

                if (addressLine != null)
                {
                    this.textView.setText(addressLine);
                }
            }
            catch (IOException e)
            {
                Log.e("getFromLocation()", "IOException");
            }
        }
    }

    void getLocation() {
        if (ContextCompat.checkSelfPermission(this.activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this.activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 60119);
        }
        else
        {
            fusedLocationClient.getLastLocation().addOnSuccessListener(this.activity, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null)
                    {
                        setLocation(location);
                    }
                    else
                    {
                        startLocationUpdates();
                    }
                }
            });
        }
    }

    private void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this.activity, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
        {
            LocationRequest locationRequest = LocationRequest.create();
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!locationFound && requestCode == 60119 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            startLocationUpdates();
        }
    }
}
