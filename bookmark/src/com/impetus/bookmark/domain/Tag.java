package com.impetus.bookmark.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * The Tag domain object to represent one tag
 * @author manaswita.mishra
 */

@Entity
@Table(name="T_TAGS")
public class Tag implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="TAG_ID")
	private int tagId;
	
	@Column(name="TAG_NAME")	
	private String tagName;
	
	@Column(name="CREATED_DATE")
	private Date createdDate; 
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags",cascade={CascadeType.ALL})
	private Set<Bookmark> bookmarks = new HashSet<Bookmark>();
	
	public Tag()
	{
	}
	
	public Tag(int tagId, String tagName, Date createdDate)
	{
		super();
		this.tagId = tagId;
		this.tagName = tagName;
		this.createdDate = createdDate;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Set<Bookmark> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(Set<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}
	
}
