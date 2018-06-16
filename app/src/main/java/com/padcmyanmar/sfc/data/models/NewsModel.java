package com.padcmyanmar.sfc.data.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.padcmyanmar.sfc.SFCNewsApp;
import com.padcmyanmar.sfc.data.db.AppDatabase;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.events.RestApiEvents;
import com.padcmyanmar.sfc.network.MMNewsDataAgent;
import com.padcmyanmar.sfc.network.MMNewsDataAgentImpl;
import com.padcmyanmar.sfc.network.reponses.GetNewsResponse;
import com.padcmyanmar.sfc.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by aung on 12/3/17.
 */

public class NewsModel {

    private static NewsModel objInstance;

    private List<NewsVO> mNews;
    private int mmNewsPageIndex = 1;
    private AppDatabase mAppDatabase;
    private PublishSubject<List<NewsVO>> mNewsSubject;

    private NewsModel() {
        EventBus.getDefault().register(this);
        mNews = new ArrayList<>();
    }

    public static NewsModel getInstance() {
        if(objInstance == null) {
            objInstance = new NewsModel();
        }
        return objInstance;
    }

    public void initDatabase(Context context) {
        mAppDatabase = AppDatabase.getNewsDatabase(context);
    }

    public void initPublishSubject(PublishSubject<List<NewsVO>> newsSubject) {
        this.mNewsSubject = newsSubject;
    }

    public void startLoadingMMNews() {
        Single<GetNewsResponse> getNewsResponseObservable = getMMNews();

        getNewsResponseObservable
                .subscribeOn(Schedulers.io()) //run value creation code on a specific thread (non-UI thread)
                .map(new Function<GetNewsResponse, List<NewsVO>>() {
                    @Override
                    public List<NewsVO> apply(@NonNull GetNewsResponse getNewsResponse) {
                        return getNewsResponse.getNewsList();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) //observe the emitted value of the Observable on an appropriate thread
                .subscribeWith(new DisposableSingleObserver<List<NewsVO>>() {

                    @Override
                    public void onSuccess(List<NewsVO> newsVOs) {
                        Log.d(SFCNewsApp.LOG_TAG, "onSuccess: " + newsVOs.size());
                        mNewsSubject.onNext(newsVOs);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(SFCNewsApp.LOG_TAG, "onError: " + e.getMessage());
                    }
                });
    }

    public Single<GetNewsResponse> getMMNews() {
        SFCNewsApp rxJavaApp = new SFCNewsApp();
        return rxJavaApp.getMMNewsApi().loadMMNews(mmNewsPageIndex, AppConstants.ACCESS_TOKEN);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNewsDataLoaded(RestApiEvents.NewsDataLoadedEvent event) {
        mNews.addAll(event.getLoadNews());
        mmNewsPageIndex = event.getLoadedPageIndex() + 1;
    }
}
