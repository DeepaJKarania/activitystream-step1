package com.stackroute.activitystream.config;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.stackroute.activitystream.model.Message;

@SuppressWarnings("deprecation")
public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		Configuration config=new Configuration();
		try{
			config.configure("hibernate.cfg.xml");
			sessionFactory=config.buildSessionFactory();
			return sessionFactory;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public boolean sendMessage(Message message) {
		this.sessionFactory=getSessionFactory();
		if(sessionFactory!=null){
		Session session=sessionFactory.openSession();
		Transaction t=session.beginTransaction();
		try{
	   
	    message.setPostedDate();
		session.save(message);}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
		t.commit();
		return true;
		}
		else
		{
			return false;
		}
	}
	
	public List <Message> getListOfMessages()
	{
		List <Message>messageList=null;
		this.sessionFactory=getSessionFactory();
		if(sessionFactory!=null){
		Session session=sessionFactory.openSession();
		Transaction t=session.beginTransaction();
		try{
			Query query=session.createQuery("from Message");
			messageList=query.list();

		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			
		}
		t.commit();
		return messageList;
		}
		else
		{
			return null;
		}
	}



	/*
	 * build the sessionFactory object based on the parameters from
	 * hibernate.cfg.xml file. Also, handle exception if the session factory
	 * object can't be created
	 */

	
	
}
