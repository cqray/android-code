package cn.cqray.demo.android_utils;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import cn.cqray.android.util.ButterKnifeUtils;
import cn.cqray.android.util.ResUtils;
import cn.cqray.android.util.RoundDrawable;
import cn.cqray.android.util.SizeUtils;
import cn.cqray.android.util.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnifeUtils.bind(this);
        Utils.initialize(getApplication());


        RoundDrawable drawable = new RoundDrawable(drawableToBitmap(new ColorDrawable(Color.YELLOW)));

        findViewById(R.id.tv).setBackground(drawable);


        float size = SizeUtils.get(ResUtils.getDimenIdByName("content"));

        Log.e("数据", "大小：" + size);
        Log.e("数据", "大小：" + ResUtils.dp2px(20));
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        w = w <= 0 ? 300 : w;
        h = h <= 0 ? 300 : h;
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(bm);//将Bitmap对象放到画布里面
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);//将drawble画到画布上
        return bm;
    }
}