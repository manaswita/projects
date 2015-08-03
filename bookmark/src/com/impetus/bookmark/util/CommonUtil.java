package com.impetus.bookmark.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.impetus.bookmark.constants.BookmarkConstants;

/**
 * Common utilities for the bookmark project
 * @author manaswita.mishra
 *
 */
public class CommonUtil {
	
	/**
	 * format the input URL by appending http:// if not already there
	 * @param inputURL
	 * @return
	 */
	public static String formatURL(String inputURL){
		StringBuilder sbf=new StringBuilder();
		if(!inputURL.startsWith(BookmarkConstants.HTTP_APPENDER)){
			sbf.append(BookmarkConstants.HTTP_APPENDER);
		}
		sbf.append(inputURL);
		return sbf.toString();
	}
	
	/**
	 * Break the input String to a list of String Arraylist
	 * @param inputString
	 * @return
	 */
	public static List<String> getSearchList(String inputString){
		List<String> outputList =new ArrayList<String>();
		
		if(null !=inputString && !inputString.equals(BookmarkConstants.EMPTY_STRING)){
			StringTokenizer searchToken=new StringTokenizer(inputString,BookmarkConstants.COMMA_SEPARATOR);
			while(searchToken.hasMoreTokens()){
				outputList.add(searchToken.nextToken());
			}
		}
		return outputList;
	}
}

