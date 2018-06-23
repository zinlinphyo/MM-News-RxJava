package com.padcmyanmar.sfc.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.delegates.NewsItemDelegate;
import com.padcmyanmar.sfc.viewholders.GoogleNewsViewHolder;

public class GoogleNewsAdapter extends BaseRecyclerAdapter<GoogleNewsViewHolder, NewsVO> {

    private NewsItemDelegate mNewsItemDelegate;

    public GoogleNewsAdapter(Context context, NewsItemDelegate newsItemDelegate) {
        super(context);
        mNewsItemDelegate = newsItemDelegate;
    }

    @NonNull
    @Override
    public GoogleNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View googleNewsItemView = mLayoutInflator.inflate(R.layout.view_item_google_news, parent, false);
        return new GoogleNewsViewHolder(googleNewsItemView, mNewsItemDelegate);
    }
}
