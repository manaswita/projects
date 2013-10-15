package com.impetus.bookmark.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.impetus.bookmark.domain.Bookmark;

/**
 * The dao layer for bookmark operations
 * @author manaswita.mishra
 *
 */
@Repository
@Transactional
@Component("bookmarkDAO")
public class BookmarkDAOImpl implements BookmarkDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = Logger.getLogger(BookmarkDAO.class); 

	/**
	 * Get All Bookmarks
	 * @return
	 */
	public List<Bookmark> searchBookmarks(List<String> tagFilterList){
		List<Bookmark> bookmarkList = new ArrayList<Bookmark>();
		
		try{
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Bookmark.class);
			
			/*Filter on tags if provided. This supports mutile tag search and wildcard enties as well*/ 
			if(null !=tagFilterList && tagFilterList.size()>0){
				Disjunction orClause = Restrictions.disjunction();
				for(String tag:tagFilterList){
					orClause.add(Restrictions.like("tagName", tag, MatchMode.ANYWHERE));
				}
				criteria.createCriteria("tags").add(orClause);
			}
			
			bookmarkList=criteria.list();
		}catch (HibernateException e){
			logger.error(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return bookmarkList;
	}
	
	/**
	 * Add a new bookmark
	 * @param bookmarkBean
	 * @return
	 */
	public void addBookmark(Bookmark bookmark) {
		try{
			
			sessionFactory.getCurrentSession().persist(bookmark);
			
		}catch (HibernateException e){
			logger.error(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * Delete a bookmark
	 * @param bookmarkId
	 */
	public void deleteBookmark(Integer bookmarkId){
		Bookmark bookmark = (Bookmark) sessionFactory.getCurrentSession().load(Bookmark.class, bookmarkId);
		try{
			
			/*Bookmark bookmark = (Bookmark) sessionFactory.getCurrentSession().load(Bookmark.class, bookmarkId);*/
	        if (null != bookmark) {
	        	bookmark.getTags().clear();
	            sessionFactory.getCurrentSession().delete(bookmark);
	        }
		}catch (ObjectNotFoundException e){
			logger.error(e.getMessage());
		}catch (HibernateException e){
			logger.error(e.getMessage());
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
