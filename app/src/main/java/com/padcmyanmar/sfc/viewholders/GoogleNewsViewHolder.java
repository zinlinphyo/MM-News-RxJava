package com.padcmyanmar.sfc.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.delegates.NewsItemDelegate;

import butterknife.BindView;

public class GoogleNewsViewHolder extends BaseViewHolder<NewsVO> {

    @BindView(R.id.iv_google_news_hero_image)
    ImageView ivNewsHeroImage;

    @BindView(R.id.tv_google_news_brief_news)
    TextView tvBriefNews;

    @BindView(R.id.iv_google_news_publication_logo)
    ImageView ivPublicationLogo;

    private NewsItemDelegate mDelegate;

    private NewsVO mNews;

    public GoogleNewsViewHolder(View itemView, NewsItemDelegate newsItemDelegate) {
        super(itemView);
        mDelegate = newsItemDelegate;
    }

    @Override
    public void setData(NewsVO data) {
        mNews = data;

        tvBriefNews.setText(data.getDetails());

        if (!data.getImages().isEmpty()){
            Glide.with(ivNewsHeroImage.getContext())
                    .load(data.getImages().get(0))
                    .into(ivNewsHeroImage);
        } else {
            ivNewsHeroImage.setVisibility(View.GONE);
        }

        Glide.with(ivPublicationLogo.getContext())
                .load(data.getPublication().getLogo())
                .into(ivPublicationLogo);
    }

    @Override
    public void onClick(View view) {
        mDelegate.onTapNews(mNews);
    }
}
