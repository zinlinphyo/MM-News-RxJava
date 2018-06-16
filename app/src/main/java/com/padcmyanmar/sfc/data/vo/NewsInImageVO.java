package com.padcmyanmar.sfc.data.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "NewsInImage", foreignKeys = {
        @ForeignKey(
                entity = NewsVO.class,
                parentColumns = "news_id",
                childColumns = "news_id"
        )}
)
public class NewsInImageVO {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "news_in_image_id")
    private int newsInImageId;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    @ColumnInfo(name = "news_id")
    private String newsId;

    public int getNewsInImageId() {
        return newsInImageId;
    }

    public void setNewsInImageId(int newsInImageId) {
        this.newsInImageId = newsInImageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }
}
