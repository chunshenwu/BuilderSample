package com.example.jason_wu.buildersample.builders.Spannable;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.TypefaceSpan;


/**
 * Created by jason_wu on 5/19/16.
 */
public class TimeSpannableBuilder {

    private static final boolean DEG = true;
    private static final String TAG = "TimeSpannableBuilder";

    private final float mProportion;
    private final float mTime;


    private String mPreFix = "";
    private String mPlus = "";
    private String mSuffix = "";

    private boolean isSpacePrefix = false;
    private boolean isSpaceSuffix = false;
    private boolean mKeepMins = false;

    private boolean mRemoveUnitSpace = false;


    public TimeSpannableBuilder(final float time, final float proportion) {
        mTime = time;
        mProportion = proportion;
    }

    public TimeSpannableBuilder appendPreFix(final String preFix) {
        mPreFix = preFix;
        isSpacePrefix = false;
        return this;
    }

    public TimeSpannableBuilder appendSpacePreFix() {
        mPreFix = "  ";
        isSpacePrefix = true;
        return this;
    }

    public TimeSpannableBuilder appendSuffix(final String suffix) {
        mSuffix = suffix;
        isSpaceSuffix = false;
        return this;
    }

    public TimeSpannableBuilder appendSpaceSuffix() {
        mSuffix = "  ";
        isSpaceSuffix = true;
        return this;
    }

    public TimeSpannableBuilder appendPlus() {
        mPlus = "+";
        return this;
    }

    public TimeSpannableBuilder keepMins() {
        mKeepMins = true;
        return this;
    }

    public TimeSpannableBuilder removeUnitSpace() {
        mRemoveUnitSpace = true;
        return this;
    }

    public Spannable getSpannable() {
        StringBuilder sb = new StringBuilder();
        sb.append(mPreFix);
        sb.append(mPlus);
        sb.append(mSuffix);

        final int h = (int) mTime / 60;

        final String hourToken = getUnitByCheckRemoveSpace(" h");
        String minToken;

        if (!mKeepMins) {
            //串h
            if (h != 0) {
                final int hFlt = getFlt(h);
//                CommonLog.d(DEG, TAG, "getSpannable: h = " + h + ", hFlt = " + hFlt);
                sb.append(String.format("%" + hFlt + "d", h)).append(hourToken).append(" ");
            }
            //串m
            minToken = (h > 0) ? " m" : " mins";
            minToken = getUnitByCheckRemoveSpace(minToken);
            final int m = (int) mTime % 60;
            appendMinToStringBuilder(sb, m, minToken);
        } else {
            //串m
            minToken = (mTime > 1) ? " mins" : " min";
            minToken = getUnitByCheckRemoveSpace(minToken);
            appendMinToStringBuilder(sb, (int)mTime, minToken);
        }

//        String.format("%02d", h) + "h " + String.format("%02d", m) + "m";
        final String timeString = sb.toString();
        final Spannable span = new SpannableString(timeString);

        //縮小空格的Prefix, to follow ui guide
        if (mPreFix.equals("  ") && isSpacePrefix) {
//            CommonLog.d(DEG, TAG, "appendSpacePreFix:");
            int index_PreFix = timeString.indexOf(mPreFix);
            span.setSpan(new RelativeSizeSpan(mProportion + 0.1f), index_PreFix, index_PreFix + mPreFix.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new TypefaceSpan("sans-serif"), index_PreFix, index_PreFix + mPreFix.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        //縮小空格的suffix, to follow ui guide
        if (mSuffix.equals("  ") && isSpaceSuffix) {
//            CommonLog.d(DEG, TAG, "appendSpaceSuffix:");
            int index_Suffix = timeString.lastIndexOf(mSuffix);
            span.setSpan(new RelativeSizeSpan(mProportion + 0.1f), index_Suffix, index_Suffix + mSuffix.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new TypefaceSpan("sans-serif"), index_Suffix, index_Suffix + mSuffix.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        //縮小h
        if (h != 0 && !mKeepMins) {
            int index_h = timeString.indexOf(hourToken);
            span.setSpan(new RelativeSizeSpan(mProportion), index_h, index_h + hourToken.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new TypefaceSpan("sans-serif"),index_h, index_h + hourToken.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        //縮小m
        int index_m = timeString.indexOf(minToken);
        span.setSpan(new RelativeSizeSpan(mProportion), index_m, index_m + minToken.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new TypefaceSpan("sans-serif"), index_m, index_m + minToken.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return span;

    }

    private String getUnitByCheckRemoveSpace(String unitStr) {
        if (mRemoveUnitSpace) {
            unitStr = unitStr.replace(" ", "");
        }
        return unitStr;
    }

    private int getFlt(double val) {
        if (val <= 0) {
            return 1;
        } else {
            //val = 9, flt =0.x
            //val = 10, flt =1
            //val = 11, flt =1.041
            //val = 100, flt =2
            //val = 101, flt =2.0043
            return ((int) Math.log10(val) + 1);
        }
    }

    private void appendMinToStringBuilder(final StringBuilder sb, final int m, final String minToken) {
        final int mFlt = getFlt(m);
//        CommonLog.d(DEG, TAG, "getSpannable: m = " + m + ", mFlt = " + mFlt);
        sb.append(String.format("%" + mFlt + "d", m)).append(minToken);
    }

}
