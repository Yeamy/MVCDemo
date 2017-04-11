package com.yeamy.pattern.feature.demo;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.yeamy.pattern.BindClick;
import com.yeamy.pattern.BindId;
import com.yeamy.pattern.BindLayout;
import com.yeamy.pattern.ContentView;
import com.yeamy.pattern.R;
import com.yeamy.pattern.data.DemoBean;

import java.util.Random;

/**
 * 处理所有有关View的操作
 */
@BindLayout(R.layout.activity_demo)
public class DemoView extends ContentView {
    @BindId(R.id.tv_name)
    private TextView tv_name;
    @BindId(R.id.btn_showName)
    private TextView btn_showName;

    @NonNull
    private ActionImpl actionImpl;//View归属于其"业务逻辑实现"，所以不为空；相反，impl的View可能为空

    public DemoView(@NonNull ActionImpl actionImpl) {
        this.actionImpl = actionImpl;
    }

    // ------------------------------------

    /**
     * 为外部提供操控View的方法
     *
     * @param bean 原则上只允许读取不允许修改数据结构的内容
     */
    public void showBean(DemoBean bean) {
        tv_name.setText(bean.name + new Random().nextInt());
        btn_showName.setEnabled(true);
    }

    /**
     * 点击的回调方法
     *
     * @param v 被点击的空间
     */
    @BindClick(R.id.action_back)
    public void actionBack(View v) {
        // 在此处修改View的状态
        v.setEnabled(false);
        // 最后回调给"业务逻辑实现"
        actionImpl.actionBack();
    }

    @BindClick(R.id.btn_showName)
    public void showName(View v) {
        v.setEnabled(false);
        actionImpl.showName();
    }

    public interface ActionImpl {
        void actionBack();
        void showName();
    }
}
