package com.ckw.zfsoft.ckwapparchitecture.modules.firstmodule.phone.phone_inject;

/**
 * Created by ckw
 * on 2018/1/30.
 */

public class PhoneInfo {
//     "xm":"叶成春",
//             "depname":"销售部-协同办公"
    private String xm;
    private String depname;

    public PhoneInfo(String xm, String depname) {
        this.xm = xm;
        this.depname = depname;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    @Override
    public String toString() {
        return "PhoneInfo{" +
                "xm='" + xm + '\'' +
                ", depname='" + depname + '\'' +
                '}';
    }
}
