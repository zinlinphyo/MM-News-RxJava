package com.padcmyanmar.sfc.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.padcmyanmar.sfc.data.vo.CommentActionVO;

import java.util.List;

@Dao
public interface CommentActionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertCommentAction(CommentActionVO commentAction);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insertCommentActions(List<CommentActionVO> commentActions);

}
