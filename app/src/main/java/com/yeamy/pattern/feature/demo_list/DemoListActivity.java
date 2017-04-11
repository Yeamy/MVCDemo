package com.yeamy.pattern.feature.demo_list;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yeamy.pattern.data.DemoBean;

public class DemoListActivity extends Activity implements DemoListView.ActionImpl {
    private DemoListView contentView = new DemoListView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentView.createView(this, savedInstanceState));
//        contentView.
//        contentView.updateListData();
    }

    @Override
    public void getNextPage() {
    }

    @Override
    public void onItemClick(DemoBean bean) {
    }

    @Override
    public void onRefresh() {
        contentView.setRefreshing(true);
    }

    @Override
    public void sendForm() {

    }
}
