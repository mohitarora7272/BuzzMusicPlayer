package com.appsboy.buzzmusic.ui.fragments.artistdetailactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appsboy.buzzmusic.R;

/**
 * @author Mohit Arora
 */

@SuppressWarnings("ALL")
public class ArtistSongsFragment extends Fragment {


    public ArtistSongsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artist_album_song_list, container, false);
    }

}
