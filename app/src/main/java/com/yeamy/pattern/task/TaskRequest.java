package com.yeamy.pattern.task;

import android.support.annotation.AnyThread;
import android.support.annotation.UiThread;

public interface TaskRequest {

    /**
     * 后台任务在这里处理
     */
    @AnyThread
    void doInBackground();

    /**
     * 结果走onSuccess还是onError的判断条件
     */
    @UiThread
    boolean isSuccess();
}