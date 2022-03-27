package cn.cqray.android.util;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RoundDrawable extends Drawable {
    private Paint mPaint;   //图片画笔
    private RectF mRectF;   //创建一个矩形，将图片绘制到该矩形上。
    private Bitmap mBitmap; //需要绘制的图片

    //region 绘制图片方法
    public RoundDrawable(Bitmap bitmap) {

        mBitmap = bitmap;
        //获取图片的着色器
        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //创建画笔
        mPaint = new Paint();
        //画笔防锯齿
        mPaint.setAntiAlias(true);
        //将图片设置到画笔上
        mPaint.setShader(bitmapShader);
    }
    //endregion

    //设置矩形大小和位置
    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        mRectF = new RectF(left, top, right, bottom);
    }

    //绘制图片
    @Override
    public void draw(@NonNull Canvas canvas) {
        float radius = 60;
        float[] radii = new float[8];
        for (int i = 0; i < 8; i++) {
            radii[i] = 60;
        }

        radii[2] = 30;
        radii[3] = 60;

        Path path = new Path();
        RectF rect = new RectF(0, 0, 300, 300 );
        path.addRoundRect(mRectF,radii, Path.Direction.CW);
        canvas.drawPath(path, mPaint);

        //super.draw(canvas);

        //canvas.drawRoundRect(mRectF,  /*圆角x轴位置*/30,/*圆角y轴位置*/30, mPaint);
        //canvas.drawDoubleRoundRect(mRectF, new float[] {10, 10,10,10,10,10,10,10}, new RectF(), new float[]{0,0,0,0,0,0,0,0}, mPaint);
    }

    //设置图片宽度
    @Override
    public int getIntrinsicWidth() {
        return mBitmap.getWidth();
    }

    //设置图片高度
    @Override
    public int getIntrinsicHeight() {
        return mBitmap.getHeight();
    }

    //设置透明度
    @Override
    public void setAlpha(int i) {
        mPaint.setAlpha(i);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
