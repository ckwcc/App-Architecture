package com.ckw.zfsoft.ckwapparchitecture.modules.fifthmodule

import android.os.Bundle
import android.view.View
import com.ckw.zfsoft.ckwapparchitecture.R
import com.ckw.zfsoft.ckwapparchitecture.base.BaseFragment

/**
 * Created by ckw
 * on 2017/12/13.
 */
class MedalFragment: BaseFragment() {

    fun newInstance(): MedalFragment {
        val args = Bundle()
        val fragment = MedalFragment()
        fragment.arguments = args
        return fragment
    }

    override fun initPresenter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutResID(): Int {
        return R.layout.fragment_medal
    }

    override fun initVariables() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun handleBundle(bundle: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initViews(view: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initListener() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}