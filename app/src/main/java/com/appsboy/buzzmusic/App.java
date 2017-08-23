package com.appsboy.buzzmusic;

import android.app.Application;
import android.os.Build;

import com.appsboy.buzzmusic.appshortcuts.DynamicShortcutManager;

/**
 * @author Mohit Arora
 */
@SuppressWarnings("ALL")
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Set up dynamic shortcuts
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            new DynamicShortcutManager(this).initDynamicShortcuts();
        }
    }
}