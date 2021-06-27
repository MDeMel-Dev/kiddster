package com.example.kiddster.util;


import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

public class WordPrint extends androidx.appcompat.widget.AppCompatTextView {


    private CharSequence mText;
    private int mIndex;
    private long mDelay = 150; // in ms
    public WordPrint(Context context) {
        super(context);
    }
    public WordPrint (Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private Handler mHandler = new Handler ();
    private Runnable characterAdder = new Runnable () {
        @Override
        public void run() {
            setText(mText.subSequence(0, mIndex++));
            if (mIndex <= mText.length()) {
                mHandler.postDelayed(characterAdder, mDelay);
            }
        }
    };
    public void animateText(CharSequence txt) {
        mText = txt;
        mIndex = 0;
        setText("");
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);
    }
    public void setCharacterDelay(long m) {
        mDelay = m;
    }
}