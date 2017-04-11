package com.yeamy.pattern;

import android.support.annotation.LayoutRes;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * ContentView/BindAdapter绑定布局文件
 */
@Retention(RUNTIME)
@Target({TYPE})
public @interface BindLayout {
    @LayoutRes int value();
}