package com.impetus.bookmark.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.impetus.bookmark.domain.Bookmark;
import com.impetus.bookmark.formbean.BookmarkBean;

/**
 * Validator class to handle bookmark validations
 * @author manaswita.mishra
 *
 */
@Component("bookmarkFormValidator")
public class BookmarkFormValidator implements Validator
{
	@Override
	public boolean supports(Class<?> clazz)
	{
		return Bookmark.class.isAssignableFrom(clazz);
	}

	
	/**
	 * Add bookmark validations
	 * @param model
	 * @param errors
	 */
	@Override
	public void validate(Object model, Errors errors)
	{
		BookmarkBean bookmarkBean=(BookmarkBean) model;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "url","required.url", "URL is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title","required.password", "Title is required.");
		if(null!= bookmarkBean.getNotes()&& bookmarkBean.getNotes().length()>200){
			errors.rejectValue("notes", "notes.tooLong");
		}
					
	}
}
