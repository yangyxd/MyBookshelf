package com.kunfei.bookshelf.widget.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatSeekBar;

import com.kunfei.bookshelf.R;
import com.kunfei.bookshelf.utils.theme.ATH;
import com.kunfei.bookshelf.utils.theme.ThemeStore;

/**
 * @author Aidan Follestad (afollestad)
 */
public class ATESeekBar extends AppCompatSeekBar {

    public ATESeekBar(Context context) {
        super(context);
        init(context, null);
    }

    public ATESeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ATESeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ATESeekBar);
        ATH.setTint(this, a.getColor(R.styleable.ATESeekBar_tint, ThemeStore.accentColor(context)));
    }
}
