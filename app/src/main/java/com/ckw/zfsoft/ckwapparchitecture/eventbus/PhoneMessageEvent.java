package com.ckw.zfsoft.ckwapparchitecture.eventbus;

/**
 * Created by ckw
 * on 2018/1/29.
 */

public class PhoneMessageEvent {
    int phoneState;

    public PhoneMessageEvent(int phoneState) {
        this.phoneState = phoneState;
    }

    public int getPhoneState() {
        return phoneState;
    }

    public void setPhoneState(int phoneState) {
        this.phoneState = phoneState;
    }
}
