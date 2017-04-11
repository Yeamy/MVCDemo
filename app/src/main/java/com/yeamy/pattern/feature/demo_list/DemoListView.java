package com.yeamy.pattern.feature.demo_list;

import android.support.annotation.NonNull;
import android.view.View;

import com.yeamy.pattern.BindClick;
import com.yeamy.pattern.BindLayout;
import com.yeamy.pattern.R;
import com.yeamy.pattern.data.DemoBean;

@BindLayout(R.layout.activity_demo)
public class DemoListView extends ListContentView<DemoBean> {
    @NonNull protected ActionImpl actionImpl;

    // view must belong to ActionImpl
    public DemoListView(@NonNull ActionImpl actionImpl) {
        super(actionImpl);
        this.actionImpl = actionImpl;
    }

    @NonNull
    @Override
    protected PagedListAdapter<DemoBean> onCreateAdapter() {
        return new DemoAdapter();
    }

    // ------------------------------------

    public void showBean(DemoBean bean) {
        //TODO
    }

    @BindClick(android.R.id.button1)// bind button Id
    public void sendForm(View v) {
        // do some ui changing
        v.setEnabled(false);
        // invoke p;
        actionImpl.sendForm();
    }

    @BindClick(R.id.action_back)
    public void finish(View v) {
        actionImpl.finish();
    }

    public interface ActionImpl extends ListContentView.ActionImpl<DemoBean> {
        void sendForm();
        void finish();
    }
}
