package com.example.stamper;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class StamperView extends View {

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
    protected void onDraw(Canvas canvas) {
    }
}
