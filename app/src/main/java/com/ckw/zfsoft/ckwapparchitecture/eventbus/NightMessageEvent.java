package com.ckw.zfsoft.ckwapparchitecture.eventbus;

/**
 * Created by ckw
 * on 2018/1/8.
 */

public class NightMessageEvent {

    private boolean isNight;

    public NightMessageEvent(boolean isNight) {
        this.isNight = isNight;
    }

    public boolean isNight() {
        return isNight;
    }

    public void setNight(boolean night) {
        isNight = night;
    }
}
