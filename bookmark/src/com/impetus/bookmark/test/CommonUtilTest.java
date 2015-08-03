package com.impetus.bookmark.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.AbstractSingleSpringContextTests;

import com.impetus.bookmark.util.CommonUtil;

@RunWith(MockitoJUnitRunner.class)
public class CommonUtilTest extends AbstractSingleSpringContextTests {

	
	@Test
	public void testFormatURL() {
		String actual = CommonUtil.formatURL("google.com");
		Assert.assertEquals("Expected http://google.com", "http://google.com", actual);
		actual = CommonUtil.formatURL("http://google.com");
		Assert.assertEquals("Expected http://google.com", "http://google.com", actual);
		actual = CommonUtil.formatURL("htp://google.com");
		Assert.assertEquals("Expected http://htp://google.com", "http://htp://google.com", actual);
	}
	
	@Test
	public void testgetSearchList() {
		String[] expectedList={"one","two","three"};
		List<String> actual = CommonUtil.getSearchList("one,two,three");
		Assert.assertEquals("Expected something", expectedList, actual.toArray());
		
		actual = CommonUtil.getSearchList("one,two,three,");
		Assert.assertEquals("Expected to remove the extra comma", expectedList, actual.toArray());
		
		actual = CommonUtil.getSearchList(",one,two,three,");
		Assert.assertEquals("Expected to remove the preceding comma", expectedList, actual.toArray());
		
		actual = CommonUtil.getSearchList("");
		Assert.assertEquals("Expected Empty 1",0, actual.size());
		
		actual = CommonUtil.getSearchList(",");
		Assert.assertEquals("Expected Empty 2",0, actual.size());
		
		actual = CommonUtil.getSearchList(",,");
		Assert.assertEquals("Expected Empty 3",0, actual.size());
	}
}
