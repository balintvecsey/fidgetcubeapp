package com.greenfox.paranoidandroids.fidgetcubeapp.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.greenfox.paranoidandroids.fidgetcubeapp.R;

public class DrawingFragment extends Fragment {

  DrawingView dv ;
  private Paint mPaint;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.drawing, container, false);
    dv = new DrawingView(getActivity());

    RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.drawing_container);
    relativeLayout.addView(dv);

    mPaint = new Paint();
    mPaint.setAntiAlias(true);
    mPaint.setDither(true);
    mPaint.setColor(Color.rgb(52,97,53));
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeJoin(Paint.Join.ROUND);
    mPaint.setStrokeCap(Paint.Cap.ROUND);
    mPaint.setStrokeWidth(12);
    return view;
  }

  public class DrawingView extends View {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mBitmapPaint;
    Context context;
    private Paint circlePaint;
    private Path circlePath;

    public DrawingView(Context c) {
      super(c);
      context = c;
      mPath = new Path();
      mBitmapPaint = new Paint(Paint.DITHER_FLAG);
      circlePaint = new Paint();
      circlePath = new Path();
      circlePaint.setAntiAlias(true);
      circlePaint.setColor(Color.BLUE);
      circlePaint.setStyle(Paint.Style.STROKE);
      circlePaint.setStrokeJoin(Paint.Join.MITER);
      circlePaint.setStrokeWidth(4f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
      super.onSizeChanged(w, h, oldw, oldh);

      mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
      mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);

      canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
      canvas.drawPath(mPath, mPaint);
      canvas.drawPath(circlePath, circlePaint);
    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private void touch_start(float x, float y) {
      mPath.reset();
      mPath.moveTo(x, y);
      mX = x;
      mY = y;
    }

    private void touch_move(float x, float y) {
      float dx = Math.abs(x - mX);
      float dy = Math.abs(y - mY);
      if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
        mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
        mX = x;
        mY = y;

        circlePath.reset();
        circlePath.addCircle(mX, mY, 30, Path.Direction.CW);
      }
    }

    private void touch_up() {
      mPath.lineTo(mX, mY);
      circlePath.reset();
      mCanvas.drawPath(mPath, mPaint);
      mPath.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
      float x = event.getX();
      float y = event.getY();

      switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
          touch_start(x, y);
          invalidate();
          break;
        case MotionEvent.ACTION_MOVE:
          touch_move(x, y);
          invalidate();
          break;
        case MotionEvent.ACTION_UP:
          touch_up();
          invalidate();
          break;
      }
      return true;
    }
  }
}
