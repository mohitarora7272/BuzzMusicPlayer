package com.appsboy.buzzmusic.glide.palette;

import android.widget.ImageView;

import com.bumptech.glide.request.target.ImageViewTarget;
/**
 * @author Mohit Arora
 */
@SuppressWarnings("ALL")
public class BitmapPaletteTarget extends ImageViewTarget<BitmapPaletteWrapper> {
    public BitmapPaletteTarget(ImageView view) {
        super(view);
    }

    @Override
    protected void setResource(BitmapPaletteWrapper bitmapPaletteWrapper) {
        view.setImageBitmap(bitmapPaletteWrapper.getBitmap());
    }
}
