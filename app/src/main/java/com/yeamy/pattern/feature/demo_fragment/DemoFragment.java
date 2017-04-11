package com.yeamy.pattern.feature.demo_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DemoFragment extends Fragment implements DemoFragmentView.ActionImpl {
    private DemoFragmentView contentView = new DemoFragmentView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = contentView.createView(inflater, savedInstanceState);
        contentView.setActionImpl(this);
        return view;
    }

    @Override
    public void doAction() {

    }
}
