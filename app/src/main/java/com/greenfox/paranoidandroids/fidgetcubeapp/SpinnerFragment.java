package com.greenfox.paranoidandroids.fidgetcubeapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class SpinnerFragment extends Fragment {

  private final float ROTATE_FROM = 0.0f;
  private final float ROTATE_TO = 10.0f - 360.0f;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.spinner_fragment, container, false);

    final ImageView spinner = (ImageView) view.findViewById(R.id.spinner);

    spinner.setOnTouchListener(new View.OnTouchListener() {

      @Override
      public boolean onTouch(View view, MotionEvent motionEvent) {
        RotateAnimation r = new RotateAnimation(ROTATE_FROM, ROTATE_TO,
          Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        r.setDuration((6000));
        r.setRepeatCount(0);
        spinner.startAnimation(r);
        return true;
      }
    });
    return view;
  }
}
