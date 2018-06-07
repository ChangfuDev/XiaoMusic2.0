package com.yzx.xiaomusic.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(indices = {@Index(value = {"title"}, unique = true)}, tableName = "searchHistory")
public class SearchHistory {

    public SearchHistory(String title) {
        this.title = title;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long id;

    @NonNull
    public String title;

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }
}
