package com.yeamy.pattern.feature.demo_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yeamy.pattern.ContentView;
import com.yeamy.pattern.R;

/**
 * Your App Framework <br>
 * contain views: <br>
 * (must) ListView id = android.R.id.list<br>
 * (optional) SwipeRefreshLayout id = R.id.swipe<br>
 *
 * @param <T> data in Adapter
 */
public abstract class ListContentView<T> extends ContentView implements AdapterView.OnItemClickListener,
        AbsListView.OnScrollListener {
    @Nullable
    protected SwipeRefreshLayout swipe;
    protected PagedListAdapter<T> adapter;
    @NonNull
    protected ActionImpl<T> actionImpl;

    public ListContentView(@NonNull ActionImpl<T> actionImpl) {
        this.actionImpl = actionImpl;
    }

    @Override
    public View createView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        View view = super.createView(inflater, savedInstanceState);
        // swipe
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        if (swipe != null) swipe.setOnRefreshListener(actionImpl);
        // lv
        ListView lv = (ListView) view.findViewById(android.R.id.list);
        View[] hvs = onCreateHeaderViews();
        for (View v : hvs) {
            lv.addHeaderView(v);
        }
        View[] fvs = onCreateFooterViews();
        for (View v : fvs) {
            lv.addHeaderView(v);
        }
        lv.setAdapter(adapter = onCreateAdapter());
        lv.setOnItemClickListener(this);
        lv.setOnScrollListener(this);
        adapter.notifyDataSetChanged();
        return view;
    }

    protected View[] onCreateHeaderViews() {
        return null;
    }

    protected View[] onCreateFooterViews() {
        return null;
    }

    @NonNull
    protected abstract PagedListAdapter<T> onCreateAdapter();

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            actionImpl.getNextPage();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView lv = (ListView) parent;
        position -= lv.getHeaderViewsCount();
        actionImpl.onItemClick(adapter.getItem(position));
    }

    public void setRefreshing(boolean refreshing) {
        if (swipe != null) swipe.setRefreshing(refreshing);
    }

    /**
     * update adapter data
     *
     * @param ts        the list content data
     * @param curPage   current page index
     * @param totalPage total page count
     */
    public void updateListData(T[] ts, int curPage, int totalPage) {
        adapter.add(ts);
        adapter.setCurPage(curPage);
        adapter.setTotalPage(totalPage);
        adapter.notifyDataSetChanged();
    }

    public interface ActionImpl<T> extends SwipeRefreshLayout.OnRefreshListener {

        void getNextPage();

        void onItemClick(T t);
    }
}
