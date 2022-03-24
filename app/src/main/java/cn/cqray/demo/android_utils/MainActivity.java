package cn.cqray.demo.android_utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cn.cqray.android.util.ButterKnifeUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnifeUtils.bind(this);
    }
}