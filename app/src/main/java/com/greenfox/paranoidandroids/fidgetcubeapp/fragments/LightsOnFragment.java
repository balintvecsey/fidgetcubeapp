package com.greenfox.paranoidandroids.fidgetcubeapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import com.greenfox.paranoidandroids.fidgetcubeapp.MainActivity;
import com.greenfox.paranoidandroids.fidgetcubeapp.R;

/**
 * Created by Bálint on 2017. 07. 07..
 */

public class LightsOnFragment extends Fragment{

  private static final String TAG = "LightsOnFragment";

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_lightson, container, false);
    Log.d(TAG, "canWrite: " + Settings.System.canWrite(getContext()));
    Switch switchLightsOn = (Switch) view.findViewById(R.id.switch_lights);
    try {
      int currentLights = System.getInt(getActivity().getContentResolver(), System.SCREEN_BRIGHTNESS);
      if(currentLights == 255) {
        switchLightsOn.setChecked(true);
      }
    } catch (SettingNotFoundException e) {
      e.printStackTrace();
    }

    switchLightsOn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
          Settings.System.putInt(getActivity().getContentResolver(), System.SCREEN_BRIGHTNESS, 255);
        } else  {
          Settings.System.putInt(getActivity().getContentResolver(), System.SCREEN_BRIGHTNESS, 0);
        }
      }
    });
    return view;
  }
}
