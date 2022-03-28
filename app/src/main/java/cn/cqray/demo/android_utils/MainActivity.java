package cn.cqray.demo.android_utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import cn.cqray.android.code.util.ButterKnifeUtils;
import cn.cqray.android.code.util.ResUtils;
import cn.cqray.android.code.util.SizeUtils;
import cn.cqray.android.code.util.Utils;
import cn.cqray.android.code.delegate.ViewDelegate;

public class MainActivity extends AppCompatActivity {

     ViewDelegate delegate = new ViewDelegate(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnifeUtils.bind(this);
        Utils.initialize(getApplication());


        View view = findViewById(R.id.tv);
        delegate.setBackground(ContextCompat.getDrawable(this, R.color.colorPrimary));
        delegate.setRadius(10);
        delegate.setWidth(200);
        delegate.setHeight(40);
        delegate.setView(view);


        float size = SizeUtils.get(ResUtils.getDimenIdByName("content"));

        Log.e("数据", "大小：" + size);
        Log.e("数据", "大小：" + ResUtils.dp2px(150));
    }

}