package com.padcmyanmar.sfc.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.padcmyanmar.sfc.data.vo.NewsInImageVO;

import java.util.List;

@Dao
public abstract class NewsInImageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insertNewsInImage(NewsInImageVO newsInImage);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long[] insertNewsInImages(List<NewsInImageVO> newsInImage);

}
