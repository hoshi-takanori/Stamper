package com.example.stamper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class StamperView extends View {

    private Bitmap bitmap;
    private Canvas bitmapCanvas;

    public StamperView(Context context) {
        super(context);
    }

    public StamperView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StamperView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStrokeWidth(20);
            paint.setStrokeCap(Paint.Cap.ROUND);
            bitmapCanvas.drawPoint(x, y, paint);
            invalidate();
        }

        return true;
    }
}
