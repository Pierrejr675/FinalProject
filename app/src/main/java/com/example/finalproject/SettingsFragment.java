package com.example.finalproject;

import android.graphics.Color;
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
        Button bottomMenuIconsBtn = view.findViewById(R.id.settings_bottomMenuIcons_Button);

        topMenuBtn.setOnClickListener(v -> {
            selectedElement = 1;
            updateSliders(mainActivity.getTopToolbarColor());
        });
        bottomMenuBtn.setOnClickListener(v -> {
            selectedElement = 2;
            updateSliders(mainActivity.getBottomToolbarColor());
        });
        bottomMenuIconsBtn.setOnClickListener(v -> {
            selectedElement = 4;
            updateSliders(mainActivity.getButtonColor());
        });

        SeekBar.OnSeekBarChangeListener colorChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        };

        seekBarRed.setOnSeekBarChangeListener(colorChangeListener);
        seekBarGreen.setOnSeekBarChangeListener(colorChangeListener);
        seekBarBlue.setOnSeekBarChangeListener(colorChangeListener);

        return view;
    }

    private void updateSliders(int color) {
        seekBarRed.setProgress(Color.red(color));
        seekBarGreen.setProgress(Color.green(color));
        seekBarBlue.setProgress(Color.blue(color));
    }

    private void updateColor() {
        int red = seekBarRed.getProgress();
        int green = seekBarGreen.getProgress();
        int blue = seekBarBlue.getProgress();
        int color = Color.rgb(red, green, blue);

        switch (selectedElement) {
            case 1:
                mainActivity.setTopToolbarColor(color);
                break;
            case 2:
                mainActivity.setBottomToolbarColor(color);
                break;
            case 3:
                mainActivity.setBottombarButtonColor(color);
                break;
        }
    }
}