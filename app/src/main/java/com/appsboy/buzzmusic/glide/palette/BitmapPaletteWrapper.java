package com.appsboy.buzzmusic.glide.palette;

import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
/**
 * @author Mohit Arora
 */
public class BitmapPaletteWrapper {
    private final Bitmap mBitmap;
    private final Palette mPalette;

    public BitmapPaletteWrapper(Bitmap bitmap, Palette palette) {
        mBitmap = bitmap;
        mPalette = palette;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public Palette getPalette() {
        return mPalette;
    }
}