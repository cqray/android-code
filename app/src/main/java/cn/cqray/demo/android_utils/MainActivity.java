package cn.cqray.demo.android_utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import cn.cqray.android.util.ButterKnifeUtils;
import cn.cqray.android.util.ResUtils;
import cn.cqray.android.util.SizeUtils;
import cn.cqray.android.util.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnifeUtils.bind(this);
        Utils.initialize(getApplication());


        float size = SizeUtils.get(ResUtils.getDimenIdByName("content"));

        Log.e("数据", "大小：" + size);
        Log.e("数据", "大小：" + ResUtils.dp2px(20));
    }
}