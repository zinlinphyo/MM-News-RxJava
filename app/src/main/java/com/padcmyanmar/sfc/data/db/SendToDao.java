package com.padcmyanmar.sfc.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.padcmyanmar.sfc.data.vo.SentToVO;

import java.util.List;

@Dao
public interface SendToDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertSendTo(SentToVO sentTo);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertSendTos(List<SentToVO> sentTos);

}
