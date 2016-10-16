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

/**
 * お絵描きビュー
 */
public class StamperView extends View {

    /**
     * フィールド（メソッドが終わった後も値が残る）
     */
    private Bitmap bitmap;
    private Canvas bitmapCanvas;

    private String mode;    // お絵描きする方法
    private int color;      // お絵描きする色

    /**
     * コンストラクタ
     */
    public StamperView(Context context) {
        super(context);
    }

    public StamperView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StamperView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * ビューのサイズが設定されたときに呼ばれるメソッド
     * お絵描きするビットマップとキャンバスを用意する
     */
    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
    }

    /**
     * ビューを描画するメソッド
     * お絵描きをした内容を画面に表示する
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    /**
     * お絵描きをした内容を全部消すメソッド
     */
    public void clear() {
        Paint paint = new Paint();
        bitmapCanvas.drawColor(Color.WHITE);
        invalidate();
    }

    /**
     * お絵描きする方法を設定するメソッド
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * お絵描きする色を設定するメソッド
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * 画面にタッチしたときに呼ばれるメソッド
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // タッチの詳細を取得する
        // ACTION_DOWN ... 画面に触れた時
        // ACTION_MOVE ... 画面に触れたまま指を動かした時
        // ACTION_UP   ... 画面から指を離した時
        int action = event.getAction();

        // タッチされた場所を取得する
        int x = (int) event.getX();
        int y = (int) event.getY();

        // 塗料を用意する
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Paint.Cap.ROUND);

        // お絵描きする方法に応じて分岐する
        switch (mode) {
            case "点を描く":
                // 画面に触れた時だけ、点を描く
                if (action == ACTION_DOWN) {
                    bitmapCanvas.drawPoint(x, y, paint);
                    invalidate();
                }
                break;

            case "点を連続":
                // 画面に触れた時と、そのまま指を動かした時に、点を描く
                if (action == ACTION_DOWN || action == ACTION_MOVE) {
                    bitmapCanvas.drawPoint(x, y, paint);
                    invalidate();
                }
                break;
        }

        return true;
    }
}
