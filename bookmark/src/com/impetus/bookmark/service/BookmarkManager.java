package com.impetus.bookmark.service;

import java.util.List;
import java.util.Map;

import com.impetus.bookmark.domain.Bookmark;
import com.impetus.bookmark.domain.Tag;
import com.impetus.bookmark.formbean.BookmarkBean;

/**
 * The service interface for bookmark
 * @author manaswita.mishra
 *
 */
public interface BookmarkManager {
	
	/**
	 * Get All Bookmarks
	 * @param tagFilter
	 * @return
	 */
	public List<Bookmark> searchBookmarks(String tagFilter) throws Exception;
	/**
	 * Add a new bookmark
	 * @param bookmarkBean
	 * @return
	 */
	public List<BookmarkBean> addBookmark(BookmarkBean bookmarkBean,Map<String,Tag> userTagMap) throws Exception ;
	
	/**
	 * Delete a bookmark
	 * @param bookmarkId
	 */
	public void deleteBookmark(Integer bookmarkId) throws Exception;
}
