package com.impetus.bookmark.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.impetus.bookmark.domain.Bookmark;

/**
 * The dao interface for bookmark
 * @author manaswita.mishra
 *
 */
public interface BookmarkDAO
{
	
	/**
	 * Get All Bookmarks
	 * @param tagFilter
	 * @return
	 */
	public List<Bookmark> searchBookmarks(List<String> tagFilter);
	
	/**
	 * Add a new bookmark
	 * @param bookmarkBean
	 * @return
	 */
	public void addBookmark(Bookmark bookmark) ;
	/**
	 * Delete a bookmark
	 * @param bookmarkId
	 */
	public void deleteBookmark(Integer bookmarkId);
	
	
}
