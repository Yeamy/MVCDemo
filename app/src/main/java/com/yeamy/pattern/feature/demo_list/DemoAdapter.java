package com.yeamy.pattern.feature.demo_list;

import android.view.View;

import com.yeamy.pattern.BindLayout;
import com.yeamy.pattern.R;
import com.yeamy.pattern.data.DemoBean;

@BindLayout(R.layout.li_demo)
public class DemoAdapter extends BindAdapter<DemoBean> {

    @Override
    protected void onBindView(View convertView, DemoBean demoBean, int position) {

    }
}
