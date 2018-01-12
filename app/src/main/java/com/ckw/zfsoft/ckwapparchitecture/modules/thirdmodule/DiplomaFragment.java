package com.ckw.zfsoft.ckwapparchitecture.modules.thirdmodule;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.ckw.zfsoft.ckwapparchitecture.base.BaseFragment;
import com.ckw.zfsoft.ckwapparchitecture.di.ActivityScoped;
import com.github.clans.fab.FloatingActionButton;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import uk.co.senab.photoview.log.LoggerDefault;

/**
 * Created by ckw
 * on 2017/12/27.
 */
@ActivityScoped
public class DiplomaFragment extends BaseFragment {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private List<String> mData;
    private DiplomaAdapter mAdapter;
    private int mCount = 1;

    @Inject
    public DiplomaFragment(){

    }
    
    @Override
    public void initPresenter() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_diploma;
    }

    @Override
    protected void initVariables() {
        mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mData.add("测试数据"+i);
        }
        mAdapter = new DiplomaAdapter(getContext());
        mAdapter.addAll(mData);

    }

    @Override
    protected void handleBundle(Bundle bundle) {

    }

    @Override
    protected void operateViews(View view) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        mFab.hide(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mFab.show(true);
                mFab.setShowAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.show_from_bottom));
                mFab.setHideAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.hide_to_bottom));
            }
        }, 300);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initListener() {
        mSmartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1500);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 3; i++) {
                            mData.add("第"+mCount+"次刷新的数据："+i);
                        }
                        mCount++;
                        mAdapter.clear();
                        mAdapter.addAll(mData);
                        mAdapter.notifyDataSetChanged();
                        refreshlayout.finishLoadmore();
                    }
                },1500);
            }
        });


        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy>0){//上拉操作
                    mFab.hide(true);
                }else {//下拉
                    mFab.show(true);
                }
            }

        });

        /**
         * 滑动到顶部
         */
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.scrollToPosition(0);
            }
        });

    }
}
