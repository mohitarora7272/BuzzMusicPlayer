package com.appsboy.buzzmusic.loader;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import com.appsboy.buzzmusic.model.Song;
import com.appsboy.buzzmusic.util.PreferenceUtil;

import java.util.ArrayList;

/**
 * @author Mohit Arora
 */
@SuppressWarnings("ALL")
public class ArtistSongLoader extends SongLoader {

    @NonNull
    public static ArrayList<Song> getArtistSongList(@NonNull final Context context, final int artistId) {
        return getSongs(makeArtistSongCursor(context, artistId));
    }

    public static Cursor makeArtistSongCursor(@NonNull final Context context, final int artistId) {
        try {
            return makeSongCursor(
                    context,
                    MediaStore.Audio.AudioColumns.ARTIST_ID + "=?",
                    new String[]{
                            String.valueOf(artistId)
                    },
                    PreferenceUtil.getInstance(context).getArtistSongSortOrder()
            );
        } catch (SecurityException e) {
            return null;
        }
    }
}