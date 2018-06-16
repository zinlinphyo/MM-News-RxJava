package com.padcmyanmar.sfc.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.padcmyanmar.sfc.data.vo.ActedUserVO;

import java.util.List;

@Dao
public interface ActedUserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertActedUser(ActedUserVO actedUser);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertActedUsers(List<ActedUserVO> actedUsers);

}
