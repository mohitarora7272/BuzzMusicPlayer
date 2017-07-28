package com.alpha.music.model.smartplaylist;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.alpha.music.model.Song;
import com.alpha.music.provider.SongPlayCountStore;
import com.alpha.music.R;
import com.alpha.music.loader.TopAndRecentlyPlayedTracksLoader;

import java.util.ArrayList;

/**
 * @author Mohit Arora
 */
public class MyTopTracksPlaylist extends AbsSmartPlaylist {

    public MyTopTracksPlaylist(@NonNull Context context) {
        super(context.getString(R.string.my_top_tracks), R.drawable.ic_trending_up_white_24dp);
    }

    @NonNull
    @Override
    public ArrayList<Song> getSongs(@NonNull Context context) {
        return TopAndRecentlyPlayedTracksLoader.getTopTracks(context);
    }

    @Override
    public void clear(@NonNull Context context) {
        SongPlayCountStore.getInstance(context).clear();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    protected MyTopTracksPlaylist(Parcel in) {
        super(in);
    }

    public static final Parcelable.Creator<MyTopTracksPlaylist> CREATOR = new Parcelable.Creator<MyTopTracksPlaylist>() {
        public MyTopTracksPlaylist createFromParcel(Parcel source) {
            return new MyTopTracksPlaylist(source);
        }

        public MyTopTracksPlaylist[] newArray(int size) {
            return new MyTopTracksPlaylist[size];
        }
    };
}