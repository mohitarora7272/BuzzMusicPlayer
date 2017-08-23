package com.appsboy.buzzmusic.glide;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.appsboy.buzzmusic.glide.palette.BitmapPaletteWrapper;
import com.appsboy.buzzmusic.util.AlphaMusicColorUtil;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.kabouzeid.appthemehelper.util.ATHUtil;
import com.appsboy.buzzmusic.R;
import com.appsboy.buzzmusic.glide.palette.BitmapPaletteTarget;
/**
 * @author Mohit Arora
 */
@SuppressWarnings("ALL")
public abstract class AlphaMusicColoredTarget extends BitmapPaletteTarget {
    protected AlphaMusicColoredTarget(ImageView view) {
        super(view);
    }

    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        super.onLoadFailed(e, errorDrawable);
        onColorReady(getDefaultFooterColor());
    }

    @Override
    public void onResourceReady(BitmapPaletteWrapper resource, GlideAnimation<? super BitmapPaletteWrapper> glideAnimation) {
        super.onResourceReady(resource, glideAnimation);
        onColorReady(AlphaMusicColorUtil.getColor(resource.getPalette(), getDefaultFooterColor()));
    }

    protected int getDefaultFooterColor() {
        return ATHUtil.resolveColor(getView().getContext(), R.attr.iconColor);
    }

    protected int getAlbumArtistFooterColor() {
        return ATHUtil.resolveColor(getView().getContext(), R.attr.iconColor);
    }

    public abstract void onColorReady(int color);
}
