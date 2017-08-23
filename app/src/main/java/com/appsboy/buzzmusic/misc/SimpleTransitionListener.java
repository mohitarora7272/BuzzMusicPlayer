package com.appsboy.buzzmusic.misc;

import android.annotation.TargetApi;
import android.os.Build;
import android.transition.Transition;

/**
 * @author Mohit Arora
 */
@SuppressWarnings("ALL")
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public abstract class SimpleTransitionListener implements Transition.TransitionListener {
    @Override
    public void onTransitionStart(Transition transition) {
    }

    @Override
    public void onTransitionEnd(Transition transition) {
    }

    @Override
    public void onTransitionCancel(Transition transition) {
    }

    @Override
    public void onTransitionPause(Transition transition) {
    }

    @Override
    public void onTransitionResume(Transition transition) {
    }
}
