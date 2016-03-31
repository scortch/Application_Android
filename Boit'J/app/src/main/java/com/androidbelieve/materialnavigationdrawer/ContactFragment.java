package com.androidbelieve.materialnavigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link ContactFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link ContactFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ContactFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_layout, null);
        Button buttonContact = (Button) view.findViewById(R.id.buttonContact);
        buttonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FormulaireActivity.class));
            }
        });

        Button buttonMaps = (Button) view.findViewById(R.id.buttonMaps);
        buttonMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MapsActivity.class));
            }
        });
//        MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map);
//        if(mapFragment == null)
//        {
//            mapFragment = MapFragment.newInstance();
//        }
//        mapFragment.getMapAsync(this);
        return view;
    }


    @Override
    public void onMapReady(GoogleMap map) {
        LatLng boitJ = new LatLng(43.537116,1.344992);
        map.addMarker(new MarkerOptions().title("La Boit'J").position(boitJ));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(boitJ,10));
    }
}
