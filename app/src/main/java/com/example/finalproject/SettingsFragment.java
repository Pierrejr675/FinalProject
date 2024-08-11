package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;


public class SettingsFragment extends Fragment {

    private MainActivity mainActivity;
    private SeekBar seekBarRed, seekBarGreen, seekBarBlue;
    private int selectedElement = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        mainActivity = (MainActivity) getActivity();

        seekBarRed = view.findViewById(R.id.settings_red_SeekBar);
        seekBarGreen = view.findViewById(R.id.settings_green_SeekBar);
        seekBarBlue = view.findViewById(R.id.settings_blue_SeekBar);

        Button topMenuBtn = view.findViewById(R.id.settings_topMenu_Button);
        Button bottomMenuBtn = view.findViewById(R.id.settings_bottomMenu_Button);
        Button topMenuIconsBtn = view.findViewById(R.id.settings_topMenuIcons_button);
        Button bottomMenuIconsBtn = view.findViewById(R.id.settings_bottomMenuIcons_Button);

//        topMenuBtn.setOnClickListener(v -> {
//            selectedElement = 1;
//            updateSliders(mainActivity.getTopToolbarColor());
//        });
//        bottomMenuBtn.setOnClickListener(v -> {
//            selectedElement = 2;
//            updateSliders(mainActivity.getBottomToolbarColor());
//        });
//        topMenuIconsBtn.setOnClickListener(v -> {
//            selectedElement = 3;
//            updateSliders(mainActivity.getButtonColor());
//        });
//        bottomMenuIconsBtn.setOnClickListener(v -> {
//            selectedElement = 4;
//            updateSliders(mainActivity.getButtonColor());
//        });


        return view;
    }
}