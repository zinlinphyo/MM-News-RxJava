package com.padcmyanmar.sfc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.adapters.GoogleNewsAdapter;
import com.padcmyanmar.sfc.adapters.NewsAdapter;
import com.padcmyanmar.sfc.components.EmptyViewPod;
import com.padcmyanmar.sfc.components.SmartRecyclerView;
import com.padcmyanmar.sfc.components.SmartScrollListener;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.mvp.presenters.NewsListPresenter;
import com.padcmyanmar.sfc.mvp.views.NewsListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;

public class GoogleNewsListActivity extends BaseActivity
        implements NewsListView {

    private GoogleNewsAdapter mNewsAdapter;

    private PublishSubject<List<NewsVO>> mNewsSubject;

    private NewsListPresenter mPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_google_news)
    SmartRecyclerView rvNews;

    @BindView(R.id.vp_empty_news)
    EmptyViewPod vpEmptyNews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_google_news_list);
        ButterKnife.bind(this, this);

        mPresenter = new NewsListPresenter(this);
        mPresenter.onCreate();

        setSupportActionBar(toolbar);

        rvNews.setEmptyView(vpEmptyNews);
        rvNews.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mNewsAdapter = new GoogleNewsAdapter(getApplicationContext(), mPresenter);
        rvNews.setAdapter(mNewsAdapter);

        mPresenter.onFinishSetupUIComponent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void displayNewsList(List<NewsVO> newsList) {
        mNewsAdapter.appendNewData(newsList);
    }

    @Override
    public void launchNewsDetailsScreen(String newsId) {
        Intent intent = NewsDetailsActivity.newIntent(getApplicationContext(), newsId);
        startActivity(intent);
    }

    @Override
    public void displayErrorMsg(String errorMsg) {
        Snackbar.make(rvNews, errorMsg, Snackbar.LENGTH_INDEFINITE).show();
    }
}
