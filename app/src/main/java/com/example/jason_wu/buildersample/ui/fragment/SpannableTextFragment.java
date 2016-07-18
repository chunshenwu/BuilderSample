package com.example.jason_wu.buildersample.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jason_wu.buildersample.R;
import com.example.jason_wu.buildersample.builders.Spannable.TimeSpannableBuilder;
import com.example.jason_wu.buildersample.builders.html.FormatTextBuilder;

/**
 * Created by jason_wu on 7/18/16.
 */
public class SpannableTextFragment extends Fragment {
    @Nullable

    private View mRootView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.spannable_fragment, container, false);
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
        float time = 125;

        ((TextView)mRootView.findViewById(R.id.appendprefix_tv)).setText(new TimeSpannableBuilder(time, 1.0f).appendPreFix("Prefix_").appendPlus().getSpannable());


        ((TextView)mRootView.findViewById(R.id.appendplus_tv)).setText(new TimeSpannableBuilder(time, 0.75f).appendPlus().getSpannable());

        ((TextView)mRootView.findViewById(R.id.appendplus_and_appendsuffix_tv)).setText(new TimeSpannableBuilder(time, 0.5f).appendPlus().appendSuffix("Suffix_").getSpannable());


        ((TextView)mRootView.findViewById(R.id.keep_mins_tv)).setText(new TimeSpannableBuilder(time, 0.25f).appendPlus().keepMins().getSpannable());


    }
}
