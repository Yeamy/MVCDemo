package com.yeamy.pattern.feature.demo_list;

import android.view.View;
import android.view.ViewGroup;

import com.google.gson.annotations.Expose;
import com.yeamy.pattern.BindLayout;
import com.yeamy.pattern.data.DemoBean;
import com.yeamy.pattern.task.HttpRequest;

import java.util.ArrayList;

/**
 * Your App Framework <br>
 *
 * @param <T> data in Adapter
 */
public abstract class BindAdapter<T> extends PagedListAdapter<T> {
    private int layout;

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        if (layout == 0) {
            layout = getClass().getAnnotation(BindLayout.class).value();
        }
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), layout, null);
        }
        onBindView(convertView, getItem(position), position);
        return convertView;
    }

    protected abstract void onBindView(View convertView, T t, int position);

    public static class DemoListRequest extends HttpRequest {
        public ArrayList<DemoBean> bean;
        public int totalPage;
        @Expose
        public int curPage;
    }
}
