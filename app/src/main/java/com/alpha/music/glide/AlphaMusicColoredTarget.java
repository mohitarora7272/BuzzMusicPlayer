package com.alpha.music.glide;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.alpha.music.glide.palette.BitmapPaletteWrapper;
import com.alpha.music.util.AlphaMusicColorUtil;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.kabouzeid.appthemehelper.util.ATHUtil;
import com.alpha.music.R;
import com.alpha.music.glide.palette.BitmapPaletteTarget;
/**
 * @author Mohit Arora
 */
public abstract class AlphaMusicColoredTarget extends BitmapPaletteTarget {
    public AlphaMusicColoredTarget(ImageView view) {
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
        return ATHUtil.resolveColor(getView().getContext(), R.attr.defaultFooterColor);
    }

    protected int getAlbumArtistFooterColor() {
        return ATHUtil.resolveColor(getView().getContext(), R.attr.cardBackgroundColor);
    }

    public abstract void onColorReady(int color);
}
