package com.impetus.bookmark.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * The Bookmark domain object to represent one bookmark
 * @author manaswita.mishra
 */

@Entity
@Table(name="T_BOOKMARKS")
public class Bookmark implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="BOOKMARK_ID")
	private int bookmarkId;
	
	@Column(name="TITLE")	
	private String title;
	
	@Column(name="URL")
	private String url; 
	
	@Column(name="NOTES")
	private String notes; 

	@Column(name="CREATED_DATE")
	private Date createdDate; 

	@ManyToMany(targetEntity=Tag.class,fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name = "T_BOOKMARK_TAG_MAP", joinColumns = { 
			@JoinColumn(name = "BOOKMARK_ID") }, 
			inverseJoinColumns = { @JoinColumn(name = "TAG_ID") })
	private Set<Tag> tags;
	
	public Bookmark()
	{
	}
	
	public Bookmark(int bookmarkId, String title, String url, String notes,
			Date createdDate,Set<Tag> tags)
	{
		super();
		this.bookmarkId = bookmarkId;
		this.title = title;
		this.url = url;
		this.notes = notes;
		this.createdDate = createdDate;
		this.tags=tags;
	}

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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

}
