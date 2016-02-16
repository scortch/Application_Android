package com.androidbelieve.materialnavigationdrawer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link ContactFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link ContactFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ContactFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_layout, null);
        Button button = (Button) view.findViewById(R.id.buttonContact);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                Fragment formulaireFragment = new FormulaireFragment();
//                fragmentTransaction.replace(R.id.containerView,formulaireFragment);
//                fragmentTransaction.commit();
                  startActivity(new Intent(getActivity(),FormulaireActivity.class));
            }
        });
        return view;
    }


}
