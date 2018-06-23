package com.padcmyanmar.sfc.mvp.presenters;

import android.content.Intent;
import android.util.Log;

import com.padcmyanmar.sfc.SFCNewsApp;
import com.padcmyanmar.sfc.activities.NewsDetailsActivity;
import com.padcmyanmar.sfc.data.models.NewsModel;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.delegates.NewsItemDelegate;
import com.padcmyanmar.sfc.events.RestApiEvents;
import com.padcmyanmar.sfc.mvp.views.NewsListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class NewsListPresenter extends BasePresenter<NewsListView> implements NewsItemDelegate {

	private PublishSubject<List<NewsVO>> mNewsSubject;

	public NewsListPresenter(NewsListView view) {
		super(view);
	}

	public void onFinishSetupUIComponent(){

	}

	@Override
	public void onCreate() {
		super.onCreate();
		mNewsSubject = PublishSubject.create();
		mNewsSubject.subscribe(new Observer<List<NewsVO>>() {
			@Override
			public void onSubscribe(Disposable d) {

			}

			@Override
			public void onNext(List<NewsVO> newsVOS) {
				Log.d(SFCNewsApp.LOG_TAG, "onNext: " + newsVOS.size());
				if (newsVOS == null){
					mView.displayErrorMsg("Empty data");
				}else {
					mView.displayNewsList(newsVOS);
				}
			}

			@Override
			public void onError(Throwable e) {
				mView.displayErrorMsg(e.getMessage());
			}

			@Override
			public void onComplete() {

			}
		});

		NewsModel.getInstance().startLoadingMMNews(mNewsSubject);
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onTapComment() {

	}

	@Override
	public void onTapSendTo() {

	}

	@Override
	public void onTapFavorite() {

	}

	@Override
	public void onTapStatistics() {

	}

	@Override
	public void onTapNews(NewsVO news) {
		mView.launchNewsDetailsScreen(news.getNewsId());
	}
}
