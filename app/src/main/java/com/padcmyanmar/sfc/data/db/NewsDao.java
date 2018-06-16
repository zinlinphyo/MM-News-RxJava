package com.padcmyanmar.sfc.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.padcmyanmar.sfc.data.vo.NewsVO;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertNews(NewsVO news);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertNewsList(NewsVO... newsList);

}
