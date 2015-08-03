package com.impetus.pizzaonline.util;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.impetus.pizzaonline.action.GetItemAction;

public class HibernateUtil {
  static Session session = null;
  private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);
  static {
	  try {
		  // Create the SessionFactory from hibernate.cfg.xml
		  SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		  session = sessionFactory.openSession();
	  } catch (Throwable ex) {
		  LOGGER.info("hibernate session error");
		  throw new ExceptionInInitializerError(ex);
		  
	  }
  }

  public static Session getSession() {
	  return session;
  }
} 