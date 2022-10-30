package com.example.easeoffapplication.EatHealthy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;

public class Reminder extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer player=MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI);
        player.start();
    }
}
