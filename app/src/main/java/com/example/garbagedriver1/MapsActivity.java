package com.example.garbagedriver1;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.room.Database;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationClickListener, GoogleMap.OnMyLocationButtonClickListener {

    private GoogleMap mMap;
    String latitude;
    String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Location");
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mMap.clear();
                latitude = snapshot.child("latitude").getValue().toString();
                longitude = snapshot.child("longitude").getValue().toString();
                Toast.makeText(MapsActivity.this, latitude + "   " + longitude, Toast.LENGTH_SHORT).show();
                LatLng latLng = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
//                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title(latitude + " , " + longitude +
                        "     " + "Marker in my home").icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_baseline_directions_bus_24)));
//                mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in my home"));
//                marker.setPosition(latLng);
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                                latLng, 16f
                        )
                );
                mMap.setTrafficEnabled(true);
                mMap.getUiSettings().setCompassEnabled(true);
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.getUiSettings().setAllGesturesEnabled(true);
                mMap.getUiSettings().setRotateGesturesEnabled(true);
                mMap.setBuildingsEnabled(true);

                if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mMap.setMyLocationEnabled(true);
                mMap.setOnMyLocationButtonClickListener(MapsActivity.this);
                mMap.setOnMyLocationClickListener(MapsActivity.this);




//                12.9032724,77.5095461

                Circle circle = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9221379,77.5750938))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));

                Circle circle1 = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9202366,77.5808573))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));


                Circle circle2 = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9165954,77.5971079))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));


                Circle jayaNagar4thblock = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9270341,77.5788817))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));

//                https://www.google.com/maps/place/4th+Block,+Jayanagar,+Bengaluru,+Karnataka+560041/@12.9270341,77.5788817,16z/data=!3m1!4b1!4m5!3m4!1s0x3bae159eeb3a5753:0xbe47e1b47ffa7c46!8m2!3d12.9259401!4d77.5830419
//                https://www.google.com/maps/place/Jayanagar+East,+Jaya+Nagar+1st+Block,+Jaya+Nagar+East,+Jayanagar,+Bengaluru,+Karnataka/@12.9337018,77.5749752,15z/data=!3m1!4b1!4m5!3m4!1s0x3bae159827565b39:0xb65b5c0ee05b34b2!8m2!3d12.9329463!4d77.5838692



                Circle jayaNagar3rdBlock = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9337018,77.5749752))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));

//                https://www.google.com/maps/place/South+End+Circle,+2nd+Block,+Jayanagar,+Bengaluru,+Karnataka+560041/@12.9367159,77.5713388,15z/data=!3m1!4b1!4m5!3m4!1s0x3bae1596c702399b:0x2f4a2b96fdf65adc!8m2!3d12.936716!4d77.5800936


                Circle southEndCircle = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9367159,77.5713388))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));

                Circle KrishnaRaoPark = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9424869,77.5671165))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));

                Circle GandhiBazar = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9459723,77.5619667))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));


                Circle RamakrishnaAshrama = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9477145,77.5588747))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));

                Circle GaneshaCircle = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9435504,77.5556391))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));

                Circle HanumanthNagar = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9332382,77.5343415))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));


                Circle PesPoly= mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9413082,77.5460156))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));

                Circle SeethaCircle = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9370779,77.5400322))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));

                Circle SHosakerehallliCross = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9332382,77.5343415))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));



                Circle BigBazar = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9325664,77.5044718))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));



                Circle DGPB = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9222029,77.5599679))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));

                Circle PadmaNagar = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9166084,77.5385004))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));


                Circle  Brigade= mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9108784,77.5552367))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));

                Circle  Rajatadri= mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9072868,77.5408068))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));

                Circle  RNS= mMap.addCircle(new CircleOptions()
                        .center(new LatLng(12.9021902,77.5163933))
                        .radius(30)
                        .strokeColor(Color.BLACK)
                        .fillColor(Color.WHITE));



                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9035704,77.5061209)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));





                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9221379,77.5750938)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9202366,77.5808573)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));

                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9165954,77.5971079)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9270341,77.5788817)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9337018,77.5749752)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9367159,77.5713388)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9424869,77.5671165)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));

                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9459723,77.5619667)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9477145,77.5588747)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9435504,77.5556391)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9332382,77.5343415)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9413082,77.5460156)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9370779,77.5400322)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));

                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9332382,77.5343415)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9325664,77.5044718)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9222029,77.5599679)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9166084,77.5385004)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9108784,77.5552367)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9072868,77.5408068)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(12.9021902,77.5163933)).title("Marker in my home").icon(BitmapFromVector(getApplicationContext(),R.drawable.ic_baseline_flag_24)));

                Polyline polyline1 = mMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(
                                new LatLng(12.9221379,77.5750938),
                                new LatLng(12.9202366,77.5808573),
                                new LatLng(12.9165954,77.5971079),
                                new LatLng(12.9270341,77.5788817),
                                new LatLng(12.9337018,77.5749752),
                                new LatLng(12.9367159,77.5713388),
                                new LatLng(12.9424869,77.5671165),
                                new LatLng(12.9459723,77.5619667),
                                new LatLng(12.9477145,77.5588747),
                                new LatLng(12.9435504,77.5556391),
                                new LatLng(12.9332382,77.5343415),
                                new LatLng(12.9413082,77.5460156),
                                new LatLng(12.9370779,77.5400322),
                                new LatLng(12.9332382,77.5343415),
                                new LatLng(12.9325664,77.5044718),
                                new LatLng(12.9222029,77.5599679),
                                new LatLng(12.9166084,77.5385004),
                                new LatLng(12.9108784,77.5552367),
                                new LatLng(12.9072868,77.5408068),
                                new LatLng(12.9021902,77.5163933),
                                new LatLng(12.9035704,77.5061209)
                                )
              );







            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        int lat1=Integer.parseInt(latitude);
//        int lon1=Integer.parseInt(longitude);
//        LatLng sydney = new LatLng(0,0);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


    }

    private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }
}