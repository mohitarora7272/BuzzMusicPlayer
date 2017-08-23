package com.appsboy.buzzmusic.service.notification;

import com.appsboy.buzzmusic.service.MusicService;

/**
 * @author Mohit Arora
 */

public interface PlayingNotification {
    int NOTIFICATION_ID = 1;

    void init(MusicService service);

    void update();

    void stop();
}
