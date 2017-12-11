package com.ckw.zfsoft.ckwapparchitecture.base;

/**
 * Created by ckw
 * on 2017/12/11.
 */

public interface BaseView<T> {
    /**
     * 让view层持有presenter层的引用
     *
     * @param presenter 所有的presenter都要继承BasePresenter
     */
    void initPresenter();

    /**
     * 判断Fragment是否依附在Activity上
     *
     * @return true:依附在Activity上  false:没有依附在Activity上
     */
    boolean isActive();
}
