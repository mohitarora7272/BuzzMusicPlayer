package com.appsboy.buzzmusic.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.module.GlideModule;
import com.appsboy.buzzmusic.glide.artistimage.ArtistImage;
import com.appsboy.buzzmusic.glide.artistimage.ArtistImageLoader;
import com.appsboy.buzzmusic.glide.audiocover.AudioFileCover;
import com.appsboy.buzzmusic.glide.audiocover.AudioFileCoverLoader;

import java.io.InputStream;

/**
 * @author Mohit Arora
 */
@SuppressWarnings("ALL")
public class AlphaMusicGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(AudioFileCover.class, InputStream.class, new AudioFileCoverLoader.Factory());
        glide.register(ArtistImage.class, InputStream.class, new ArtistImageLoader.Factory(context));
    }
}
