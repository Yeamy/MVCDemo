package com.yeamy.pattern.task;

import android.support.annotation.UiThread;

public interface TaskCallback<T extends TaskRequest> {

    @UiThread
    void onSuccess(T request);

    @UiThread
    void onError(T request);

}