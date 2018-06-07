package com.yzx.xiaomusic.model.entity.eventbus;

public class SearchContent {
    public SearchContent(int searchType, String searchContent) {
        this.searchType = searchType;
        this.searchContent = searchContent;
    }

    int searchType;
    String searchContent;


    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }
}
