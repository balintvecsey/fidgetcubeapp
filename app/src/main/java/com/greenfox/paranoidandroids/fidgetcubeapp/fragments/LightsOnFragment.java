package com.greenfox.paranoidandroids.fidgetcubeapp.fragments;

import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import com.greenfox.paranoidandroids.fidgetcubeapp.R;
import com.greenfox.paranoidandroids.fidgetcubeapp.service.LogicService;

public class LightsOnFragment extends Fragment{

  private static final String TAG = "LightsOnFragment";
  int currentBrightness;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_lightson, container, false);
    final SeekBar seekbarLightsOn = (SeekBar) view.findViewById(R.id.seekBar_lights);
    Log.d(TAG, "canWrite: " + Settings.System.canWrite(getContext()));

    final Switch switchLightsOn = (Switch) view.findViewById(R.id.switch_lights);
    try {
      currentBrightness = System.getInt(getActivity().getContentResolver(), System.SCREEN_BRIGHTNESS);
      Log.d(TAG, "onCreateView: " + currentBrightness);
      if(currentBrightness >= 250) {
        switchLightsOn.setChecked(true);
      }
      seekbarLightsOn.setProgress(currentBrightness);
    } catch (SettingNotFoundException e) {
      Log.e("Error", "Cannot access system brightness");
      e.printStackTrace();
    }

    switchLightsOn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
          currentBrightness = 255;
          Settings.System.putInt(getActivity().getContentResolver(), System.SCREEN_BRIGHTNESS, currentBrightness);
        } else  {
          currentBrightness = 0;
          Settings.System.putInt(getActivity().getContentResolver(), System.SCREEN_BRIGHTNESS, currentBrightness);
        }
        seekbarLightsOn.setProgress(currentBrightness);
      }
    });

    Button buttonLightsOn = (Button) view.findViewById(R.id.button_lights);
    buttonLightsOn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        LogicService logicService = new LogicService();
        currentBrightness = logicService.randomNumber(0, 256);
        Log.d(TAG, "Random: " + currentBrightness);
        Settings.System.putInt(getActivity().getContentResolver(), System.SCREEN_BRIGHTNESS, currentBrightness);
        seekbarLightsOn.setProgress(currentBrightness);

        if(currentBrightness >= 250) {
          switchLightsOn.setChecked(true);
        } else {
          switchLightsOn.setChecked(false);
        }
      }
    });

    seekbarLightsOn.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        currentBrightness = progress;
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
        Settings.System.putInt(getActivity().getContentResolver(), System.SCREEN_BRIGHTNESS, currentBrightness);

        if(currentBrightness >= 250) {
          switchLightsOn.setChecked(true);
        } else {
          switchLightsOn.setChecked(false);
        }
      }
    });

    return view;
  }
}
