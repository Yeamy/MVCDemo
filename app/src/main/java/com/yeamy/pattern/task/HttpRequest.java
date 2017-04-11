package com.yeamy.pattern.task;

import com.google.gson.annotations.Expose;

/**
 * Your App Framework Object
 */
public class HttpRequest implements TaskRequest {
    /**
     * http respond code;
     */
    @Expose
    public int code;

    @Expose
    public String raw;

    @Override
    public void doInBackground() {
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        code = 200;
        raw = "Hello World !";
    }

    @Override
    public boolean isSuccess() {
        return (code / 100) == 2;
    }
}
