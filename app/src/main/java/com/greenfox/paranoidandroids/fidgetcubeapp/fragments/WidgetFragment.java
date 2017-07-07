package com.greenfox.paranoidandroids.fidgetcubeapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.paranoidandroids.fidgetcubeapp.R;

public class WidgetFragment extends android.support.v4.app.Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_widget, container, false);
    return view;
  }
}
