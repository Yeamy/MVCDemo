package com.yeamy.pattern.feature.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.yeamy.pattern.data.DemoBean;
import com.yeamy.pattern.task.HttpRequest;
import com.yeamy.pattern.task.TaskCallback;
import com.yeamy.pattern.task.TaskExecutor;

/**
 * 处理APP的业务逻辑，实现View的"业务逻辑实现"接口和任务的"结果回调"接口
 */
public class DemoActivity extends Activity implements DemoView.ActionImpl, TaskCallback<DemoBeanRequest> {
    private DemoView contentView = new DemoView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentView.createView(this, savedInstanceState));
        contentView.showBean(new DemoBean("NULL", 1));
    }

    @Override
    public void actionBack() {
        TaskExecutor.execute(new HttpRequest(), new TaskCallback<HttpRequest>() {
            @Override
            public void onSuccess(HttpRequest request) {
                finish();
            }

            @Override
            public void onError(HttpRequest request) {
                finish();
            }
        });
    }

    @Override
    public void showName() {
        TaskExecutor.execute(new DemoBeanRequest(), this);
    }

    @Override
    public void onSuccess(DemoBeanRequest request) {
        if (request.isSuccess()) {
            contentView.showBean(request.bean);
        } else {
            showToast(request.bean.msg);
        }
    }

    @Override
    public void onError(DemoBeanRequest request) {
        showToast("失败");
    }

    private void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
