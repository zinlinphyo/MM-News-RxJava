package com.padcmyanmar.sfc.data.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aung on 12/3/17.
 */
@Entity(tableName = "SendTo", foreignKeys = {
        @ForeignKey(
                entity = NewsVO.class,
                parentColumns = "news_id",
                childColumns = "news_id"
        ),
        @ForeignKey(
                entity = ActedUserVO.class,
                parentColumns = "acted_user_id",
                childColumns = "sender_user_id"
        ),
        @ForeignKey(
                entity = ActedUserVO.class,
                parentColumns = "acted_user_id",
                childColumns = "receiver_user_id"
        )}
)
public class SentToVO {

    @PrimaryKey
    @SerializedName("send-to-id")
    @ColumnInfo(name = "sent_to_id")
    @NonNull
    private String sendToId;

    @SerializedName("sent-date")
    private String sentDate;

    @Ignore
    @SerializedName("acted-user")
    private ActedUserVO sender;

    @Ignore
    @SerializedName("received-user")
    private ActedUserVO receiver;

    @ColumnInfo(name = "news_id")
    private String newsId;

    @ColumnInfo(name = "sender_user_id")
    private String senderUserId;

    @ColumnInfo(name = "receiver_user_id")
    private String receiverUserId;

    public String getSendToId() {
        return sendToId;
    }

    public String getSentDate() {
        return sentDate;
    }

    public ActedUserVO getSender() {
        return sender;
    }

    public ActedUserVO getReceiver() {
        return receiver;
    }

    public void setSendToId(String sendToId) {
        this.sendToId = sendToId;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public void setSender(ActedUserVO sender) {
        this.sender = sender;
    }

    public void setReceiver(ActedUserVO receiver) {
        this.receiver = receiver;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(String receiverUserId) {
        this.receiverUserId = receiverUserId;
    }
}
