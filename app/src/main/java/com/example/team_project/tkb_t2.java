package com.example.team_project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class tkb_t2 extends Fragment {

    View view;
    TextView txttietBD ,txttietKT ,txtidPhong ,txtMonHoc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_tkb_t2, container, false);
        View view =inflater.inflate(R.layout.fragment_tkb_t2,container,false);
        txttietBD =(TextView) view.findViewById(R.id.tietBD);
        txttietKT=(TextView) view.findViewById(R.id.tietKT);
        txtidPhong=(TextView) view.findViewById(R.id.idPhong);
        txtMonHoc =(TextView) view.findViewById(R.id.Monhoc);

        return  view;
    }

}