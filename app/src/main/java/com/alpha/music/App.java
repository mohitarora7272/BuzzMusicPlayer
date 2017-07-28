package com.alpha.music;

import android.app.Application;
import android.os.Build;

import com.alpha.music.appshortcuts.DynamicShortcutManager;

/**
 * @author Mohit Arora
 */
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
