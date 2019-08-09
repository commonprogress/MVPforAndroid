package com.play.library_base.imageloader.transformation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.util.Util;
import com.play.library_base.imageloader.util.Utils;

import java.security.MessageDigest;

/**
 * @author by sunfusheng on 2017/6/6.
 */
public class RadiusTransformation extends BitmapTransformation {
    private final String ID = getClass().getName();

    private int radius;

    public RadiusTransformation(Context context, int radius) {
        this.radius = Utils.dp2px(context, radius);
    }

    @Override
    protected Bitmap transform(BitmapPool pool,Bitmap toTransform, int outWidth, int outHeight) {
        int width = toTransform.getWidth();
        int height = toTransform.getHeight();

        Bitmap bitmap = pool.get(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setHasAlpha(true);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(toTransform, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        canvas.drawRoundRect(new RectF(0, 0, width, height), radius, radius, paint);
        return bitmap;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RadiusTransformation) {
            RadiusTransformation other = (RadiusTransformation) obj;
            return radius == other.radius;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Util.hashCode(ID.hashCode(), Util.hashCode(radius));
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update((ID + radius).getBytes(CHARSET));
    }

}
