package com.impetus.bookmark.test;

import java.util.regex.Pattern;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pattern digitPattern = Pattern.compile("^[0-9]+$");
		 System.out.println( "does is 6 digit number : "
                 + digitPattern.matcher("12334324").matches());


	}

}
