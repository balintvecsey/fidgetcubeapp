package com.greenfox.paranoidandroids.fidgetcubeapp.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.greenfox.paranoidandroids.fidgetcubeapp.R;

public class CatFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_cat, container, false);
    ImageView cat = (ImageView) view.findViewById(R.id.cat);
    cat.setDrawingCacheEnabled(true);

    cat.setOnTouchListener(new OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
        int color = bmp.getPixel((int) event.getX(), (int) event.getY());
        Vibrator vib = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
        if (color == Color.TRANSPARENT) {
          return false;
        } else {
          if (event.getAction() == MotionEvent.ACTION_MOVE) {
            vib.vibrate(60000);
          } else {
            vib.cancel();
          }
          return true;
        }
      }
    });

    return view;
  }
}
