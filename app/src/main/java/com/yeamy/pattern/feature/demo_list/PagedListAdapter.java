package com.yeamy.pattern.feature.demo_list;

import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Your App Framework <br>
 *
 * @param <T> data in Adapter
 */
public abstract class PagedListAdapter<T> extends BaseAdapter {
    private ArrayList<T> data;
    private int totalPage = 0;
    private int curPage = 0;//first page is 1

    public void putAll(ArrayList<T> list) {
        if (data == null) {
            data = list;
        } else {
            data.addAll(list);
        }
    }

    public void addAll(ArrayList<T> list) {
        if (data == null) data = new ArrayList<>();
        data.addAll(list);
    }

    public void add(T... t) {
        if (data == null) data = new ArrayList<>();
        Collections.addAll(data, t);
    }

    public void remove(int position) {
        data.remove(position);
    }

    public void remove(T t) {
        data.remove(t);
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isLastPage() {
        return totalPage != 0 && totalPage == curPage;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
