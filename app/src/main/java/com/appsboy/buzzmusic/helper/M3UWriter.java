package com.appsboy.buzzmusic.helper;

import android.content.Context;

import com.appsboy.buzzmusic.loader.PlaylistSongLoader;
import com.appsboy.buzzmusic.model.Playlist;
import com.appsboy.buzzmusic.model.Song;
import com.appsboy.buzzmusic.model.PlaylistSong;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * @author Mohit Arora
 */
@SuppressWarnings("ALL")
public class M3UWriter implements M3UConstants {
    public static final String TAG = M3UWriter.class.getSimpleName();

    public static File write(Context context, File dir, Playlist playlist) throws IOException {
        if (!dir.exists()) //noinspection ResultOfMethodCallIgnored
            dir.mkdirs();
        File file = new File(dir, playlist.name.concat("." + EXTENSION));

        ArrayList<PlaylistSong> songs = PlaylistSongLoader.getPlaylistSongList(context, playlist.id);

        if (songs.size() > 0) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            bw.write(HEADER);
            for (Song song : songs) {
                bw.newLine();
                bw.write(ENTRY + song.duration + DURATION_SEPARATOR + song.artistName + " - " + song.title);
                bw.newLine();
                bw.write(song.data);
            }

            bw.close();
        }

        return file;
    }
}