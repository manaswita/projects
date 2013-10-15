package com.impetus.bookmark.formbean;


/**
 * The bean carrying the bookmark details to be shown to the end user
 * @author manaswita.mishra
 *
 */
public class BookmarkBean implements Comparable<BookmarkBean>{
	
	private int bookmarkId;
	
	private String title;
	
	private String url; 
	
	private String notes; 
	
	private String tagString;
	
	private String tagFilter;
	

	public int getBookmarkId() {
		return bookmarkId;
	}

	public void setBookmarkId(int bookmarkId) {
		this.bookmarkId = bookmarkId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getTagString() {
		return tagString;
	}

	public void setTagString(String tagString) {
		this.tagString = tagString;
	}

	public String getTagFilter() {
		return tagFilter;
	}

	public void setTagFilter(String tagFilter) {
		this.tagFilter = tagFilter;
	}

	/**
	 * Sort the bookmarks on titles, if title is same, consider the ids
	 * @param arg0
	 */
	@Override
	public int compareTo(BookmarkBean arg0) {
		int cmp = this.title.compareTo(arg0.title);
	     if (cmp == 0) {
	       cmp = this.bookmarkId-arg0.bookmarkId;
	     } 
		return cmp;
	}

}
