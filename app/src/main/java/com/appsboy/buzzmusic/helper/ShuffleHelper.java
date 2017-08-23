package com.appsboy.buzzmusic.helper;

import android.support.annotation.NonNull;

import com.appsboy.buzzmusic.model.Song;

import java.util.Collections;
import java.util.List;

/**
 * @author Mohit Arora
 */
public class ShuffleHelper {

    public static void makeShuffleList(@NonNull List<Song> listToShuffle, final int current) {
        if (listToShuffle.isEmpty()) return;
        if (current >= 0) {
            Song song = listToShuffle.remove(current);
            Collections.shuffle(listToShuffle);
            listToShuffle.add(0, song);
        } else {
            Collections.shuffle(listToShuffle);
        }
    }
}
