package com.example.justmotor.ui.Comparador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.justmotor.R;

public class ComparadorFragment extends Fragment {

    Button btnMoto1;
    Button btnMoto2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_comparador, container, false);

        btnMoto1 = v.findViewById(R.id.btnMoto1);
        btnMoto2 = v.findViewById(R.id.btnMoto2);

        btnMoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.filterMotoFragment);
            }
        });

        btnMoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.filterMotoFragment);
            }
        });

        return v;
    }
}
