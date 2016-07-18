package com.example.jason_wu.buildersample.builders.html;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.annotation.StringRes;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;

import java.util.UnknownFormatConversionException;

/**
 * Created by jason_wu on 7/14/16.
 */
public class FormatTextBuilder {


    private static final String TAG = "FormatTextBuilder";

    private final Context mContext;
    private final @StringRes
    int mResId;
    private boolean isBold = false;
    private String mColorCode;
    public FormatTextBuilder(Context context, @StringRes int resId) {
        //Input
        //        Please clean %1$s draining apps and enable Charge Master now!
        mContext = context;
        mResId = resId;
    }

    public FormatTextBuilder makeBold() {
        isBold = true;
        return this;
    }

    public FormatTextBuilder makeColor(String colorCode) {
        //        ED5565
        try {
            Color.parseColor(colorCode);
            mColorCode = colorCode;
            // color is a valid color
        } catch (IllegalArgumentException iae) {
            // This color string is not valid
        }
        return this;
    }

    public CharSequence buildCharByCloudText(final String cloudText, final int count){
        //Output, ex : count = 6
        //        Please clean 6 draining apps and enable Charge Master now!

        final String textFormatForParse = getTextFormat(count);
        final CharSequence localCharSequence = getLocalCharSequence(textFormatForParse);
        if (TextUtils.isEmpty(cloudText)) {
            Log.w(TAG, "getContentText: cloudText is empty, use default localCharSequence = " + localCharSequence);
            return localCharSequence;
        } else {
            return getCloudCharSequence(textFormatForParse, cloudText, localCharSequence);
        }
    }


    private String getTextFormat(final int count) {
        StringBuilder textFormat = new StringBuilder();
        if (!TextUtils.isEmpty(mColorCode)) {
            textFormat.append("<font color=").append(mColorCode).append(">");
        }
        if (isBold) {
            textFormat.append("<strong>");
        }

        textFormat.append(count);

        if (isBold) {
            textFormat.append("</strong>");
        }
        if (!TextUtils.isEmpty(mColorCode)) {
            textFormat.append("</font>");
        }
        return textFormat.toString();
    }

    private CharSequence getLocalCharSequence(final String textFormatForParse) {
        final String localSource = String.format(mContext.getString(mResId, textFormatForParse));
        final CharSequence localDefaultChar = mContext.getString(mResId);
        return parseByHtml(localSource, localDefaultChar);
    }

    private CharSequence getCloudCharSequence(final String textFormatForParse, final String cloudText, final CharSequence cloudDefaultChar) {
        Configuration configuration = new Configuration();
        final String cloudSource = String.format(configuration.locale, cloudText, textFormatForParse);
        return parseByHtml(cloudSource, cloudDefaultChar);
    }

    private CharSequence parseByHtml(final String source, final CharSequence defaultChar) {
        CharSequence cloudCharSequence;
        try {
//            cloudCharSequence = Html.fromHtml(String.format(configuration.locale, cloudText, textFormat.toString()));
            cloudCharSequence = Html.fromHtml(source);
        } catch (UnknownFormatConversionException e) {
            cloudCharSequence = defaultChar;
            Log.w(TAG, "parseByHtml: UnknownFormatConversionException, Use defaultChar = " + defaultChar);
        } catch (Exception e) {
            cloudCharSequence = defaultChar;
            Log.w(TAG, "parseByHtml: Exception, Use defaultStr = " + defaultChar);
        }
        return cloudCharSequence;
    }
}
