package com.padcmyanmar.sfc.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.padcmyanmar.sfc.data.vo.FavoriteActionVO;

import java.util.List;

@Dao
public interface FavoriteActionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertFavoriteAction(FavoriteActionVO favoriteAction);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertFavoriteActions(List<FavoriteActionVO> favoriteActions);

}
