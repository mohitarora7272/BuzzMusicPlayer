package com.alpha.music.ui.fragments.player;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alpha.music.glide.SongGlideRequest;
import com.alpha.music.helper.MusicPlayerRemote;
import com.alpha.music.helper.MusicProgressViewUpdateHelper;
import com.alpha.music.helper.PlayPauseButtonOnClickHandler;
import com.alpha.music.ui.fragments.AbsMusicServiceFragment;
import com.alpha.music.views.PlayPauseDrawable;
import com.bumptech.glide.Glide;
import com.kabouzeid.appthemehelper.ThemeStore;
import com.kabouzeid.appthemehelper.util.ATHUtil;
import com.alpha.music.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * @author Mohit Arora
 */
public class MiniPlayerFragment extends AbsMusicServiceFragment implements MusicProgressViewUpdateHelper.Callback {

    private Unbinder unbinder;

    @BindView(R.id.mini_player_title)
    TextView miniPlayerTitle;
    @BindView(R.id.mini_player_play_pause_button)
    ImageView miniPlayerPlayPauseButton;
    @BindView(R.id.progress_bar)
    MaterialProgressBar progressBar;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.player_prev_button)
    ImageButton prevButton;
    @BindView(R.id.player_next_button)
    ImageButton nextButton;

    private PlayPauseDrawable miniPlayerPlayPauseDrawable;

    private MusicProgressViewUpdateHelper progressViewUpdateHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressViewUpdateHelper = new MusicProgressViewUpdateHelper(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mini_player, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        view.setOnTouchListener(new FlingPlayBackController(getActivity()));
        setUpMiniPlayer();
        setUpPrevNext();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setUpMiniPlayer() {
        setUpPlayPauseButton();
        progressBar.setProgressTintList(ColorStateList.valueOf(ThemeStore.accentColor(getActivity())));
    }

    private void setUpPrevNext() {
        updatePrevNextColor();
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicPlayerRemote.playNextSong();
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicPlayerRemote.back();
            }
        });
    }

    private void updatePrevNextColor() {
        nextButton.setColorFilter(ATHUtil.resolveColor(getActivity(), R.attr.iconColor, ThemeStore.textColorSecondary(getActivity())), PorterDuff.Mode.SRC_IN);
        prevButton.setColorFilter(ATHUtil.resolveColor(getActivity(), R.attr.iconColor, ThemeStore.textColorSecondary(getActivity())), PorterDuff.Mode.SRC_IN);
    }

    private void setUpPlayPauseButton() {
        miniPlayerPlayPauseDrawable = new PlayPauseDrawable(getActivity());
        miniPlayerPlayPauseButton.setImageDrawable(miniPlayerPlayPauseDrawable);
        miniPlayerPlayPauseButton.setColorFilter(ATHUtil.resolveColor(getActivity(), R.attr.iconColor, ThemeStore.textColorSecondary(getActivity())), PorterDuff.Mode.SRC_IN);
        miniPlayerPlayPauseButton.setOnClickListener(new PlayPauseButtonOnClickHandler());
    }

    private void updateSongTitle() {
        miniPlayerTitle.setText(MusicPlayerRemote.getCurrentSong().title);
    }

    @Override
    public void onServiceConnected() {
        updateSongTitle();
        loadAlbumCover();
        updatePlayPauseDrawableState(false);
    }

    private void loadAlbumCover() {
        if (image == null) return;
        SongGlideRequest.Builder.from(Glide.with(getActivity()), MusicPlayerRemote.getCurrentSong())
                .checkIgnoreMediaStore(getActivity())
                .build().into(image);
    }

    @Override
    public void onPlayingMetaChanged() {
        updateSongTitle();
        loadAlbumCover();
    }

    @Override
    public void onPlayStateChanged() {
        updatePlayPauseDrawableState(true);
    }

    @Override
    public void onUpdateProgressViews(int progress, int total) {
        progressBar.setMax(total);
        progressBar.setProgress(progress);
    }

    @Override
    public void onResume() {
        super.onResume();
        progressViewUpdateHelper.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        progressViewUpdateHelper.stop();
    }

    private static class FlingPlayBackController implements View.OnTouchListener {

        GestureDetector flingPlayBackController;

        FlingPlayBackController(Context context) {
            flingPlayBackController = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    if (Math.abs(velocityX) > Math.abs(velocityY)) {
                        if (velocityX < 0) {
                            MusicPlayerRemote.playNextSong();
                            return true;
                        } else if (velocityX > 0) {
                            MusicPlayerRemote.playPreviousSong();
                            return true;
                        }
                    }
                    return false;
                }
            });
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return flingPlayBackController.onTouchEvent(event);
        }
    }

    protected void updatePlayPauseDrawableState(boolean animate) {
        if (MusicPlayerRemote.isPlaying()) {
            miniPlayerPlayPauseDrawable.setPause(animate);
        } else {
            miniPlayerPlayPauseDrawable.setPlay(animate);
        }
    }
}
