package com.alpha.music.interfaces;

import android.support.annotation.NonNull;

import com.afollestad.materialcab.MaterialCab;

/**
 * @author Mohit Arora
 */
public interface CabHolder {

    @NonNull
    MaterialCab openCab(final int menuRes, final MaterialCab.Callback callback);
}
