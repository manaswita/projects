package com.impetus.bookmark.test;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.impetus.bookmark.dao.BookmarkDAO;
import com.impetus.bookmark.dao.BookmarkDAOImpl;
import com.impetus.bookmark.domain.Bookmark;

@RunWith(MockitoJUnitRunner.class)
public class BookmarkDAOTest extends AbstractTransactionalDataSourceSpringContextTests  {

	@Mock
	private SessionFactory sessionFactory;

	/** class under test */
    private BookmarkDAO dao;
 
    public BookmarkDAOTest() {
        super();
        ApplicationContext ctx = super.getApplicationContext();
        dao = (BookmarkDAO) ctx.getBean("bookmarkDAO");
        assertNotNull(dao);
    }
 
    @Override
    protected String[] getConfigLocations() {
        return new String[] { "applicationContext.xml" };
    }

	
	/*@Override
    protected void onSetUpInTransaction() throws Exception {
		Session session = sessionFactory.getCurrentSession();
	}*/
 
 
    @Test
	public void searchBookmarksIfSessionIsNull() {
		BookmarkDAOImpl b = new BookmarkDAOImpl();

		Mockito.when(sessionFactory.getCurrentSession()).thenReturn(null);
		List<Bookmark> myList = b.searchBookmarks(null);
	}
    
    @Test
    public final void testFindAll() {
        List<Bookmark> list = dao.searchBookmarks(null);
        assertNotNull("Expected a non null list", list);
        assertTrue("Expected a non-empty list", list.size()>0);
    }
	


}
