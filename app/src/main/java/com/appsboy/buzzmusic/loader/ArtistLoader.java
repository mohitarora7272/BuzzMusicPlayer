package com.appsboy.buzzmusic.loader;

import android.content.Context;
import android.provider.MediaStore.Audio.AudioColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.appsboy.buzzmusic.model.Album;
import com.appsboy.buzzmusic.model.Song;
import com.appsboy.buzzmusic.util.PreferenceUtil;
import com.appsboy.buzzmusic.model.Artist;

import java.util.ArrayList;

/**
 * @author Mohit Arora
 */
@SuppressWarnings("ALL")
public class ArtistLoader {
    public static String getSongLoaderSortOrder(Context context) {
        return PreferenceUtil.getInstance(context).getArtistSortOrder() + ", " + PreferenceUtil.getInstance(context).getArtistAlbumSortOrder() + ", " + PreferenceUtil.getInstance(context).getAlbumSongSortOrder();
    }

    @NonNull
    public static ArrayList<Artist> getAllArtists(@NonNull final Context context) {
        ArrayList<Song> songs = SongLoader.getSongs(SongLoader.makeSongCursor(
                context,
                null,
                null,
                getSongLoaderSortOrder(context))
        );
        return splitIntoArtists(AlbumLoader.splitIntoAlbums(songs));
    }

    @NonNull
    public static ArrayList<Artist> getArtists(@NonNull final Context context, String query) {
        ArrayList<Song> songs = SongLoader.getSongs(SongLoader.makeSongCursor(
                context,
                AudioColumns.ARTIST + " LIKE ?",
                new String[]{"%" + query + "%"},
                getSongLoaderSortOrder(context))
        );
        return splitIntoArtists(AlbumLoader.splitIntoAlbums(songs));
    }

    @NonNull
    public static Artist getArtist(@NonNull final Context context, int artistId) {
        ArrayList<Song> songs = SongLoader.getSongs(SongLoader.makeSongCursor(
                context,
                AudioColumns.ARTIST_ID + "=?",
                new String[]{String.valueOf(artistId)},
                getSongLoaderSortOrder(context))
        );
        return new Artist(AlbumLoader.splitIntoAlbums(songs));
    }

    @NonNull
    public static ArrayList<Artist> splitIntoArtists(@Nullable final ArrayList<Album> albums) {
        ArrayList<Artist> artists = new ArrayList<>();
        if (albums != null) {
            for (Album album : albums) {
                getOrCreateArtist(artists, album.getArtistId()).albums.add(album);
            }
        }
        return artists;
    }

    private static Artist getOrCreateArtist(ArrayList<Artist> artists, int artistId) {
        for (Artist artist : artists) {
            if (!artist.albums.isEmpty() && !artist.albums.get(0).songs.isEmpty() && artist.albums.get(0).songs.get(0).artistId == artistId) {
                return artist;
            }
        }
        Artist album = new Artist();
        artists.add(album);
        return album;
    }
}