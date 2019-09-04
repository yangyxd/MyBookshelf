package com.kunfei.bookshelf.widget.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.kunfei.bookshelf.R;
import com.kunfei.bookshelf.utils.ColorUtil;
import com.kunfei.bookshelf.utils.DensityUtil;
import com.kunfei.bookshelf.utils.ScreenUtils;
import com.kunfei.bookshelf.utils.Selector;
import com.kunfei.bookshelf.utils.theme.ThemeStore;

public class ATEAccentBgTextView extends AppCompatTextView {
    public ATEAccentBgTextView(Context context) {
        super(context);
        init(context, null);
    }

    public ATEAccentBgTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ATEAccentBgTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ATEAccentBgTextView);

        setBackground(Selector.shapeBuild()
                .setCornerRadius(a.getDimensionPixelSize(R.styleable.ATEAccentBgTextView_cornerRadius, DensityUtil.dp2px(context, 3.0f)))
                .setDefaultBgColor(ThemeStore.accentColor(context))
                .setPressedBgColor(ColorUtil.darkenColor(ThemeStore.accentColor(context)))
                .create());
        setTextColor(a.getColor(R.styleable.ATEAccentBgTextView_textColor, Color.WHITE));
    }
}
