package com.yzx.xiaomusic.ui.search;

import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_ALBUM;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_MUSIC;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_MV;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_RADIO;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_SINGER;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_SONG_SHEET;
import static com.yzx.xiaomusic.ui.search.result.SearchResultFragment.TYPE_SEARCH_USER;

public class SearchUtils {
    public static int getSearchTypeByPosition(int position) {
        switch (position) {
            case 0:
                return TYPE_SEARCH_MUSIC;
            case 1:
                return TYPE_SEARCH_MV;
            case 2:
                return TYPE_SEARCH_SINGER;
            case 3:
                return TYPE_SEARCH_ALBUM;
            case 4:
                return TYPE_SEARCH_SONG_SHEET;
            case 5:
                return TYPE_SEARCH_RADIO;
            case 6:
                return TYPE_SEARCH_USER;
            default:
                return 0;
        }
    }
}
