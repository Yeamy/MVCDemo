package com.yeamy.pattern.feature.demo;

import com.yeamy.pattern.data.DemoBean;
import com.yeamy.pattern.task.HttpRequest;

import java.util.Random;

public class DemoBeanRequest extends HttpRequest {
    public DemoBean bean;

    @Override
    public void doInBackground() {
        super.doInBackground();
        // 在异步线程解析数据
        bean = new DemoBean(raw, new Random().nextInt(1));
    }
}