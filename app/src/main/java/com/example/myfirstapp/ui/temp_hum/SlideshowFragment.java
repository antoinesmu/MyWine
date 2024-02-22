package com.example.myfirstapp.ui.temp_hum;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;

import com.example.myfirstapp.R;
import com.example.myfirstapp.Singleton;


public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        slideshowViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_temp_hum, container, false);

        Singleton.requestGETTempHum();

        ((TextView) (root.findViewById(R.id.textViewTemp))).setText("la température est de "+Singleton.getInstance().temp+" °C");
        ((TextView) (root.findViewById(R.id.textViewHum))).setText("l'humidité est de "+Singleton.getInstance().hum+" %");

        return root;
    }


}