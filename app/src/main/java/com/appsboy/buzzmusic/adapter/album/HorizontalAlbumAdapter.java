package com.appsboy.buzzmusic.adapter.album;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.appsboy.buzzmusic.glide.AlphaMusicColoredTarget;
import com.appsboy.buzzmusic.glide.SongGlideRequest;
import com.appsboy.buzzmusic.helper.HorizontalAdapterHelper;
import com.appsboy.buzzmusic.interfaces.CabHolder;
import com.appsboy.buzzmusic.model.Album;
import com.bumptech.glide.Glide;
import com.kabouzeid.appthemehelper.ThemeStore;

import java.util.ArrayList;

/**
 * @author Mohit Arora
 */
@SuppressWarnings("ALL")
public class HorizontalAlbumAdapter extends AlbumAdapter {
    public static final String TAG = AlbumAdapter.class.getSimpleName();
    private int lastPlaybackControlsColor = -1;

    public HorizontalAlbumAdapter(@NonNull AppCompatActivity activity, ArrayList<Album> dataSet, boolean usePalette, @Nullable CabHolder cabHolder) {
        super(activity, dataSet, HorizontalAdapterHelper.LAYOUT_RES, usePalette, cabHolder);
    }

    @Override
    protected ViewHolder createViewHolder(View view, int viewType) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        HorizontalAdapterHelper.applyMarginToLayoutParams(activity, params, viewType);
        return new ViewHolder(view);
    }

    @Override
    protected void setColors(int color, ViewHolder holder) {
        if (holder.itemView != null) {
            View card = holder.itemView;
            card.setBackgroundColor(color);
            if (holder.title != null) {
                //holder.title.setTextColor(MaterialValueHelper.getPrimaryTextColor(activity, ColorUtil.isColorLight(color)));
                holder.title.setTextColor(lastPlaybackControlsColor);
            }
            if (holder.text != null) {
                //holder.text.setTextColor(MaterialValueHelper.getSecondaryTextColor(activity, ColorUtil.isColorLight(color)));
                holder.text.setTextColor(lastPlaybackControlsColor);
            }
            holder.image.setBackgroundColor(lastPlaybackControlsColor);
        }
    }

    @Override
    protected void loadAlbumCover(Album album, final ViewHolder holder) {
        if (holder.image == null) return;

        SongGlideRequest.Builder.from(Glide.with(activity), album.safeGetFirstSong())
                .checkIgnoreMediaStore(activity)
                .generatePalette(activity).build()
                .into(new AlphaMusicColoredTarget(holder.image) {
                    @Override
                    public void onLoadCleared(Drawable placeholder) {
                        super.onLoadCleared(placeholder);
                        setColors(getAlbumArtistFooterColor(), holder);
                    }

                    @Override
                    public void onColorReady(int color) {
                        if (usePalette) {
                            int colorNew = ThemeStore.primaryColorDark(activity);
                            setColors(colorNew, holder);
                        } else {
                            setColors(getAlbumArtistFooterColor(), holder);
                        }
                    }
                });
    }

    @Override
    protected String getAlbumText(Album album) {
        return String.valueOf(album.getYear());
    }

    @Override
    public int getItemViewType(int position) {
        return HorizontalAdapterHelper.getItemViewtype(position, getItemCount());
    }
}
