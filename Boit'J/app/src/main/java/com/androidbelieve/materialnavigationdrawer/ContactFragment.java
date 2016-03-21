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

    private GoogleMap map;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_layout, null);
        Button button = (Button) view.findViewById(R.id.buttonContact);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FormulaireActivity.class));
            }
        });
        if(MainActivity.mapFragment == null)
            MainActivity.mapFragment = SupportMapFragment.newInstance();
        MainActivity.mapFragment.getMapAsync(this);
        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng boitJ = new LatLng(43.537116,1.344992);
        map.addMarker(new MarkerOptions().position(boitJ).title("La Boit'J"));
        map.moveCamera(CameraUpdateFactory.newLatLng(boitJ));
    }
}
