package com.yeamy.pattern.feature.demo_fragment;

import android.view.View;

import com.yeamy.pattern.BindClick;
import com.yeamy.pattern.BindLayout;
import com.yeamy.pattern.ContentView;
import com.yeamy.pattern.R;
import com.yeamy.pattern.data.DemoBean;

@BindLayout(R.layout.activity_demo)
public class DemoFragmentView extends ContentView {
    private ActionImpl actionImpl;

    public void setActionImpl(ActionImpl actionImpl) {
        this.actionImpl = actionImpl;
    }

    // ------------------------------------

    public void showBean(DemoBean bean) {
    }

    @BindClick(android.R.id.button1)// bind button Id
    public void sendForm(View v) {
        // do some ui changing
        v.setEnabled(false);
        // invoke p;
        if (actionImpl != null) actionImpl.doAction();
    }

    public interface ActionImpl {
        void doAction();
    }
}
