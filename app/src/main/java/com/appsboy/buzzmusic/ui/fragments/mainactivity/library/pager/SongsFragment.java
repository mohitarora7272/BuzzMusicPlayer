package com.appsboy.buzzmusic.ui.fragments.mainactivity.library.pager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;

import com.appsboy.buzzmusic.model.Song;
import com.appsboy.buzzmusic.util.PreferenceUtil;
import com.appsboy.buzzmusic.R;
import com.appsboy.buzzmusic.adapter.song.ShuffleButtonSongAdapter;
import com.appsboy.buzzmusic.adapter.song.SongAdapter;
import com.appsboy.buzzmusic.interfaces.LoaderIds;
import com.appsboy.buzzmusic.loader.SongLoader;
import com.appsboy.buzzmusic.misc.WrappedAsyncTaskLoader;

import java.util.ArrayList;

/**
 * @author Mohit Arora
 */
@SuppressWarnings("ALL")
public class SongsFragment extends AbsLibraryPagerRecyclerViewCustomGridSizeFragment<SongAdapter, GridLayoutManager> implements LoaderManager.LoaderCallbacks<ArrayList<Song>> {

    public static final String TAG = SongsFragment.class.getSimpleName();

    private static final int LOADER_ID = LoaderIds.SONGS_FRAGMENT;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @NonNull
    @Override
    protected GridLayoutManager createLayoutManager() {
        return new GridLayoutManager(getActivity(), getGridSize());
    }

    @NonNull
    @Override
    protected SongAdapter createAdapter() {
        int itemLayoutRes = getItemLayoutRes();
        notifyLayoutResChanged(itemLayoutRes);
        boolean usePalette = loadUsePalette();
        ArrayList<Song> dataSet = getAdapter() == null ? new ArrayList<Song>() : getAdapter().getDataSet();

        if (getGridSize() <= getMaxGridSizeForList()) {
            return new ShuffleButtonSongAdapter(
                    getLibraryFragment().getMainActivity(),
                    dataSet,
                    itemLayoutRes,
                    usePalette,
                    getLibraryFragment());
        }
        return new SongAdapter(
                getLibraryFragment().getMainActivity(),
                dataSet,
                itemLayoutRes,
                usePalette,
                getLibraryFragment());
    }

    @Override
    protected int getEmptyMessage() {
        return R.string.no_songs;
    }

    @Override
    public void onMediaStoreChanged() {
        getLoaderManager().restartLoader(LOADER_ID, null, this);
    }

    @Override
    protected int loadGridSize() {
        return PreferenceUtil.getInstance(getActivity()).getSongGridSize(getActivity());
    }

    @Override
    protected void saveGridSize(int gridSize) {
        PreferenceUtil.getInstance(getActivity()).setSongGridSize(gridSize);
    }

    @Override
    protected int loadGridSizeLand() {
        return PreferenceUtil.getInstance(getActivity()).getSongGridSizeLand(getActivity());
    }

    @Override
    protected void saveGridSizeLand(int gridSize) {
        PreferenceUtil.getInstance(getActivity()).setSongGridSizeLand(gridSize);
    }

    @Override
    public void saveUsePalette(boolean usePalette) {
        PreferenceUtil.getInstance(getActivity()).setSongColoredFooters(usePalette);
    }

    @Override
    public boolean loadUsePalette() {
        return PreferenceUtil.getInstance(getActivity()).songColoredFooters();
    }

    @Override
    public void setUsePalette(boolean usePalette) {
        getAdapter().usePalette(usePalette);
    }

    @Override
    protected void setGridSize(int gridSize) {
        getLayoutManager().setSpanCount(gridSize);
        getAdapter().notifyDataSetChanged();
    }

    @Override
    public Loader<ArrayList<Song>> onCreateLoader(int id, Bundle args) {
        return new AsyncSongLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Song>> loader, ArrayList<Song> data) {
        getAdapter().swapDataSet(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Song>> loader) {
        getAdapter().swapDataSet(new ArrayList<Song>());
    }

    private static class AsyncSongLoader extends WrappedAsyncTaskLoader<ArrayList<Song>> {
        public AsyncSongLoader(Context context) {
            super(context);
        }

        @Override
        public ArrayList<Song> loadInBackground() {
            return SongLoader.getAllSongs(getContext());
        }
    }
}
