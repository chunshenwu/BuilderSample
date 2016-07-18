package com.example.jason_wu.buildersample.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jason_wu.buildersample.R;
import com.example.jason_wu.buildersample.builders.html.FormatTextBuilder;

/**
 * Created by jason_wu on 7/18/16.
 */
public class FormatTextFragment extends Fragment {
    @Nullable

    private View mRootView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.html_fragment, container, false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        initTextView();
    }

    private void initTextView() {
        ((TextView)mRootView.findViewById(R.id.normal_tv)).setText(new FormatTextBuilder(mRootView.getContext(), R.string.format_text).buildCharByCloudText("", 1));

        ((TextView)mRootView.findViewById(R.id.bold_tv)).setText(new FormatTextBuilder(mRootView.getContext(), R.string.format_text).makeBold().buildCharByCloudText("", 2));


        ((TextView)mRootView.findViewById(R.id.color_tv)).setText(new FormatTextBuilder(mRootView.getContext(), R.string.format_text).makeColor("#ED5565").buildCharByCloudText("", 3));


        ((TextView)mRootView.findViewById(R.id.color_bold_tv)).setText(new FormatTextBuilder(mRootView.getContext(), R.string.format_text).makeBold().makeColor("#ED5565").buildCharByCloudText("", 4));


    }
}
