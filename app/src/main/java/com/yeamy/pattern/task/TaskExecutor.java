package com.yeamy.pattern.task;

import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;

import java.lang.ref.WeakReference;

public class TaskExecutor {

    public static<T extends TaskRequest> void execute(T request, TaskCallback<T> callback) {
        AsyncTaskCompat.executeParallel(new DemoTask<>(request, callback));
    }

    private static class DemoTask<T extends TaskRequest> extends AsyncTask<Object, Object, T> {
        private T request;
        private WeakReference<TaskCallback<T>> callback;

        private DemoTask(T request, TaskCallback<T> callback) {
            this.request = request;
            this.callback = new WeakReference<>(callback);
        }

        @Override
        protected T doInBackground(Object... params) {
            request.doInBackground();
            return request;
        }

        @Override
        protected void onPostExecute(T request) {
            TaskCallback<T> callback = this.callback.get();
            if (callback == null) return;
            if (request.isSuccess()) {
                callback.onSuccess(request);
            } else {
                callback.onError(request);
            }
        }
    }
}