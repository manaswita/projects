package com.impetus.bookmark.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.impetus.bookmark.constants.BookmarkConstants;
import com.impetus.bookmark.domain.Bookmark;
import com.impetus.bookmark.domain.Tag;
import com.impetus.bookmark.formbean.BookmarkBean;
import com.impetus.bookmark.service.BookmarkManager;
import com.impetus.bookmark.validator.BookmarkFormValidator;

/**
 * The bookmark controller for capturing all bookmark actions
 * @author manaswita.mishra
 *
 */
@Controller
public class BookmarkController {
	
	@Autowired
	private BookmarkManager bookmarkManager;
	
	@Autowired
	private BookmarkFormValidator bookmarkFormValidator;
	
	private static final Logger logger = Logger.getLogger(BookmarkController.class); 

	/**
	 * Show all bookmarks
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping("/searchBookmarks")
	public String searchBookmarks(@ModelAttribute("bookmarkBean")BookmarkBean bookmarkBean,Map<String, Object> map, HttpSession session) {
		Set<BookmarkBean> userBookmarks = new TreeSet<BookmarkBean>();
		
		try {
			String tagFilter=bookmarkBean.getTagFilter();
			List<Bookmark> allBookmarks = bookmarkManager.searchBookmarks(tagFilter);
			
			Map<String, Tag> userTagMap = new TreeMap<String, Tag>(String.CASE_INSENSITIVE_ORDER);
			StringBuilder sbf = null;

			/*Get all tags for the user*/
			for (Bookmark b : allBookmarks) {
				sbf = new StringBuilder();
				bookmarkBean = new BookmarkBean();
				for (Tag t : b.getTags()) {
					userTagMap.put(t.getTagName().trim(), t);
					sbf.append(t.getTagName() + ",");
				}
				//Remove the last comma in the tag string
				sbf.setLength(sbf.length() - 1);
				
				BeanUtils.copyProperties(bookmarkBean, b);
				bookmarkBean.setTagString(sbf.toString());
				userBookmarks.add(bookmarkBean);
			}
			/*Set the user tags to session*/
			if(null ==tagFilter || tagFilter.equals("")){
				session.setAttribute("userTagMap", userTagMap);
			}
		} catch (IllegalAccessException e) {
			map.put(BookmarkConstants.USER_ERROR_MSG, "Error while fetching bookmarks");
			logger.error(e.getMessage());
		} catch (InvocationTargetException e) {
			map.put(BookmarkConstants.USER_ERROR_MSG, "Error while fetching bookmarks");
			logger.error(e.getMessage());
		}catch (HibernateException e){
			map.put(BookmarkConstants.USER_ERROR_MSG, "Error while fetching bookmarks");
			logger.error(e.getMessage());
		}catch (Exception e) {
			map.put(BookmarkConstants.USER_ERROR_MSG, "Some Runtime Error Occured");
			logger.error(e.getMessage());
		}

		map.put("bookmarkList", userBookmarks);

		return "BookmarkList";
}
	
	/**
	 * Go to Add bookmark page
	 * @param bookmarkBean
	 * @return
	 */
	@RequestMapping(value="/gotoAddBookmark", method=RequestMethod.GET)
	public ModelAndView gotoAddBookmark(@ModelAttribute("bookmarkBean")BookmarkBean bookmarkBean){

		return new ModelAndView("AddBookmark", "bookmarkBean", bookmarkBean);
	}
	
	/**
	 * Add a new Bookmark
	 * @param bookmarkBean
	 * @param result
	 * @param session
	 * @param req
	 * @param model
	 * @return
	 */
    @RequestMapping("/addBookmark")
	public String addBookmark(@ModelAttribute("bookmarkBean") @Valid BookmarkBean bookmarkBean,final BindingResult result,HttpSession session,HttpServletRequest req,Model model){
		
			bookmarkFormValidator.validate(bookmarkBean, result);
			
			if (result.hasErrors()) {
				logger.info("Validation errors while adding bookmarks");
				return "AddBookmark";
			}
			Map<String,Tag> userTagMap=(TreeMap<String, Tag>) session.getAttribute("userTagMap");
			try {
				bookmarkManager.addBookmark(bookmarkBean,userTagMap);
		
			}catch (HibernateException e){
				model.addAttribute(BookmarkConstants.USER_ERROR_MSG, "Error while adding bookmark");
				logger.error(e.getMessage());
			}catch (Exception e) {
				model.addAttribute(BookmarkConstants.USER_ERROR_MSG, "Some Runtime Error Occured");
				logger.error(e.getMessage());
				return "AddBookmark";
			}
		return "redirect:/searchBookmarks.do";

	}
    
    /**
     * Delete a bookmark
     * @param request
     * @return
     */
    @RequestMapping(value="/deleteBookmark", method=RequestMethod.GET)
    public String deleteBookmark(HttpServletRequest request,Map<String, Object> map) {
    	try {
    		
    		Integer bookmarkId= Integer.valueOf(request.getParameter("bookmarkId"));
			bookmarkManager.deleteBookmark(bookmarkId);
			
		} catch (NumberFormatException e) {
			map.put(BookmarkConstants.USER_ERROR_MSG, "Not a valid Bookmark");
			logger.error(e.getMessage());
		} catch (ObjectNotFoundException e){
			map.put(BookmarkConstants.USER_ERROR_MSG, "Bookmark not found");
			logger.error(e.getMessage());
		}catch (HibernateException e){
			map.put(BookmarkConstants.USER_ERROR_MSG, "Error while deleting bookmark");
			logger.error(e.getMessage());
		}catch (Exception e) {
			map.put(BookmarkConstants.USER_ERROR_MSG, "Some Runtime Error Occured");
			logger.error(e.getMessage());
		}
 
        return "redirect:/searchBookmarks.do";
    }
    
    /**
     * clear the tag filter
     * @param bookmarkBean
     * @return
     */
    @RequestMapping(value="/clearFilters", method=RequestMethod.GET)
    	public String clearFilters(@ModelAttribute("bookmarkBean")BookmarkBean bookmarkBean){
    		
    	bookmarkBean.setTagFilter(null);
    	
    	return "redirect:/searchBookmarks.do";
    	}
	
}
