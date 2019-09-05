package com.kunfei.bookshelf.help;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/** 先缩小再模糊 */
public class BlurTransformation extends BitmapTransformation {
    private RenderScript rs;
    private int radius;

    public BlurTransformation(Context context, int radius) {
        super();
        rs = RenderScript.create(context);
        this.radius = radius;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        if (radius > 0 && android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            final int maxWidth = 720;
            final int maxHeight = 720;
            final int w = toTransform.getWidth();
            final int h = toTransform.getHeight();

            int blurZoom = 3;
            if (h > maxHeight || w > maxWidth) {
                final int heightRatio = Math.round((float) h / (float) maxHeight);
                final int widthRatio = Math.round((float) w / (float) maxWidth);
                blurZoom = Math.max(blurZoom, heightRatio > widthRatio ? heightRatio : widthRatio);
            };

            ScriptIntrinsicBlur _blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            Bitmap _temp, bm;
            if (blurZoom == 0) {
                bm = toTransform;
                _temp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            } else {
                int tw = w / blurZoom;
                int th = h / blurZoom;
                bm = Bitmap.createScaledBitmap(toTransform, tw, th, false);
                _temp = Bitmap.createBitmap(tw, th, Bitmap.Config.ARGB_8888);
            }
            Allocation _in = Allocation.createFromBitmap(rs, bm);
            Allocation _out = Allocation.createFromBitmap(rs, _temp);
            _blur.setRadius(Math.min(25.0f, radius)); // 0 ~ 25
            _blur.setInput(_in);
            _blur.forEach(_out);
            _out.copyTo(_temp);
            if (blurZoom == 0)
                return Bitmap.createBitmap(_temp);
            else
                return Bitmap.createScaledBitmap(_temp, w, h, true);
        } else
            return toTransform.copy(Bitmap.Config.ARGB_8888, true);
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update("blur transformation".getBytes());
    }
}
