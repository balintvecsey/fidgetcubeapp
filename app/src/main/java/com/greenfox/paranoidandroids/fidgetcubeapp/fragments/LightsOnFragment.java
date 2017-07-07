package com.greenfox.paranoidandroids.fidgetcubeapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.greenfox.paranoidandroids.fidgetcubeapp.R;

/**
 * Created by Bálint on 2017. 07. 07..
 */

public class LightsOnFragment extends Fragment{

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_lightson, container, false);
  }
}
