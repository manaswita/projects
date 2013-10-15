package com.impetus.bookmark.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.bookmark.constants.BookmarkConstants;
import com.impetus.bookmark.dao.BookmarkDAO;
import com.impetus.bookmark.domain.Bookmark;
import com.impetus.bookmark.domain.Tag;
import com.impetus.bookmark.formbean.BookmarkBean;
import com.impetus.bookmark.util.CommonUtil;

/**
 * The service layer for bookmark operations
 * @author manaswita.mishra
 *
 */
@Service("bookmarkManager")
public class BookmarkManagerImpl implements BookmarkManager{
	
	@Autowired
	private BookmarkDAO bookmarkDAO;
	
	/**
	 * Get All Bookmarks
	 * @param tagFilter
	 * @return
	 */
	public List<Bookmark> searchBookmarks(String tagFilter) throws HibernateException,Exception{
		List<Bookmark> bookmarkList = new ArrayList<Bookmark>();
		List<String> searchCriteria=CommonUtil.getSearchList(tagFilter);
		
		bookmarkList=bookmarkDAO.searchBookmarks(searchCriteria);
		return bookmarkList;
	}
	
	/**
	 * Add a new Bookmark
	 * @param bookmarkBean
	 * @return
	 */
	public List<BookmarkBean> addBookmark(BookmarkBean bookmarkBean, Map<String,Tag> userTagMap) throws HibernateException,Exception{
		List<BookmarkBean> bookmarkList=new ArrayList<BookmarkBean>();
		Bookmark newBookMark=new Bookmark();
		Set<Tag> tags = resolveTags(bookmarkBean.getTagString(),userTagMap);
		
		newBookMark.setCreatedDate(new Date());
		newBookMark.setNotes(bookmarkBean.getNotes());
		newBookMark.setTitle(bookmarkBean.getTitle());
		newBookMark.setUrl(CommonUtil.formatURL(bookmarkBean.getUrl()));
		newBookMark.setTags(tags);
		
		bookmarkDAO.addBookmark(newBookMark);
		
		return bookmarkList;
	}
	
	/**
	 * If tag already exists, add the reference, else add it
	 * @param tagString
	 * @param userTagMap
	 * @return
	 */
	private Set<Tag> resolveTags(String tagString,Map<String,Tag> userTagMap){
		Set<Tag> tags = new HashSet<Tag>();
		
		StringTokenizer tagToken=new StringTokenizer(tagString,BookmarkConstants.COMMA_SEPARATOR);
		Tag existingTag;
		String inputTag=null;
		while(tagToken.hasMoreTokens()){
			inputTag=tagToken.nextToken().trim();
			if(null !=userTagMap){
				existingTag=userTagMap.get(inputTag);
				if(null == existingTag){
					existingTag=new Tag();
					existingTag.setCreatedDate(new Date());
					existingTag.setTagName(inputTag);
				}
				tags.add(existingTag);
			}
		}
		return tags;
	}
	
	/**
	 * Delete a Bookmark
	 * @param bookmarkId
	 */
	public void deleteBookmark(Integer bookmarkId) throws ObjectNotFoundException, HibernateException,Exception{
		bookmarkDAO.deleteBookmark(bookmarkId);
	}
}
