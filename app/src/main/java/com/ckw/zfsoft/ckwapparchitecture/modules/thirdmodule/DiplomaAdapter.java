package com.ckw.zfsoft.ckwapparchitecture.modules.thirdmodule;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ckw.zfsoft.ckwapparchitecture.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by ckw
 * on 2018/1/11.
 */

public class DiplomaAdapter extends RecyclerArrayAdapter<String> {
    public DiplomaAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent,R.layout.item_diploma_adapter);
    }

    class ViewHolder extends BaseViewHolder<String>{

        TextView mTxt;
        public ViewHolder(ViewGroup parent, int res) {
            super(parent, res);
            mTxt = $(R.id.tv_text);
        }

        @Override
        public void setData(String data) {
            super.setData(data);
            mTxt.setText(data);
        }
    }


}
