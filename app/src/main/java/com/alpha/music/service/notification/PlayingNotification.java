package com.alpha.music.service.notification;

import com.alpha.music.service.MusicService;

/**
 * @author Mohit Arora
 */

public interface PlayingNotification {
    int NOTIFICATION_ID = 1;

    void init(MusicService service);

    void update();

    void stop();
}
