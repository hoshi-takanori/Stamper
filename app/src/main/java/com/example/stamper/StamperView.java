package com.example.stamper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

public class StamperView extends View {

    private Bitmap bitmap;
    private Canvas bitmapCanvas;

    private String mode;
    private int color;

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

    public void clear() {
        Paint paint = new Paint();
        bitmapCanvas.drawColor(Color.WHITE);
        invalidate();
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Paint.Cap.ROUND);

        switch (mode) {
            case "点を描く":
                if (action == ACTION_DOWN) {
                    bitmapCanvas.drawPoint(x, y, paint);
                    invalidate();
                }
                break;

            case "点を連続":
                if (action == ACTION_DOWN || action == ACTION_MOVE) {
                    bitmapCanvas.drawPoint(x, y, paint);
                    invalidate();
                }
                break;
        }

        return true;
    }
}
